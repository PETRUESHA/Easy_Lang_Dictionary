package com.example.easy_lang_dictionary.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {
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
        TextView title = binding.title;
        bottomNavigationView = binding.bottomNavigationView;
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_profile:
                        title.setText(R.string.profile);
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
                    default: return true;
                }
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_profile);

        /* ProfileFragment profileFragment = new ProfileFragment();
        SearchFragment searchFragment = new SearchFragment();
        DictionaryFragment dictionaryFragment = new DictionaryFragment();                # For future
        ParserFragment parserFragment = new ParserFragment();
        TranslatorFragment translatorFragment = new TranslatorFragment();
         */
    }
}