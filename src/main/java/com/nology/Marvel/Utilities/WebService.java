package com.nology.Marvel.Utilities;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;

public class WebService {
    private String MarvelApiUrl;
    private String characterId = "blank";
    private String timestamp;
    private String publicKey;
    private int offset;
    private String hash;

    public WebService(String characterId, String timestamp, String publicKey, String hash) {
        this.characterId = characterId;
        this.timestamp = timestamp;
        this.publicKey = publicKey;
        this.hash = hash;
        setMarvelApiUrl();
    }

    public WebService(int offset, String timestamp, String publicKey, String hash) {
        this.offset = offset;
        this.timestamp = timestamp;
        this.publicKey = publicKey;
        this.hash = hash;
        setMarvelApiUrl();
    }

    public String getMarvelApiUrl() {
        return MarvelApiUrl;
    }

    public void setMarvelApiUrl() {
        if (!characterId.equals("blank")) {
            MarvelApiUrl ="https://gateway.marvel.com/v1/public/characters/"+characterId+"?&ts="+timestamp+"&apikey="+publicKey+"&hash="+hash;
        } else {
            MarvelApiUrl = "https://gateway.marvel.com/v1/public/characters?limit=100&offset="+offset+"&ts="+timestamp+"&apikey="+publicKey+"&hash="+hash;
        }

    }

    public JsonNode getJson (WebService url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url.getMarvelApiUrl(), JsonNode.class).getBody();
    }

}
