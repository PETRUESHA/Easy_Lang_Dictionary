package com.example.easy_lang_dictionary.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.adapters.DictionaryAdapter;
import com.example.easy_lang_dictionary.databinding.ActivityMain2Binding;
import com.example.easy_lang_dictionary.fragments_MainActivity2.DictionaryFragment;
import com.example.easy_lang_dictionary.fragments_MainActivity2.ParserFragment;
import com.example.easy_lang_dictionary.fragments_MainActivity2.ProfileFragment;
import com.example.easy_lang_dictionary.fragments_MainActivity2.SearchFragment;
import com.example.easy_lang_dictionary.fragments_MainActivity2.TranslatorFragment;
import com.example.easy_lang_dictionary.retrofit.Api;
import com.example.easy_lang_dictionary.retrofit.Translate;
import com.example.easy_lang_dictionary.retrofit.Translation;
import com.example.easy_lang_dictionary.room.Converters;
import com.example.easy_lang_dictionary.room.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;
    public static NavController navController;
    public static BottomNavigationView bottomNavigationView;
    public static EditText title;
    public static User user;
    public static final String BASE_URL = "https://developers.lingvolive.com/";
    private static final String API_KEY = "ZmYzMzZiYTYtZmM5MC00NjJkLWIwYmYtMjE4NjJjYzRkNTE0OjE1YWMxYTg5MzhlMDQyZjk4NDI5Y2U1MDAxOGYwMGI1";
    public static String token_key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).baseUrl(BASE_URL).build();
        Api api = retrofit.create(Api.class);


        Call<String> token = api.getToken();

        token.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    token_key = response.body();
                    Log.d("TTT", token_key);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });

        user = (User) getIntent().getSerializableExtra("user");

        title = binding.title;
        bottomNavigationView = binding.bottomNavigationView;
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_profile:
                        title.setFocusable(false);
                        navController.navigate(R.id.fragment_profile);
                        return true;
                    case R.id.navigation_search:
                        title.setText(R.string.search);
                        title.setFocusable(false);
                        navController.navigate(R.id.fragment_search);
                        return true;
                    case R.id.navigation_dictionary:
                        title.setText(R.string.dictionary);
                        title.setFocusable(false);
                        navController.navigate(R.id.fragment_dictionary);
                        return true;
                    case R.id.navigation_parser:
                        title.setText(R.string.parser);
                        title.setFocusable(false);
                        navController.navigate(R.id.fragment_parser);
                        return true;
                    case R.id.navigation_translator:
                        title.setText(R.string.translator);
                        title.setFocusable(false);
                        navController.navigate(R.id.fragment_translator);
                        return true;
                    default:
                        return true;
                }
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_profile);


    }
}