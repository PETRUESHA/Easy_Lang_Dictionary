package com.example.easy_lang_dictionary.fragments_MainActivity2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.easy_lang_dictionary.activities.MainActivity2;
import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.databinding.FragmentEditProfileBinding;
import com.example.easy_lang_dictionary.room.App;
import com.example.easy_lang_dictionary.room.User;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class EditProfileFragment extends Fragment {
    private FragmentEditProfileBinding binding;
    private NavController navController;
    private EditText title, name, surname, email, password, language;


    public EditProfileFragment() {
    }
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        User user = MainActivity2.user;

        title = MainActivity2.title;
        name = binding.editTextTextName;
        surname = binding.editTextTextSurname;
        email = binding.editTextTextEmail;
        password = binding.editTextTextPassword;
        language = binding.editTextTextLanguage;
        loadUser(user);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        Button button = binding.confirmButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateUser(user)) {
                    title.setFocusable(false);
                    App.getInstance(getContext())
                            .getDatabase().userDao()
                            .update(user);                                                          // КАК СДЕЛАТЬ ЧЕРЕЗ rxjava!!!!
                    navController.navigate(R.id.action_fragment_edit_profile_to_fragment_profile);
                }
            }
        });

        return view;
    }

    private void loadUser(User user) {
        title.setText(user.getProfile_name());
        title.setFocusableInTouchMode(true);
        name.setText(user.getName());
        surname.setText(user.getSurname());
        email.setText(user.getEmail());
        password.setText(user.getPassword());
        language.setText(user.getLanguage());
    }

    private Boolean updateUser(User user) {
        String user_profile = title.getText().toString().trim();
        String user_name = name.getText().toString().trim();
        String user_surname = surname.getText().toString().trim();
        String user_email = email.getText().toString().trim();
        String user_password = password.getText().toString().trim();
        String user_language = language.getText().toString().trim();
        Boolean pass = true;

        if (user_profile.isEmpty()) {
            title.setError(getString(R.string.required));
            title.requestFocus();
            pass = false;
        }
        if (user_name.isEmpty()) {
            name.setError(getString(R.string.required));
            name.requestFocus();
            pass = false;
        }
        if (user_surname.isEmpty()) {
            surname.setError(getString(R.string.required));
            surname.requestFocus();
            pass = false;
        }
        if (user_email.isEmpty()) {
            email.setError(getString(R.string.required));
            email.requestFocus();
            pass =  false;
        }
        if (user_password.isEmpty()) {
            password.setError(getString(R.string.required));
            password.requestFocus();
            pass = false;
        }
        if (user_language.isEmpty()) {
            language.setError(getString(R.string.required));
            language.requestFocus();
            pass = false;
        }
        if (pass) {
            user.setProfile_name(user_profile);
            user.setName(user_name);
            user.setSurname(user_surname);
            user.setEmail(user_email);
            user.setPassword(user_password);
            user.setLanguage(user_language);
            return true;
        }
        else {
            return false;
        }
    }
}