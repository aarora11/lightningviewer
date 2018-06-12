package com.aarora.lightningviewer.controller;

import com.aarora.lightningviewer.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class UploadFile {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/")
    public String index(){
        return "upload";
    }


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        if (file.isEmpty()){
            return "error";
        }
        documentService.processDocument(file);

        return "Uploaded Successfully";
    }
}
