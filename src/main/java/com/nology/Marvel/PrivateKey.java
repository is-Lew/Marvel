package com.nology.Marvel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PrivateKey {

    @Value("${app.publicKey}")
    private String publicKey;

    @Value("${app.privateKey}")
    private String privateKey;

    public PrivateKey() {
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}
