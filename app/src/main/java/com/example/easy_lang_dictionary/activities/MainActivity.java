package com.example.easy_lang_dictionary.activities;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
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
        User user = new User();
        user.setId(1);
        user.setName("Petr");
        user.setSurname("Zarenkov");
        user.setEmail("pzarenkov.99@gmail.com");
        user.setPassword("DeafaultPass");
        user.setLanguage("English");
        user.setLevel_of_language("B2");

        userDao.insert(user);

        List<User> users = userDao.getAll();
        Log.d("RRR", users.get(0).getName());
    }
}