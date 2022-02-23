package com.example.easy_lang_dictionary.fragments_MainActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.adapters.ChooseProfileAdapter;
import com.example.easy_lang_dictionary.databinding.FragmentChooseProfileBinding;

import java.util.ArrayList;


public class ChooseProfile extends Fragment {

    private FragmentChooseProfileBinding binding;

    public ChooseProfile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChooseProfileBinding.inflate(getLayoutInflater());
        initViews();




        return inflater.inflate(R.layout.fragment_choose_profile, container, false);
    }

    public void initViews() {
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ChooseProfileAdapter());
    }
}