package com.example.backend.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;


@RestController
@RequestMapping("/file")
public class FileController {

    private String fileID;
    @Value("${files.upload.path}")
    private String fileUploadPath;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        //存储到磁盘，path路径在配置文件
        File uploadFile = new File(fileUploadPath);
        if(!uploadFile.exists()){
            uploadFile.mkdirs();
        }
       //定义文件的唯一标识码
        String uuid = IdUtil.fastSimpleUUID();
        //String url = "http://localhost:9090/"+uuid;
        fileID = uuid + StrUtil.DOT + type;
        File finalUploadFile = new File(fileUploadPath + fileID);
        file.transferTo(finalUploadFile);
        return fileID;
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws Exception{
        //根据文件ID获取文件
        File uploadFile = new File(fileUploadPath + fileID);
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileID,"UTF-8"));
        response.setContentType("application/octet-stream");

        //读取文件字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }
}
