package com.nology.Marvel;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.simple.JSONArray;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class MarvelCharacters {

    private JSONArray idArray = new JSONArray();
    private JSONArray characterArray = new JSONArray();
//    private JSONArray characterNameArray = new JSONArray();
//    private JSONArray characterDescriptionArray = new JSONArray();


    public JSONArray getCharacterArray() {
        return characterArray;
    }

    public void getIDs () {
        int offset = 0;
        while ( offset < 1500) {
            ApiKey key = new ApiKey();
            String url = "https://gateway.marvel.com/v1/public/characters?limit=100&offset="+offset+"&ts="+key.getTs()+"&apikey=a40a45905b13589385366a363f889776&hash="+key.getHash();
            RestTemplate restTemplate = new RestTemplate();
            JsonNode characters = restTemplate.getForEntity(url, JsonNode.class).getBody();
            idArray.add(characters.get("data").get("results").findValues("id"));
            offset += 100;
        }
    }

    public JSONArray getIdArray() {
        return idArray;
    }

//    public void getCharacters () {
////        JSONArray completeCharacterList = new JSONArray();
//        getIDs();
//        JsonNode characters = null;
//        int offset = 0;
//        while ( offset < 1500) {
//            ApiKey key = new ApiKey();
//            String url = "https://gateway.marvel.com/v1/public/characters?limit=100&offset="+offset+"&ts="+key.getTs()+"&apikey=a40a45905b13589385366a363f889776&hash="+key.getHash();
//            RestTemplate restTemplate = new RestTemplate();
//            characters = restTemplate.getForEntity(url, JsonNode.class).getBody();
//            for (int i = 0; i < Objects.requireNonNull(characters).size() ; i++) {
//                MarvelCharacter character = new MarvelCharacter((characters.get("data").get("results").findValues("id").get(i).asInt()),(characters.get("data").get("results").findValues("name").get(i).asText()),(characters.get("data").get("results").findValues("description").get(i).asText()));
//                characterArray.add(character);
//            }
//            offset += 100;
//
//        }
//
//
//    }
}
