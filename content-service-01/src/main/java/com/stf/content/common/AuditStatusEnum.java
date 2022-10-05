package com.stf.content.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: content-service-01
 * @description:
 * @author: ShenTF
 * @create: 2022-10-04 21:01:48
 **/

@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    /**
     *  待审核
     */
    NOT_YET,
    /**
     *  通过
     */
    PASS,
    /**
     *  拒绝
     */
    REJECT
}