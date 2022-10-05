package com.stf.content.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.stf.content.common.ResponseResult;
import com.stf.content.common.ResultCode;
import com.stf.content.domain.entity.Notice;
import com.stf.content.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RefreshScope
@RequestMapping(value = "/notice")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeController {
    private final NoticeService noticeService;

    @Value("${disableNoticeRequest:false}")
    private Boolean disableNotice;

    @GetMapping("/latest")
    @SentinelResource(value = "getNotice", blockHandler = "getNoticeBlock")
    public ResponseResult getNotice() {
        if (disableNotice) {
            log.info("暂停服务公告");
            return ResponseResult.failure(ResultCode.INTERFACE_FORBID_VISIT);
        }
        Notice notice = noticeService.getLatestNotice();
        if (notice != null) {
            return ResponseResult.success(notice);
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    public  ResponseResult getNoticeBlock(BlockException exception) {
        log.info("接口被限流");
        log.info(exception.toString());
        return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
    }

}