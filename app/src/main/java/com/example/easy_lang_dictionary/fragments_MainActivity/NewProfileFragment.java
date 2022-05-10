package com.example.easy_lang_dictionary.fragments_MainActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.activities.MainActivity;
import com.example.easy_lang_dictionary.activities.MainActivity2;
import com.example.easy_lang_dictionary.databinding.FragmentNewProfileBinding;
import com.example.easy_lang_dictionary.room.App;
import com.example.easy_lang_dictionary.room.User;

import java.lang.annotation.Native;
import java.util.Objects;

public class NewProfileFragment extends Fragment {
    private FragmentNewProfileBinding binding;
    private NavController navController;
    private EditText title, editName, editSurname, editEmail, editPassword;
    private User user;

    public NewProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        user = new User();

        title = MainActivity.title;
        editName = binding.editTextTextName;
        editSurname = binding.editTextTextSurname;
        editEmail = binding.editTextTextEmail;
        editPassword = binding.editTextTextPassword;

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        Button button = binding.confirmButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (createUser()) {
                    App.getInstance(getContext()).getDatabase().userDao().insert(user);         // КАК СДЕЛАТЬ ЧЕРЕЗ rxjava!!!!!
                    Intent intent = new Intent(getContext(), MainActivity2.class);
                    intent.putExtra("user", user);
                    Objects.requireNonNull(getContext()).startActivity(intent);
                }
            }
        });


        return view;
    }

    private Boolean createUser() {
        String profile_name = title.getText().toString().trim();
        String name = editName.getText().toString().trim();
        String surname = editSurname.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String language = "English";
        String level_of_language = "B2";

        boolean pass = true;

        if (profile_name.isEmpty()) {
            title.setError(getString(R.string.required));
            title.requestFocus();
            pass = false;
        }
        if (name.isEmpty()) {
            editName.setError(getString(R.string.required));
            editName.requestFocus();
            pass = false;
        }
        if (surname.isEmpty()) {
            editSurname.setError(getString(R.string.required));
            editSurname.requestFocus();
            pass = false;
        }
        if (email.isEmpty()) {
            editEmail.setError(getString(R.string.required));
            editEmail.requestFocus();
            pass =  false;
        }
        if (password.isEmpty()) {
            editPassword.setError(getString(R.string.required));
            editPassword.requestFocus();
            pass = false;
        }
        if (pass) {
            user.setProfile_name(profile_name);
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPassword(password);
            user.setLanguage(language);
            user.setLevel_of_language(level_of_language);
            return true;
        }
        else {
            return false;
        }
    }
}