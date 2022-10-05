package com.stf.files.controler;

import com.stf.files.common.ResponseResult;
import com.stf.files.common.ResultCode;
import com.stf.files.util.MinIoTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @program: file-service-01
 * @description:
 * @author: ShenTF
 * @create: 2022-09-27 21:31:49
 **/
@RestController
@RequestMapping(value = "/file")
public class FileControler {
    @Resource
    MinIoTemplate minIoTemplate;

    @Value("${minio.endpoint}")
    String endPoint;

    @PostMapping("/upload")
    public ResponseResult upload(MultipartFile uploadFiles) {
        //对上传文件重命名，避免文件重名
        String oldName = uploadFiles.getOriginalFilename();
        assert oldName != null;
        String newName = UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));

        // 上传图片返回minio地址
        try {
            minIoTemplate.putObject("public", newName, uploadFiles.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
        return ResponseResult.success(endPoint + "/" + "public" + "/" + newName);
    }
}
