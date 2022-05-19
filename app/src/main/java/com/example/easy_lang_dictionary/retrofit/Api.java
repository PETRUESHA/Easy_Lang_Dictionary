package com.example.easy_lang_dictionary.retrofit;

// https://developers.lingvolive.com/

import static com.example.easy_lang_dictionary.activities.MainActivity2.token_key;

import com.example.easy_lang_dictionary.activities.MainActivity2;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @Headers("Authorization: Basic ZmYzMzZiYTYtZmM5MC00NjJkLWIwYmYtMjE4NjJjYzRkNTE0OjE1YWMxYTg5MzhlMDQyZjk4NDI5Y2U1MDAxOGYwMGI1")
    @POST("/api/v1.1/authenticate")
    Call<String> getToken();

    @GET("api/v1/Minicard")
    Call<JsonObject> getTranslate(
            @Header("Authorization") String token,
            @Query("text") String text,
            @Query("srcLang") Integer srcLang,
            @Query("dstLang") Integer dstLang);

    @GET("api/v1/Sound")
    Call<String> getSound(
            @Header("Authorization") String token,
            @Query("dictionaryName") String dictName,
            @Query("fileName") String fileName);

    @GET("api/v1/WordList")
    Call<JsonObject> getWordList(
            @Header("Authorization") String token,
            @Query("prefix") String prefix,
            @Query("srcLang") int srcLang,
            @Query("dstLang") int dstLang,
            @Query("pageSize") int pageSize);
}
