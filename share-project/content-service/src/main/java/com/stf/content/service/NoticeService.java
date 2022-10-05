package com.stf.content.service;

import com.stf.content.domain.entity.Notice;

/**
* @author 小曹同学
* @description 针对表【notice】的数据库操作Service
* @createDate 2022-09-06 20:09:11
*/
public interface NoticeService  {

    Notice getLatestNotice();
}
