package com.example.easy_lang_dictionary.activities;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.databinding.ActivityMainBinding;
import com.example.easy_lang_dictionary.room.App;
import com.example.easy_lang_dictionary.room.Database;
import com.example.easy_lang_dictionary.room.User;
import com.example.easy_lang_dictionary.room.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;
    private View view;
    public static EditText title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        title = binding.editTextTitle;
        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }
    }
}