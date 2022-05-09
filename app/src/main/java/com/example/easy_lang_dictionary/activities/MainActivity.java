package com.example.easy_lang_dictionary.activities;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }
        Database db = App.getInstance(getApplicationContext()).getDatabase();
        UserDao userDao = db.userDao();
        User user = new User(1, "Profile_1", "Petr", "Zarenkov", "pzarenkov.99@gmail.com", "DeafaultPass", "English", "B2");
        userDao.insert(user);
        User user1 = new User(2, "Profile_2", "Nastya", "Zarenkova", "azarenkova.99@gmail.com", "DeafaultPass", "German", "B1");
        userDao.insert(user1);
    }
}