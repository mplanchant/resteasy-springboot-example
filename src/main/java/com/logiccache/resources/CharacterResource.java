package com.logiccache.resources;

import com.logiccache.domain.Character;
import com.logiccache.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/characters")
@Component
public class CharacterResource {

    private final CharacterService characterService;

    @Autowired
    public CharacterResource(CharacterService characterService) {
        this.characterService = characterService;
    }

    @POST
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON})
    public Character character(String characterName) {
        return characterService.createCharacter(characterName);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Character> characters() {
        return characterService.getCharacters();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Character getCharacter(@PathParam("id") Long id) {
        return characterService.getCharacter(id);
    }

}
