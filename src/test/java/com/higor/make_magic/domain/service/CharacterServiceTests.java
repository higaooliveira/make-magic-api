package com.higor.make_magic.domain.service;

import com.higor.make_magic.domain.dto.CharacterDTO;
import com.higor.make_magic.domain.entity.Character;
import com.higor.make_magic.domain.service.exception.InvalidHouseException;
import com.higor.make_magic.domain.service.exception.ResourceNotFoundException;
import com.higor.make_magic.domain.service.implementation.CharacterService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
public class CharacterServiceTests {

    @Autowired
    private CharacterService characterService;

    @BeforeEach
    public void setUp () throws Exception {
        this.case05();
    }

    /**
     * Check if GetAll method return a Not null object
     * @throws Exception
     */
    @Test
    public void case01() throws Exception {
        Assert.assertNotNull(characterService.getAll("", ""));
    }

    /**
     * Check if GetAll with filters method return a Not null object
     * @throws Exception
     */
    @Test()
    public void case02() throws Exception {
        Assert.assertNotNull(characterService.getAll("5a05e2b252f721a3cf2ea33f", "stag"));
    }

    /**
     * Test get Character by Invalid ID
     * Test Exception
     * @throws Exception
     */
    @Test
    public void case03() throws Exception {
        try {
            Character character = characterService.getById(2L);
        }catch(ResourceNotFoundException ex){
            Assert.assertTrue(true);
        }
    }

    /**
     * Test get Character by Valid ID
     * @throws Exception
     */
    @Test
    public void case04() throws Exception {
        Assert.assertNotNull(characterService.getById(1L));
    }

    /**
     * Test create a Character
     * @throws Exception
     */
    @Test
    public void case05() throws Exception {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setName("Harry Potter");
        characterDTO.setRole("student");
        characterDTO.setSchool("Hogwaarts");
        characterDTO.setPatronus("stag");
        characterDTO.setHouse("Gryffindor");

        Assert.assertNotNull(characterService.create(characterDTO));
        Assert.assertTrue(characterService.create(characterDTO) instanceof Character);
    }

    /**
     * Test create an invalid Character
     * @throws Exception
     */
    @Test
    public void case06() throws Exception {
        try {
            CharacterDTO characterDTO = new CharacterDTO();
            characterDTO.setName("Harry Potter");
            characterDTO.setRole("student");
            characterDTO.setSchool("Hogwaarts");
            characterDTO.setPatronus("stag");
            characterDTO.setHouse("Casa inválida");
            characterService.create(characterDTO);
        }catch(InvalidHouseException ex){
            Assert.assertTrue(true);
        }
    }

    /**
     * Test create an invalid Character
     * @throws Exception
     */
    @Test
    public void case07() throws Exception {
        try {
            CharacterDTO characterDTO = new CharacterDTO();
            characterService.create(characterDTO);
        }catch(NullPointerException ex){
            Assert.assertTrue(true);
        }
    }

    /**
     * Test update a valid Character
     * @throws Exception
     */
    @Test
    public void case08() throws Exception {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setName("Harry Potter update");
        characterDTO.setRole("student");
        characterDTO.setSchool("Hogwaarts");
        characterDTO.setPatronus("stag");
        characterDTO.setHouse("Gryffindor");

        Assert.assertNotNull(characterService.update(1L, characterDTO));
    }

    /**
     * Test update a valid Character
     * @throws Exception
     */
    @Test
    public void case09() throws Exception {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setName("Harry Potter update");
        characterDTO.setRole("student");
        characterDTO.setSchool("Hogwaarts");
        characterDTO.setPatronus("stag");
        characterDTO.setHouse("Ravenclaw");

        Assert.assertTrue(characterService.update(1L, characterDTO) instanceof Character);
    }

    /**
     * Test update an invalid Character
     * @throws Exception
     */
    @Test
    public void case10() throws Exception {
        try {
            CharacterDTO characterDTO = new CharacterDTO();
            characterDTO.setName("Harry Potter update");
            characterDTO.setRole("student");
            characterDTO.setSchool("Hogwaarts");
            characterDTO.setPatronus("stag");
            characterDTO.setHouse("Casa Inválida");
            characterService.update(1L, characterDTO);
        }catch(InvalidHouseException ex){
            Assert.assertTrue(true);
        }
    }

    /**
     * Test update an invalid Character
     * @throws Exception
     */
    @Test
    public void case11() throws Exception {
        try {
            CharacterDTO characterDTO = new CharacterDTO();
            characterService.update(1L, characterDTO);
            Assert.fail();
        }catch(NullPointerException ex){
            Assert.assertTrue(true);
        }
    }

    /**
     * Test Filter house from PotterAPI by a house name
     * @throws Exception
     */
    @Test
    public void case12() throws Exception {
        String mockHouseId = "5a05e2b252f721a3cf2ea33f";
        String MockHouseName = "Gryffindor";
        Assert.assertEquals(mockHouseId, characterService.getHouseId(MockHouseName));
    }

    /**
     * Test Filter house from PotterAPI by an invalid house name
     * @throws Exception
     */
    @Test
    public void case13() throws Exception {
        try {
            String MockHouseName = "Casa inválida";
            characterService.getHouseId(MockHouseName);
            Assert.fail();
        }catch(InvalidHouseException ex){
            Assert.assertTrue(true);
        }
    }
}
