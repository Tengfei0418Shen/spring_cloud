package com.stf.content.service;

import com.stf.content.domain.entity.Share;
import com.stf.content.domain.entity.ShareAuditDto;

import java.util.List;


public interface ShareService {

    Share findById(Integer id);
    List<Share> findAll();

    String getNumber(Integer num);

    Share auditShare(ShareAuditDto shareAuditDto);


//    String getNumberBlock(Integer num, BlockException exception);
}
