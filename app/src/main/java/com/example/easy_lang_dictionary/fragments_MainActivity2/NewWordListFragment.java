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

public class NewWordListFragment extends Fragment {
    private FragmentWordListBinding binding;


    public NewWordListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWordListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        MainActivity2.title.setText(R.string.word_list_1);
        MainActivity2.title.setFocusableInTouchMode(true);

        return view;
    }
}