package com.nology.Marvel.Controller;

import com.nology.Marvel.Tools.ApiKey;
import com.nology.Marvel.Models.MarvelCharacter;
import com.nology.Marvel.Models.MarvelCharacters;
import com.nology.Marvel.Utilities.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;


@RestController
public class MarvelController {

    @Autowired
    private ApiKey key;


    @GetMapping("/characters")
    private ResponseEntity<Object> getCharacterIds() {
        if (MarvelCharacters.getIdArray().isEmpty()) {MarvelCharacters.getIDs(key);}
        try {
            return ResponseEntity.status(HttpStatus.OK).body(MarvelCharacters.getIdArray());
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/characters/{characterId}")
    private ResponseEntity<Object> getCharacterById(@PathVariable String characterId) {
        try {
            WebService url = new WebService(characterId, key.getTs(), key.getPublicKey(), key.generateHash());
            return ResponseEntity.status(HttpStatus.OK).body(MarvelCharacter.getCharacterById(url.getJson(url)));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/characters/{characterId}/{languageCode}")
    public ResponseEntity<MarvelCharacter> getCharacterByIdAndLanguage (@PathVariable String characterId, @PathVariable String languageCode) {
        try {
            WebService url = new WebService(characterId, key.getTs(), key.getPublicKey(), key.generateHash());
            return ResponseEntity.status(HttpStatus.OK).body(MarvelCharacter.getTranslatedCharacterById(url.getJson(url),languageCode));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
