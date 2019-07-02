package com.example.hicall.controller;

import com.example.hicall.bean.HiCallResponse;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

@EnableAutoConfiguration
@RestController
public class UploadController {
    private String path = "F:/log";
    @RequestMapping("/upload")
    public HiCallResponse fileUpload(@RequestParam("file")MultipartFile file){
        HiCallResponse res = new HiCallResponse();
        if(file.isEmpty()){
            res.setErrorNo("-1");
            res.setMessage("file is empty");
            return res;
        }

        String fileName = file.getOriginalFilename();

        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try{
            file.transferTo(dest);
        }catch (Exception e){
            res.setErrorNo("-1");
            res.setMessage(e.getMessage());
        }
        return res;
    }

    @RequestMapping("/fileList")
    public HiCallResponse getFileList(){
        HiCallResponse res = new HiCallResponse();
        File file = new File(path);
        File[] fileList = file.listFiles();
        List<String> fileNames = new LinkedList<>();
        for (File f:fileList) {
            fileNames.add(f.getName());
        }
        res.getInfo().put("files",fileNames);
        return res;
    }
}
