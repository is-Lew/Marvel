package com.nology.Marvel.Models;

import com.nology.Marvel.Tools.ApiKey;
import com.fasterxml.jackson.databind.JsonNode;
import com.nology.Marvel.Utilities.WebService;
import org.json.simple.JSONArray;
import org.springframework.web.client.RestTemplate;


public class MarvelCharacters {

    private static JSONArray idArray = new JSONArray();

    public static void getIDs (ApiKey key) {
        int offset = 0;
        while ( offset < 1600) {
            WebService url = new WebService(offset, key.getTs(), key.getPublicKey(), key.generateHash());
            RestTemplate restTemplate = new RestTemplate();
            JsonNode characters = restTemplate.getForEntity(url.getMarvelApiUrl(), JsonNode.class).getBody();
            idArray.addAll(characters.get("data").get("results").findValues("id"));
            offset += 100;
        }
    }

    public static JSONArray getIdArray() {
        return idArray;
    }
}
