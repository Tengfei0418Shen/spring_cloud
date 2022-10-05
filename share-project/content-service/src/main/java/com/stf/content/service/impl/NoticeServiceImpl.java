package com.stf.content.service.impl;

import com.stf.content.domain.entity.Notice;
import com.stf.content.repository.NoticeRepository;
import com.stf.content.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
* @author 小曹同学
* @description 针对表【notice】的数据库操作Service实现
* @createDate 2022-09-06 20:09:11
*/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public Notice getLatestNotice() {
        Sort sort = Sort.by("createTime").descending();
        return noticeRepository.findByShowFlag(true,sort).get(0);
    }
}
