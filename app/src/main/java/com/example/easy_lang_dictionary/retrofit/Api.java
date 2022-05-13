package com.example.easy_lang_dictionary.retrofit;

// https://developers.lingvolive.com/

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    @Headers("Authorization: Basic ZmYzMzZiYTYtZmM5MC00NjJkLWIwYmYtMjE4NjJjYzRkNTE0OjE1YWMxYTg5MzhlMDQyZjk4NDI5Y2U1MDAxOGYwMGI1")
    @POST("/api/v1.1/authenticate")
    Call<String> getToken();
}
