package com.filerental.filterantal.controller;

import com.filerental.filterantal.command.RentFileCommand;
import com.filerental.filterantal.dto.TokenKeyPair;
import com.filerental.filterantal.model.File;
import com.filerental.filterantal.service.FileService;
import com.filerental.filterantal.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {
    private final JwtService jwtService;
    private final FileService fileService;

    @PostMapping("/generateToken")
    public TokenKeyPair generateToken(@RequestBody RentFileCommand command){
        return jwtService.generateJwtToken(command);
    }

    @PostMapping("/")
    public File getFile(@RequestBody TokenKeyPair tokenKeyPair){
        return fileService.getFile(tokenKeyPair);
    }

    @PostMapping("/create")
    public File save(File file){
        return fileService.save(file);
    }
}
