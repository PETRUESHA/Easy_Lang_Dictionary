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
import com.example.easy_lang_dictionary.databinding.ActivityMain2Binding;
import com.example.easy_lang_dictionary.fragments_MainActivity2.DictionaryFragment;
import com.example.easy_lang_dictionary.fragments_MainActivity2.ParserFragment;
import com.example.easy_lang_dictionary.fragments_MainActivity2.ProfileFragment;
import com.example.easy_lang_dictionary.fragments_MainActivity2.SearchFragment;
import com.example.easy_lang_dictionary.fragments_MainActivity2.TranslatorFragment;
import com.example.easy_lang_dictionary.retrofit.Api;
import com.example.easy_lang_dictionary.room.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;
    private NavController navController;
    private BottomNavigationView bottomNavigationView;
    public static EditText title;
    public static User user;
    private static final String BASE_URL = "https://developers.lingvolive.com/";
    private static final String API_KEY = "ZmYzMzZiYTYtZmM5MC00NjJkLWIwYmYtMjE4NjJjYzRkNTE0OjE1YWMxYTg5MzhlMDQyZjk4NDI5Y2U1MDAxOGYwMGI1";
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        /*OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization", "Basic " + API_KEY)
                        .method(original.method(), original.body())
                        .build();
                Response response = chain.proceed(request);
                Log.d("RRR", response.toString());
                return response;
            }
        });*/

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
        Api api = retrofit.create(Api.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("RRR", api.getToken().execute().body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        user = (User) getIntent().getSerializableExtra("user");

        title = binding.title;
        bottomNavigationView = binding.bottomNavigationView;
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_profile:
                        navController.navigate(R.id.fragment_profile);
                        return true;
                    case R.id.navigation_search:
                        title.setText(R.string.search);
                        navController.navigate(R.id.fragment_search);
                        return true;
                    case R.id.navigation_dictionary:
                        title.setText(R.string.dictionary);
                        navController.navigate(R.id.fragment_dictionary);
                        return true;
                    case R.id.navigation_parser:
                        title.setText(R.string.parser);
                        navController.navigate(R.id.fragment_parser);
                        return true;
                    case R.id.navigation_translator:
                        title.setText(R.string.translator);
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