package com.example.easy_lang_dictionary.fragments_MainActivity2;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.easy_lang_dictionary.activities.MainActivity2;
import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.databinding.FragmentProfileBinding;
import com.example.easy_lang_dictionary.room.User;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private NavController navController;
    private TextView title, name, surname, email, password, language, level_of_language;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container,false);

        View view = binding.getRoot();

        User user = MainActivity2.user;

        title = MainActivity2.title;
        name = binding.textView6;
        surname = binding.textView17;
        email = binding.textView32;
        password = binding.textView30;
        language = binding.textView31;
        level_of_language = binding.textView33;
        loadUser(user);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        AppCompatImageButton editButton = binding.editButton;

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_fragment_profile_to_fragment_edit_profile);
            }
        });

        Button changeProfileButton = binding.button3;
        changeProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_fragment_profile_to_mainActivity);
            }
        });

        return view;
    }

    private void loadUser(User user) {
        title.setText(user.getProfile_name());
        name.setText(user.getName());
        surname.setText(user.getSurname());
        email.setText(user.getEmail());
        password.setText(user.getPassword());
        language.setText(user.getLanguage());
        level_of_language.setText(user.getLevel_of_language());
    }
}