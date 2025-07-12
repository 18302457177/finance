package com.song.controller;


import com.song.biz.dto.form.CreateVoucherForm;
import com.song.biz.dto.form.DelVoucherForm;
import com.song.biz.dto.form.ListVoucherForm;
import com.song.biz.dto.vo.GetVoucherVo;
import com.song.biz.dto.vo.ListVoucherVo;
import com.song.biz.service.VoucherService;
import com.song.common.dto.ApiResponse;
import com.song.mybatis.help.PageInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 凭证
 */
@RestController
@RequestMapping(value = "/voucher")
@RequiredArgsConstructor
@Slf4j
public class VoucherController {
    final VoucherService voucherService;

    /**
     * 创建或修改凭证
     *
     * @param form
     * @return
     */
    @PostMapping(value = "/save")
    public ApiResponse<Boolean> save(@Validated @RequestBody CreateVoucherForm form) {
        return ApiResponse.success(voucherService.save(form));
    }

    /**
     * 分页查询凭证
     *
     * @param form
     * @return
     */
    @PostMapping(value = "/list")
    public ApiResponse<PageInfo<ListVoucherVo>> list(@Validated @RequestBody ListVoucherForm form) {
        return ApiResponse.success(voucherService.list(form));
    }

    /**
     * 获取凭证明细
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/get")
    public ApiResponse<GetVoucherVo> get(@RequestParam
                                         @NotNull
                                         @Range(min = 1)
                                         Long id) {
        return ApiResponse.success(voucherService.get(id));
    }

    /**
     * 删除凭证
     *
     * @param form
     * @return
     */
    @PostMapping(value = "/del")
    public ApiResponse<Boolean> del(@Valid @RequestBody DelVoucherForm form) {
        return ApiResponse.success(voucherService.del(form));
    }
}
