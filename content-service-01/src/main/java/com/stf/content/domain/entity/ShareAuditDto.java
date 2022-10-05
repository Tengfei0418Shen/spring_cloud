package com.stf.content.domain.entity;

import com.stf.content.common.AuditStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: content-service-01
 * @description:
 * @author: ShenTF
 * @create: 2022-10-04 20:59:55
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShareAuditDto {
    private Integer id;
    private AuditStatusEnum auditStatus;
    private String reason;
    private Boolean showFlag;
}