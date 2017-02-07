package com.logiccache.services;

import com.logiccache.domain.Character;

import java.util.List;

public interface CharacterService {
    Character createCharacter(String characterName);

    List<Character> getCharacters();

    Character getCharacter(Long id);
}
