package com.song.biz.dto;


import com.song.common.dto.BaseUserInfoDTO;
import lombok.Data;

import java.util.List;

@Data
public class AdminDTO extends BaseUserInfoDTO {
    private List<Integer> permissions;
}
