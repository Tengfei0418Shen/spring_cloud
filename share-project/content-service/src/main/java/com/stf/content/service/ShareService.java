package com.stf.content.service;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.stf.content.domain.entity.Share;

import java.util.List;

/**
* @author 小曹同学
* @description 针对表【share(分享表)】的数据库操作Service
* @createDate 2022-09-06 16:40:08
*/
public interface ShareService {

    Share findById(Integer id);
    List<Share> findAll();

    String getNumber(Integer num);

    String getNumberBlock(Integer num, BlockException exception);
}
