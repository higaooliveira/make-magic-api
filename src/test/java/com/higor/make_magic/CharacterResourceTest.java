package com.higor.make_magic;


import com.higor.make_magic.resource.CharacterResource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CharacterResourceTest extends MakeMagicApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private CharacterResource characterResource;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(characterResource).build();
    }

    @Test
    public void testGetAllCharacters() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/characters")).andExpect(MockMvcResultMatchers.status().isOk());
    }
