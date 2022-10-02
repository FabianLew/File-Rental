package com.filerental.filterantal.service;

import com.filerental.filterantal.ClaimsUtils;
import com.filerental.filterantal.command.RentFileCommand;
import com.filerental.filterantal.dto.TokenKeyPair;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    public TokenKeyPair generateJwtToken(RentFileCommand command) {
        try {
            Pair<PrivateKey, PublicKey> keys = generateTokenKeyPair();
            Map<String, Object> claims = createTokenClaims(command);

            if (!command.isPermanent() && command.getRentedTo() == null){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
            }
            return new TokenKeyPair(Jwts.builder().setClaims(claims)
                    .signWith(SignatureAlgorithm.RS256, keys.getFirst())
                    .compact(), encodePublicKey(keys.getSecond()));

        } catch (NoSuchAlgorithmException e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Jws<Claims> excludeJws(String key, String token){
        return Jwts.parser()
                .setSigningKey(decodePublicKey(key))
                .parseClaimsJws(token);
    }

    private String encodePublicKey(PublicKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    private Map<String, Object> createTokenClaims(RentFileCommand command) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ClaimsUtils.FILE_UUID, command.getFileUuid());
        claims.put(ClaimsUtils.IS_PERMANENT, command.isPermanent());

        if(!command.isPermanent()){
            claims.put(ClaimsUtils.RENTED_TO, command.getRentedTo());
        }
        return claims;
    }


    private Pair<PrivateKey, PublicKey> generateTokenKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGenerator = null;
        keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(2048);
        KeyPair keyPair = keyGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        return Pair.of(privateKey, publicKey);
    }


    private PublicKey decodePublicKey(String key){
        try{
            byte[] byteKey = Base64.getDecoder().decode(key.getBytes());
            X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");

            return kf.generatePublic(X509publicKey);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
