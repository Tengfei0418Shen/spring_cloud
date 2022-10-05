package com.stf.content.service;

import com.stf.content.domain.entity.Share;

import java.util.List;


public interface ShareService {

    Share findById(Integer id);
    List<Share> findAll();

    String getNumber(Integer num);

//    String getNumberBlock(Integer num, BlockException exception);
}
