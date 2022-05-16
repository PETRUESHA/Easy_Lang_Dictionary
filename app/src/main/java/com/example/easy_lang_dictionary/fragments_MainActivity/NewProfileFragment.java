package com.example.easy_lang_dictionary.fragments_MainActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.Word_list;
import com.example.easy_lang_dictionary.activities.MainActivity;
import com.example.easy_lang_dictionary.activities.MainActivity2;
import com.example.easy_lang_dictionary.databinding.FragmentNewProfileBinding;
import com.example.easy_lang_dictionary.room.App;
import com.example.easy_lang_dictionary.room.Converters;
import com.example.easy_lang_dictionary.room.User;

import java.util.ArrayList;
import java.util.Objects;

public class NewProfileFragment extends Fragment {
    private FragmentNewProfileBinding binding;
    private NavController navController;
    private final String[] languages = {"English", "French", "Spanish", "German"};
    private Spinner spinner;
    private EditText title, editName, editSurname, editEmail, editPassword;
    private String language;
    private RadioGroup radioGroup;
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

        spinner = binding.spinner;
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), R.layout.spinner, languages);
        adapter.setDropDownViewResource(R.layout.spinner_drop_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                language = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        title = MainActivity.title;
        editName = binding.editTextTextName;
        editSurname = binding.editTextTextSurname;
        editEmail = binding.editTextTextEmail;
        editPassword = binding.editTextTextPassword;
        radioGroup = binding.radioGroup;
        binding.radioButton1.setChecked(true);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        Button button = binding.confirmButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (createUser()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            App.getInstance(getContext()).getDatabase().userDao().insert(user);
                        }
                    }).start();
                    Intent intent = new Intent(getContext(), MainActivity2.class);
                    intent.putExtra("user", user);
                    Objects.requireNonNull(getContext()).startActivity(intent);
                }
            }
        });


        return view;
    }

    @SuppressLint("NonConstantResourceId")
    private Boolean createUser() {
        String profile_name = title.getText().toString().trim();
        String name = editName.getText().toString().trim();
        String surname = editSurname.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String level_of_language;
        int radioButtonId = radioGroup.getCheckedRadioButtonId();

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

        RadioButton radioButton = Objects.requireNonNull(getView()).findViewById(radioButtonId);
        level_of_language = radioButton.getText().toString().trim();

        if (pass) {
            user.setProfile_name(profile_name);
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPassword(password);
            user.setLanguage(language);
            user.setLevel_of_language(level_of_language);
            ArrayList<Word_list> wl = new ArrayList<>();
            ArrayList<String> w = new ArrayList<>();
            w.add("world");
            w.add("good");
            ArrayList<String> t = new ArrayList<>();
            t.add("мир");
            t.add("хороший");
            Word_list f = new Word_list("first");
            f.setWords(w);
            f.setTranslates(t);
            wl.add(f);
            Word_list s = new Word_list("second");
            s.setWords(w);
            s.setTranslates(t);
            wl.add(s);
            user.setWord_lists(Converters.fromArrayList(wl));
            return true;
        }


        else {
            return false;
        }
    }
}