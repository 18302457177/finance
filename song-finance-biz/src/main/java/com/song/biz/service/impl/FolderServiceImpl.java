package com.song.biz.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.song.biz.config.ObjectConvertor;
import com.song.biz.domain.Folder;
import com.song.biz.dto.AdminDTO;
import com.song.biz.dto.form.CreateFolderForm;
import com.song.biz.dto.form.DelForm;
import com.song.biz.dto.form.ListFolderForm;
import com.song.biz.dto.form.UpdateFolderForm;
import com.song.biz.dto.vo.GetFolderVo;
import com.song.biz.dto.vo.ListFolderVo;
import com.song.biz.mapper.FolderMapper;
import com.song.biz.service.FileService;
import com.song.biz.service.FolderService;
import com.song.common.exception.BizException;
import com.song.common.service.TokenService;
import com.song.common.util.DateUtil;
import com.song.mybatis.help.MyBatisWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.song.biz.domain.FolderField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {
    final FolderMapper mapper;
    final ObjectConvertor objectConvertor;
    final TokenService<AdminDTO> tokenService;
    final ObjectMapper objectMapper;
    final ApplicationContext applicationContext;
    final FileService fileService;

    /**
     * 创建文件夹
     *
     * @param form
     * @return
     */
    @Override
    public long create(CreateFolderForm form) {
        if (form.getPid() > 0 && !isExists(form.getPid())) {
            throw new BizException("上级目录不存在");
        }
        Folder folder = objectConvertor.toFolder(form);
        folder.initDefault();
        folder.setMemberId(tokenService.getThreadLocalUserId());
        folder.setTenantId(tokenService.getThreadLocalTenantId());
        mapper.insert(folder);
        return folder.getId();
    }

    /**
     * 修改文件夹
     *
     * @param form
     * @return
     */
    @Override
    public boolean update(UpdateFolderForm form) {
        MyBatisWrapper<Folder> wrapper = new MyBatisWrapper<>();
        wrapper.update(Name, form.getName())
                .update(Sort, form.getSort())
                .update(UpdateTime, DateUtil.getSystemTime())
                .whereBuilder()
                .andEq(Id, form.getId())
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andEq(DelFlag, false);

        return mapper.updateField(wrapper) > 0;
    }

    /**
     * 修改文件夹
     *
     * @param form
     * @return
     */
    @Override
    public boolean del(DelForm form) {
        if (getChildCount(form.getId()) > 0) {
            throw new BizException("必须将当前目录的所有子目录删除才能删除该目录");
        }
        if (fileService.countByFolderId(form.getId()) > 0) {
            throw new BizException("该目录下还有文件不能删除");
        }
        MyBatisWrapper<Folder> wrapper = new MyBatisWrapper<>();
        wrapper.update(DelFlag, true)
                .update(UpdateTime, DateUtil.getSystemTime())
                .whereBuilder()
                .andEq(Id, form.getId())
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andEq(DelFlag, false);

        return mapper.updateField(wrapper) > 0;
    }

    /**
     * 查询文件夹列表
     *
     * @param form
     * @return
     */
    @Override
    public List<ListFolderVo> list(ListFolderForm form) {
        MyBatisWrapper<Folder> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Pid, Name, Sort)
                .whereBuilder()
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andEq(DelFlag, false)
                .andEq(Pid, form.getPid());
        wrapper.orderByAsc(Sort);
        List<Folder> list = mapper.list(wrapper);
        List<ListFolderVo> result = objectConvertor.toListFolderVo(list);

        //获取子节点的数量
        if (!CollectionUtils.isEmpty(list)) {
            List<Folder> childList = list(result.stream().map(ListFolderVo::getId).collect(Collectors.toSet()));
            result.forEach(p -> {
                p.setChildCount(childList.stream().filter(c -> Objects.equals(p.getId(), c.getPid())).count());
            });
        }
        return result;
    }

    /**
     * 查询文件夹
     *
     * @param id
     * @return
     */
    @Override
    public GetFolderVo get(long id) {
        MyBatisWrapper<Folder> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Pid, Name, Sort)
                .whereBuilder()
                .andEq(Id, id)
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andEq(DelFlag, false);
        Folder folder = mapper.get(wrapper);
        if (folder == null) {
            throw new BizException("文件夹不存在");
        }
        GetFolderVo result = objectConvertor.toGetFolderVo(folder);
        result.setParentName("根目录");
        if (folder.getPid() > 0) {
            wrapper.clear();
            wrapper.select(Id, Pid, Name, Sort)
                    .whereBuilder()
                    .andEq(Id, folder.getPid())
                    .andEq(TenantId, tokenService.getThreadLocalTenantId())
                    .andEq(DelFlag, false);
            folder = mapper.get(wrapper);
            if (folder == null) {
                throw new BizException("上级目录不存在");
            }
            result.setParentName(folder.getName());
        }
        return result;
    }

    private List<Folder> list(Set<Long> pids) {
        MyBatisWrapper<Folder> wrapper = new MyBatisWrapper<>();
        wrapper.select(Id, Pid, Name, Sort)
                .whereBuilder()
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andEq(DelFlag, false)
                .andIn(Pid, pids);
        wrapper.orderByAsc(Sort);
        return mapper.list(wrapper);
    }

    private boolean isExists(long id) {
        MyBatisWrapper<Folder> wrapper = new MyBatisWrapper<>();
        wrapper.whereBuilder()
                .andEq(Id, id)
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andEq(DelFlag, false);
        return mapper.count(wrapper) > 0;
    }

    /**
     * 获取子节点数量
     *
     * @param pid
     * @return
     */
    private int getChildCount(long pid) {
        MyBatisWrapper<Folder> wrapper = new MyBatisWrapper<>();
        wrapper.whereBuilder()
                .andEq(Pid, pid)
                .andEq(TenantId, tokenService.getThreadLocalTenantId())
                .andEq(DelFlag, false);
        return mapper.count(wrapper);
    }

}
