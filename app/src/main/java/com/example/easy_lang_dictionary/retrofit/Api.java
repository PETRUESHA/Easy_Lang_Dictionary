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

    @Headers("Authorization: Bearer ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmxlSEFpT2pFMk5USTJNREl6TVRRc0lrMXZaR1ZzSWpwN0lrTm9ZWEpoWTNSbGNuTlFaWEpFWVhraU9qVXdNREF3TENKVmMyVnlTV1FpT2pZd01qTXNJbFZ1YVhGMVpVbGtJam9pWm1Zek16WmlZVFl0Wm1NNU1DMDBOakprTFdJd1ltWXRNakU0TmpKall6UmtOVEUwSW4xOS5xVGVDcTZmcTN3SnFmNWQ5cXhtcUk1YlJPc056aUpkSWh3NklRZVVTdi00")
    @GET("api/v1/Minicard")
    Call<JsonObject> getTranslate(
                                  @Query("text") String text,
                                  @Query("srcLang") Integer srcLang,
                                  @Query("dstLang") Integer dstLang);
}
