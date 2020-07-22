package com.higor.make_magic.domain.service.implementation;


import com.higor.make_magic.domain.dto.CharacterDTO;
import com.higor.make_magic.domain.dto.HouseDTO;
import com.higor.make_magic.domain.entity.Character;
import com.higor.make_magic.domain.repository.CharacterRepository;
import com.higor.make_magic.domain.repository.CharacterSpecification;
import com.higor.make_magic.domain.service.definition.CharacterServiceDefinition;
import com.higor.make_magic.domain.service.exception.InvalidHouseException;
import com.higor.make_magic.domain.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacterService implements CharacterServiceDefinition {

    @Autowired
    private CharacterRepository repository;

    @Autowired
    private PotterApiService apiService;

    /**
     *
     * @param house The house hash code to filter all characters by house (Can be null)
     * @param patronus The patronus name to filter all characters by patronus (Can be null)
     * @variable specification is an object that represents the filter in query
     * @return List of Characters
     */
    @Override
    public List<Character> getAll(String house, String patronus) {
        Character filter  = new Character(house, patronus);
        Specification<Character> specification = new CharacterSpecification(filter);
        return this.repository.findAll(specification);
    }

    /**
     *
     * @param id The character id to get a character by id
     * @return Character
     */
    @Override
    public Character getById(Long id) {
        Optional<Character> character = this.repository.findById(id);
        return character.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /**
     *
     * @param characterDTO Data transfer object to create a Character
     * @return Created Character
     */
    @Override
    public Character create(CharacterDTO characterDTO) {
        Character character = characterDTO.toEntity();
        character.setHouse(this.getHouseId(character.getHouse()));
        this.repository.save(character);
        return character;
    }

    /**
     *
     * @param id The character id to update
     * @param characterDTO Data transfer object to update a Character
     * @return Updated Character
     */
    @Override
    public Character update(Long id, CharacterDTO characterDTO) {
        try{
            Character character = this.getById(id);
            characterDTO.setHouse(this.getHouseId(characterDTO.getHouse()));
            character.updateData(characterDTO);
            return this.repository.save(character);
        }catch(EntityNotFoundException ex) {
            throw new ResourceNotFoundException(id);
        }
    }

    /**
     *
     * @param id to delete
     * @return No content
     */
    @Override
    public void delete(Long id) {
        try {
            this.repository.deleteById(id);
        }catch(EmptyResultDataAccessException ex){
            throw new ResourceNotFoundException(id);
        }
    }

    /**
     *
     * @param name The house name to match the returned data from API and get your id
     * @return HouseDTO
     */
    public String getHouseId(String name){
        return this.apiService.getAllHouses()
                .stream()
                .filter(it -> it.getName().toLowerCase().equals(name.toLowerCase()))
                .findFirst()
                .map(HouseDTO::getId)
                .orElseThrow(() -> new InvalidHouseException("You must be pass a valid house !"));

    }
}
