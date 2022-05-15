package com.example.easy_lang_dictionary.fragments_MainActivity2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.Word_list;
import com.example.easy_lang_dictionary.activities.MainActivity2;
import com.example.easy_lang_dictionary.databinding.FragmentWordListBinding;

import java.util.ArrayList;


public class WordListFragment extends Fragment {
    private FragmentWordListBinding binding;
    public static Word_list word_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWordListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        MainActivity2.title.setText(word_list.getName());
        return view;
    }
}