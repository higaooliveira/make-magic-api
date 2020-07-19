package com.higor.make_magic.domain.service.definition;

import com.higor.make_magic.domain.dto.HouseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface RestClientDefinition {

    @GET("v1/houses?")
    public Call<List<HouseDTO>> getAllHouses(@Query("key") String key);
}
