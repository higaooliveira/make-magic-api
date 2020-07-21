package com.higor.make_magic.domain.service;

import com.higor.make_magic.domain.service.implementation.PotterApiService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PotterApiServiceTest {

    @Autowired
    private PotterApiService potterApiService;

    @Test
    public void case01(){
        Assert.assertNotNull(potterApiService.getAllHouses());
    }
}
