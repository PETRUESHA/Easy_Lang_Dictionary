package com.example.easy_lang_dictionary.fragments_MainActivity2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.databinding.FragmentChooseProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentChooseProfileBinding binding;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChooseProfileBinding.inflate(getLayoutInflater());
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}