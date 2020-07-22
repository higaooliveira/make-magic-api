package com.higor.make_magic.domain.service.implementation;

import com.higor.make_magic.domain.dto.HouseDTO;
import com.higor.make_magic.domain.service.definition.PotterApiServiceDefinition;
import com.higor.make_magic.domain.service.definition.RestClientDefinition;
import com.higor.make_magic.domain.service.exception.ThirdPartyApiException;
import okhttp3.OkHttpClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import java.io.IOException;
import java.util.List;

@Service
public class PotterApiService implements PotterApiServiceDefinition {

    private static final String BASE_URL = "https://www.potterapi.com/";
    private static final String KEY = "$2a$10$hG5SxuRokrOSAn2TIa2FtO7/ehyQcasVotRkkB5EjfcdNo343xgTi";

    private RestClientDefinition restClient;

    public PotterApiService() {

        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new RequestInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        this.restClient = retrofit.create(RestClientDefinition.class);
    }

    /**
     * Perform a Http Get call to PotterAPI
     * @return A List of HouseDTO from PotterAPI
     */
    @Override
    public List<HouseDTO> getAllHouses() {
        try {
            Call<List<HouseDTO>> callSync = this.restClient.getAllHouses(KEY);
            Response<List<HouseDTO>> response = callSync.execute();

            return response.body();
        }catch(IOException ex){
            throw new ThirdPartyApiException("An error occurred while trying to process your request");
        }
    }
}
