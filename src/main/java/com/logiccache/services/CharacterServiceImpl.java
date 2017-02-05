package com.logiccache.services;

import com.logiccache.domain.Character;
import com.logiccache.repositories.CharacterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component("characterService")
@Transactional
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterServiceImpl.class);

    public CharacterServiceImpl(CharacterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Character createCharacter(String characterName) {
        LOGGER.info("Creating new character {}.", characterName);
        Character character = new Character(characterName);
        return this.repository.save(character);
    }

    @Override
    public List<Character> getCharacters() {
        return repository.findAll();
    }

}
