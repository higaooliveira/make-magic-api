package com.higor.make_magic.resource;

import com.higor.make_magic.domain.dto.CharacterDTO;
import com.higor.make_magic.domain.entity.Character;
import com.higor.make_magic.domain.service.definition.CharacterServiceDefinition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value="/api")
@Api(value = "Make Magic")
public class CharacterResource {

    @Autowired
    private CharacterServiceDefinition characterService;

    /**
     *
     * @param house The house hash code to filter all characters by house (Can be null)
     * @return List of Characters
     */
    @ApiOperation(value = "List All registered characters")
    @GetMapping(value = "/characters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Character>> getAll(@RequestParam(required = false) String house){
        return ResponseEntity.ok(this.characterService.getAll(house));
    }


    /**
     *
     * @param id The character id to get a character by id
     * @return Character
     */
    @ApiOperation(value = "Get a character by Id")
    @GetMapping(value = "/characters/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Character> getById(@PathVariable Long id){
        Character character = this.characterService.getById(id);

        return ResponseEntity.ok(character);
    }

    /**
     *
     * @param characterDTO Data transfer object to create a Character
     * @return Created Character
     */
    @ApiOperation(value = "Create a character")
    @PostMapping(value = "/characters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Character> create(@RequestBody CharacterDTO characterDTO){
        Character character = this.characterService.create(characterDTO);

        return ResponseEntity
                .created(ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(character.getId())
                                .toUri())
                .body(character);
    }

    /**
     *
     * @param id The character id to update
     * @param characterDTO Data transfer object to update a Character
     * @return Updated Character
     */
    @ApiOperation(value = "Update a character")
    @PutMapping(value = "/characters/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Character> update(@PathVariable Long id, @RequestBody CharacterDTO characterDTO){
        Character character = this.characterService.update(id, characterDTO);
        return ResponseEntity.ok(character);
    }

    /**
     *
     * @param id to delete
     * @return No content
     */
    @ApiOperation(value = "Delete a character")
    @DeleteMapping(value = "/characters/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.characterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
