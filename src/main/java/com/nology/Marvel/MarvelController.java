package com.nology.Marvel;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.simple.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@RestController
public class MarvelController {


    @GetMapping("/characters")
    private ResponseEntity<Object> getCharacterIds() {
        MarvelCharacters completeCharList = new MarvelCharacters();
        completeCharList.getIDs();
        try {
            return ResponseEntity.status(HttpStatus.OK).body(completeCharList.getIdArray());
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/characters/{characterId}")
    private ResponseEntity<Object> getCharacterById(@PathVariable String characterId) {
        ApiKey key = new ApiKey();
        try {
            String url = "https://gateway.marvel.com/v1/public/characters/"+characterId+"?&ts="+key.getTs()+"&apikey=a40a45905b13589385366a363f889776&hash="+key.getHash();
            RestTemplate restTemplate = new RestTemplate();
            JsonNode character = restTemplate.getForEntity(url, JsonNode.class).getBody();
            return ResponseEntity.status(HttpStatus.OK).body(character.get("data").get("results"));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


//    @GetMapping("/test")
//    private ResponseEntity<Object> test() {
//        MarvelCharacters completeCharList = new MarvelCharacters();
//        completeCharList.getCharacters();
//        try {
//
//            return ResponseEntity.status(HttpStatus.OK).body(completeCharList.getCharacterArray());
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }


}
