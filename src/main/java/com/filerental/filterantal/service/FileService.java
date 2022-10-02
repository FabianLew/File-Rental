package com.filerental.filterantal.service;

import com.filerental.filterantal.ClaimsUtils;
import com.filerental.filterantal.dto.TokenKeyPair;
import com.filerental.filterantal.model.File;
import com.filerental.filterantal.repository.FileRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final JwtService jwtService;

    public File save(File file){
        return fileRepository.save(file);
    }

    public File getFile(TokenKeyPair tokenKeyPair) {
        Jws<Claims> jws = jwtService.excludeJws(tokenKeyPair.getPublicKey(), tokenKeyPair.getToken());
        Claims claims = jws.getBody();

        if (tokenExpired(claims)) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }

        File file = fileRepository.findByUuid(UUID.fromString((String) claims.get(ClaimsUtils.FILE_UUID)));
        if (file == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return file;
    }

    private boolean tokenExpired(Claims claims) {
        return !(boolean) claims.get(ClaimsUtils.IS_PERMANENT)
                && ((Long) claims.get(ClaimsUtils.RENTED_TO) < Instant.now().toEpochMilli());
    }
}
