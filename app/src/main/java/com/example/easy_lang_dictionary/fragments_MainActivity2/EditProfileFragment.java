package com.example.easy_lang_dictionary.fragments_MainActivity2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.easy_lang_dictionary.activities.MainActivity;
import com.example.easy_lang_dictionary.activities.MainActivity2;
import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.databinding.FragmentEditProfileBinding;
import com.example.easy_lang_dictionary.room.App;
import com.example.easy_lang_dictionary.room.User;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class EditProfileFragment extends Fragment {
    private FragmentEditProfileBinding binding;
    private NavController navController;
    private EditText title, name, surname, email, password;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6;
    private String level_of_language, user_language;
    private final String[] languages = {"English", "French", "Spanish", "German"};
    private Spinner spinner;


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

        spinner = binding.spinner2;
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), R.layout.spinner, languages);
        adapter.setDropDownViewResource(R.layout.spinner_drop_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(Arrays.asList(languages).indexOf(user.getLanguage()));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user_language = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        title = MainActivity2.title;
        name = binding.editTextTextName;
        surname = binding.editTextTextSurname;
        email = binding.editTextTextEmail;
        password = binding.editTextTextPassword;
        radioButton1 = binding.radioButton1;
        radioButton2 = binding.radioButton2;
        radioButton3 = binding.radioButton3;
        radioButton4 = binding.radioButton4;
        radioButton5 = binding.radioButton5;
        radioButton6 = binding.radioButton6;
        loadUser(user);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        Button button = binding.confirmButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateUser(user)) {
                    title.setFocusable(false);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            App.getInstance(getContext())
                                    .getDatabase().userDao()
                                    .update(user);
                        }
                    }).start();
                    MainActivity2.bottomNavigationView.setVisibility(View.VISIBLE);
                    navController.navigate(R.id.action_fragment_edit_profile_to_fragment_profile);
                }
            }
        });

        ImageButton deleteButton = binding.deleteButton;
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        App.getInstance(getContext()).getDatabase().userDao().delete(user);
                    }
                }).start();
                navController.navigate(R.id.action_fragment_edit_profile_to_mainActivity);
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
        level_of_language = user.getLevel_of_language();
        switch (level_of_language) {
            case "A1":
                radioButton1.setChecked(true);
                break;
            case "A2":
                radioButton2.setChecked(true);
                break;
            case "B1":
                radioButton3.setChecked(true);
                break;
            case "B2":
                radioButton4.setChecked(true);
                break;
            case "C1":
                radioButton5.setChecked(true);
                break;
            case "C2":
                radioButton6.setChecked(true);
                break;
        }
    }

    private Boolean updateUser(User user) {
        String user_profile = title.getText().toString().trim();
        String user_name = name.getText().toString().trim();
        String user_surname = surname.getText().toString().trim();
        String user_email = email.getText().toString().trim();
        String user_password = password.getText().toString().trim();
        String user_level_of_language;
        RadioGroup radioGroup = binding.radioGroup;
        int radioButtonId = radioGroup.getCheckedRadioButtonId();

        boolean pass = true;

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

        RadioButton radioButton = Objects.requireNonNull(getView()).findViewById(radioButtonId);
        user_level_of_language = radioButton.getText().toString().trim();

        if (pass) {
            user.setProfile_name(user_profile);
            user.setName(user_name);
            user.setSurname(user_surname);
            user.setEmail(user_email);
            user.setPassword(user_password);
            user.setLanguage(user_language);
            user.setLevel_of_language(user_level_of_language);
            return true;
        }
        else {
            return false;
        }
    }
}