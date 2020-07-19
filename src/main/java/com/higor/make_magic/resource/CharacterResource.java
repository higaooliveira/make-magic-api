package com.higor.make_magic.resource;

import com.higor.make_magic.domain.dto.CharacterDTO;
import com.higor.make_magic.domain.entity.Character;
import com.higor.make_magic.domain.service.definition.CharacterServiceDefinition;
import com.higor.make_magic.util.HateoasHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/api")
public class CharacterResource {

    @Autowired
    private CharacterServiceDefinition characterService;

    @GetMapping("/characters")
    public ResponseEntity<List<Character>> getAll(@RequestParam(required = false) String house, @RequestParam(required = false) String patronus){
        return ResponseEntity.ok(this.characterService.getAll(house, patronus));
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<Character> getById(@PathVariable Long id){
        Character character = this.characterService.getById(id);

        return ResponseEntity.ok(character);
    }

    @PostMapping("/characters")
    public ResponseEntity<Character> create(@RequestBody CharacterDTO characterDTO){
        Character character = this.characterService.create(characterDTO);

        return ResponseEntity.created(HateoasHelper.buildUrlToGetRequest(character.getId())).body(character);
    }

    @PutMapping("/characters/{id}")
    public ResponseEntity<Character> update(@PathVariable Long id, @RequestBody CharacterDTO characterDTO){
        Character character = this.characterService.update(id, characterDTO);
        return ResponseEntity.ok(character);
    }

    @DeleteMapping("/characters/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.characterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
