package com.higor.make_magic.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higor.make_magic.domain.dto.CharacterDTO;
import com.higor.make_magic.domain.entity.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
public class CharacterResourceTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CharacterResource characterResource;

    @BeforeEach
    public void setUp() throws Exception {
        this.case03();
    }

    @Test
    public void case01() throws Exception{
        List<Character> expectedReturnList = new ArrayList<>();
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/characters")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(expectedReturnList))
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void case02() throws Exception {
        Character expectedReturn = new Character();
        Long id = 1L;
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/characters/" + id)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(expectedReturn))
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void case03() throws Exception{
        CharacterDTO mockData = new CharacterDTO();
        mockData.setName("Mock Teste");
        mockData.setRole("student");
        mockData.setHouse("Hufflepuff");
        mockData.setSchool("Hogwarts School of Witchcraft and Wizardry");
        mockData.setPatronus("stag");

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/characters")
                        .contentType("application/json").content(objectMapper.writeValueAsString(mockData))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void case04() throws Exception {
        CharacterDTO mockData = new CharacterDTO();
        mockData.setName("Atualização Mock Teste");
        mockData.setRole("student");
        mockData.setHouse("Hufflepuff");
        mockData.setSchool("Hogwarts School of Witchcraft and Wizardry");
        mockData.setPatronus("stag");

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/api/characters/1")
                        .contentType("application/json").content(objectMapper.writeValueAsString(mockData))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void case05() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/characters/1")
                        .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
