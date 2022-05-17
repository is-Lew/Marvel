package com.nology.Marvel;

import org.apache.commons.codec.digest.DigestUtils;

public class ApiKey {

    private String ts = Long.toString(System.currentTimeMillis());
    private String hash = DigestUtils.md5Hex(ts + "77e453e3865a12865f890893b432779db9d85f58" + "a40a45905b13589385366a363f889776" );

    public ApiKey() {
    }

    public String getTs() {
        return ts;
    }

    public String getHash() {
        return hash;
    }
}
