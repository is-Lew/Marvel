package com.nology.Marvel.Models;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class MarvelCharacter {

    private int id;
    private String name;
    private String description;
    private Thumbnail thumbnail;

    public MarvelCharacter(int id, String name, String description, Thumbnail thumbnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public static MarvelCharacter getCharacterById (JsonNode character) {
        assert character != null;
        JsonNode characterResults = character.get("data").get("results");
        Thumbnail characterThumbnail = new Thumbnail((characterResults.findValue("thumbnail").findValue("path").asText()), (characterResults.findValue("thumbnail").findValue("extension").asText()));
        return new MarvelCharacter((characterResults.findValue("id").asInt()),(characterResults.findValue("name").asText()),(characterResults.findValue("description").asText()), characterThumbnail);
    }

    public static MarvelCharacter getTranslatedCharacterById (JsonNode character, String languageCode) {
        MarvelCharacter translated = MarvelCharacter.getCharacterById(character);
        Translate translate = TranslateOptions.newBuilder().setTargetLanguage(languageCode).build().getService();
        Translation translation = translate.translate(translated.getDescription());
        translated.setDescription(translation.getTranslatedText());
        return translated;
    }

}
