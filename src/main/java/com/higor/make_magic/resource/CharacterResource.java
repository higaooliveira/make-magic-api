package com.higor.make_magic.resource;

import com.higor.make_magic.domain.dto.CharacterDTO;
import com.higor.make_magic.domain.entity.Character;
import com.higor.make_magic.domain.service.definition.CharacterServiceDefinition;
import com.higor.make_magic.util.HateoasHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
@Api(value = "Make Magic")
public class CharacterResource {

    @Autowired
    private CharacterServiceDefinition characterService;

    @ApiOperation(value = "List All registered characters")
    @GetMapping(value = "/characters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Character>> getAll(@RequestParam(required = false) String house, @RequestParam(required = false) String patronus){
        return ResponseEntity.ok(this.characterService.getAll(house, patronus));
    }

    @ApiOperation(value = "Get a character by Id")
    @GetMapping(value = "/characters/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Character> getById(@PathVariable Long id){
        Character character = this.characterService.getById(id);

        return ResponseEntity.ok(character);
    }

    @ApiOperation(value = "Create a character")
    @PostMapping(value = "/characters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Character> create(@RequestBody CharacterDTO characterDTO){
        Character character = this.characterService.create(characterDTO);

        return ResponseEntity.created(HateoasHelper.buildUrlToGetRequest(character.getId())).body(character);
    }

    @ApiOperation(value = "Update a character")
    @PutMapping(value = "/characters/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Character> update(@PathVariable Long id, @RequestBody CharacterDTO characterDTO){
        Character character = this.characterService.update(id, characterDTO);
        return ResponseEntity.ok(character);
    }

    @ApiOperation(value = "Delete a character")
    @DeleteMapping(value = "/characters/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.characterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
