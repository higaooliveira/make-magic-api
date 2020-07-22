package com.higor.make_magic.domain.dto;

import com.higor.make_magic.domain.entity.Character;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CharacterDTOTest {

    /**
     * Test conversion class from CharacterDTO to Character
     * @throws Exception
     */
    @Test
    public void case01 () throws Exception{
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setName("Harry Potter");
        characterDTO.setRole("student");
        characterDTO.setSchool("Hogwaarts");
        characterDTO.setPatronus("stag");
        characterDTO.setHouse("Gryffindor");

        Assert.assertNotNull(characterDTO.toEntity());
        Assert.assertTrue(characterDTO.toEntity() instanceof Character);
    }

}
