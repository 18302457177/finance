package com.song.controller;


import com.song.biz.dto.form.*;
import com.song.biz.dto.vo.GetAccountBookVo;
import com.song.biz.dto.vo.ListAccountBookVo;
import com.song.biz.service.AccountBookService;
import com.song.common.dto.ApiResponse;
import com.song.mybatis.help.PageInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 账套
 */
@RestController
@RequestMapping(value = "/account_book")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AccountBookController {
    final AccountBookService accountBookService;

    /**
     * 获取账套详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/get")
    public ApiResponse<GetAccountBookVo> get(@NotNull @Min(value = 1) @RequestParam long id) {
        return ApiResponse.success(accountBookService.get(id));
    }

    /**
     * 查询账套列表
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/list")
    public ApiResponse<PageInfo<ListAccountBookVo>> list(@Validated @ModelAttribute ListAccountBookForm request) {
        return ApiResponse.success(accountBookService.list(request));
    }

    /**
     * 创建账套
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/add")
    public ApiResponse<Boolean> add(@Validated @RequestBody AddAccountBookForm request) {
        return ApiResponse.success(accountBookService.add(request));
    }

    /**
     * 禁用启用账套
     *
     * @param form
     * @return
     */
    @PostMapping(value = "/disable")
    public ApiResponse<Boolean> disable(@Validated @RequestBody AccountBookDisableForm form) {
        return ApiResponse.success(accountBookService.disable(form));
    }

    /**
     * 删除账套
     *
     * @param form
     * @return
     */
    @PostMapping(value = "/del")
    public ApiResponse<Boolean> del(@Valid @RequestBody DelForm form) {
        return ApiResponse.success(accountBookService.del(form));
    }

    /**
     * 编辑账套
     *
     * @param form
     * @return
     */
    @PostMapping(value = "/update")
    public ApiResponse<Boolean> update(@Validated @RequestBody UpdateAccountBookForm form) {
        return ApiResponse.success(accountBookService.update(form));
    }
}
