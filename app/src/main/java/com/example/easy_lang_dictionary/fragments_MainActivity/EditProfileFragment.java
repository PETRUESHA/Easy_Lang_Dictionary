package com.example.easy_lang_dictionary.fragments_MainActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.databinding.FragmentEditProfileBinding;


public class EditProfileFragment extends Fragment {
    private FragmentEditProfileBinding binding;
    private NavController navController;


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
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        Button button = binding.confirmButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_fragment_edit_profile_to_fragment_profile);
            }
        });
        return view;
    }
}