package com.example.demoretrofit.api;

import com.example.demoretrofit.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//    http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    ApiService API_SERVICE = new Retrofit.Builder().
            baseUrl("http://apilayer.net/")
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build().create(ApiService.class);

    @GET("api/live")
    Call<Product> callProducts(@Query("access_key") String access_key,
                               @Query("currencies") String currencies,
                               @Query("source") String source,
                               @Query("format") int format);

    // Cách 2
    @GET("api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1")
    Call<Product> callProducts1();

    @GET("api/live")
    Call<Product> callProducts3(@QueryMap Map<String, String> options);

    //Đối với domain k có params sau ?
    @GET("api/live")
    Call<Product> callProducts2();

    //Đối với domain động
    @GET("api/group/{1}/users")
    Call<Product> getListUserFromGroup(@Path("id") int groupID);

    //Đối với domain động và có params
    @GET("api/group/{1}/users")
    Call<Product> getListUserFromGroup2(@Path("id") int groupID, @Query("access_key") String access_key,
                                                                @Query("currencies") String currencies,
                                                                @Query("source") String source,
                                                                @Query("format") int format);
}
