package com.example.easy_lang_dictionary.fragments_MainActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easy_lang_dictionary.R;


public class EditProfileFragment extends Fragment {


    public EditProfileFragment() {

    }

    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        return fragment;
    }
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
    }
}