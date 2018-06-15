package com.aarora.lightningviewer.controller;

import com.aarora.lightningviewer.entity.DocumentModel;
import com.aarora.lightningviewer.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@RestController
public class UploadFile {
@Autowired
    private DocumentService documentService;


    @GetMapping("/")
    public String index() {
        return "upload";
    }


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            return "error";
        }
        documentService.processDocument(file);
        return "Uploaded Successfully";
    }

    @GetMapping("/getAllImages")
    public Iterable<DocumentModel> getAllStoredImages() {
        return documentService.getAllValues();
    }

    @GetMapping("/getImage/{id}")
    @CrossOrigin
    public Optional<DocumentModel> getImageForId(@PathVariable("id") int id) {
        return documentService.getImageById(id);
    }

    @GetMapping("/getAllNames")
    @CrossOrigin
    public Iterable<String> getAllDocumentNames() {

        return documentService.getAllDocumentNames();

    }

}
