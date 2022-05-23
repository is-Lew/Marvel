package com.nology.Marvel.Tools;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApiKey {

    @Value("${app.publicKey}")
    private String publicKey;

    @Value("${app.privateKey}")
    private String privateKey;

    @Value("${app.googleTranslateKey}")
    private String translateKey;

    private String ts = Long.toString(System.currentTimeMillis());

    public ApiKey() {
    }

    public String getTs() {
        return ts;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String generateHash() {
        return DigestUtils.md5Hex(ts + privateKey + publicKey );
    }

    public String getTranslateKey() {return translateKey; }
}
