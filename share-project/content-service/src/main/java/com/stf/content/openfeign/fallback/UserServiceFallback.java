package com.stf.content.openfeign.fallback;

import com.alibaba.fastjson.JSONObject;
import com.stf.content.common.ResponseResult;
import com.stf.content.domain.dto.ShareDto;
import com.stf.content.domain.entity.User;
import com.stf.content.openfeign.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program: share-project
 * @description:
 * @author: ShenTF
 * @create: 2022-09-08 09:07:10
 **/

@Component
@Slf4j
public class UserServiceFallback implements UserService {

    @Override
    public ResponseResult getUser(Integer id) {
        log.info("fallback getUser");
        User user = User.builder().id(1).mobile("asdasdasdasdasd").nickname("降级方案返回用户").build();
        return ResponseResult.success(user);
    }
}