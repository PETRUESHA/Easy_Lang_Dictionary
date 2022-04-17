package com.example.easy_lang_dictionary;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.easy_lang_dictionary.databinding.ActivityMain2Binding;
import com.example.easy_lang_dictionary.fragments_MainActivity2.DictionaryFragment;
import com.example.easy_lang_dictionary.fragments_MainActivity2.ParserFragment;
import com.example.easy_lang_dictionary.fragments_MainActivity2.ProfileFragment;
import com.example.easy_lang_dictionary.fragments_MainActivity2.SearchFragment;
import com.example.easy_lang_dictionary.fragments_MainActivity2.TranslatorFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    private ActivityMain2Binding binding;
    private NavController navController;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        bottomNavigationView = binding.bottomNavigationView;
        // bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_profile);

        ProfileFragment profileFragment = new ProfileFragment();
        SearchFragment searchFragment = new SearchFragment();
        DictionaryFragment dictionaryFragment = new DictionaryFragment();
        ParserFragment parserFragment = new ParserFragment();
        TranslatorFragment translatorFragment = new TranslatorFragment();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_profile:
                navController.navigate(R.id.fragment_profile);
                return true;
            case R.id.navigation_search:
                navController.navigate(R.id.fragment_search);
                return true;
            case R.id.navigation_dictionary:
                navController.navigate(R.id.fragment_dictionary);
                return true;
            case R.id.navigation_parser:
                navController.navigate(R.id.fragment_parser);
                return true;
            case R.id.navigation_translator:
                navController.navigate(R.id.fragment_translator);
                return true;
        }
     return false;
    }
}