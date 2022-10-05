package com.stf.content.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: content-service-01
 * @description:
 * @author: ShenTF
 * @create: 2022-10-04 16:14:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorEntity {
    Integer status;
    String body;
}
