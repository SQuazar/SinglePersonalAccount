package net.quazar.backend.controller;

import lombok.AllArgsConstructor;
import net.quazar.backend.entity.FileEntry;
import net.quazar.backend.service.FileUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/files")
@Controller
@AllArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @ResponseBody
    @PostMapping("/upload")
    public FileEntry upload(@RequestParam("file") MultipartFile file, @RequestParam("name") String displayName) {
        return fileUploadService.uploadFile(file, displayName);
    }

    @GetMapping
    public String page() {
        return "/upload";
    }

}
