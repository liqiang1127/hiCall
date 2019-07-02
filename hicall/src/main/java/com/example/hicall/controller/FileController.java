package com.example.hicall.controller;

import com.example.hicall.bean.HiCallResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

@EnableAutoConfiguration
@RestController
public class FileController {
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

    @Autowired
    HttpServletResponse response;
    @RequestMapping("/down")
    public HiCallResponse down(@RequestParam("fileName") String fileName){
        HiCallResponse res = new HiCallResponse();
        File file = new File(path + "/" +fileName);
        if(!file.exists()){
            res.setErrorNo("-1");
            res.setMessage("file does`t exists");
            return res;
        }
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);

        byte[] buffer = new byte[1024];
        FileInputStream fis;
        BufferedInputStream bis;

        OutputStream os;

        try {
            os = response.getOutputStream();
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            int i = bis.read(buffer);
            while( i != -1){
                os.write(buffer);
                i = bis.read(buffer);
            }
            bis.close();
            fis.close();
        }catch (Exception e){
            res.setErrorNo("-1");
            res.setMessage(e.getMessage());
            return res;
        }
        return null;
    }


}
