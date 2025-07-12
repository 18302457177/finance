package com.song.biz.dto.form;


import lombok.Data;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;

@Data
public class UpdateRoleDisableForm {
    /**
     * 角色id
     */
    @NotNull
    @Range(min = 1)
    private Integer id;

    /**
     * 禁用或启用角色，true禁用，false启用
     */
    @NotNull
    private Boolean disable;
}
