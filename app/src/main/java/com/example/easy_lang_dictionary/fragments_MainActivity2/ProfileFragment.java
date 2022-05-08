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

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private NavController navController;

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
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        MainActivity2 mainActivity2 = (MainActivity2) getActivity();
        TextView title = mainActivity2.findViewById(R.id.title);
        AppCompatImageButton editButton = binding.editButton;
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText(R.string.edit_profile);
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
}