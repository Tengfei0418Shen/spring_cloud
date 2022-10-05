package com.stf.content.domain.dto;

import com.stf.content.domain.entity.Share;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: share-project
 * @description:
 * @Author: 曹红亮
 * @create: 2022-09-06 16:55
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShareDto {
    private String nickName;
    private String avatar;
    private Share share;
}
