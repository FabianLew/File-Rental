package com.filerental.filterantal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenKeyPair {
    private String token;
    private String publicKey;
}
