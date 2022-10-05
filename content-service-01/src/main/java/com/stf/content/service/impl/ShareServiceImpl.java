package com.stf.content.service.impl;

import com.stf.content.domain.entity.Share;
import com.stf.content.domain.entity.ShareAuditDto;
import com.stf.content.repository.ShareRepository;
import com.stf.content.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl
implements ShareService {

    private final ShareRepository shareRepository;

    @Override
    public Share findById(Integer id) {
        return shareRepository.findById(id).orElse(null);
    }

    @Override
    public List<Share> findAll() {
        return shareRepository.findAll();
    }

    @Override
//    @SentinelResource(value = "getNumber", blockHandler = "getNumberBlock")
    public String getNumber(Integer num) {
        return "number={"+num+"}";
    }

    @Override
    public Share auditShare(ShareAuditDto shareAuditDto) {
        Share share = shareRepository.findById(shareAuditDto.getId()).orElse(null);
        if (!Objects.equals("NOT_YET",share.getAuditStatus())){
            throw new IllegalArgumentException("参数非法！该分享已审核");

        }
        share.setAuditStatus(shareAuditDto.getAuditStatus().toString());
        share.setReason(shareAuditDto.getReason());
        share.setShowFlag(shareAuditDto.getShowFlag()?1:0);
        return shareRepository.saveAndFlush(share);
    }


//    @Override
//    public String getNumberBlock(Integer num, BlockException exception) {
//        return "BLOCKED";
//    }
}
