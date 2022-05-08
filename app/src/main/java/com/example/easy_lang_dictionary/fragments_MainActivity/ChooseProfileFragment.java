package com.example.easy_lang_dictionary.fragments_MainActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.easy_lang_dictionary.activities.MainActivity;
import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.adapters.ChooseProfileAdapter;
import com.example.easy_lang_dictionary.databinding.FragmentChooseProfileBinding;


public class ChooseProfileFragment extends Fragment {

    private FragmentChooseProfileBinding binding;
    private NavController navController;

    public ChooseProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChooseProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initViews();
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        MainActivity mainActivity = (MainActivity) getActivity();
        EditText title = mainActivity.findViewById(R.id.editTextTitle);
        Button navigation_to_newProf = binding.button;
        navigation_to_newProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText(R.string.profile_1);
                title.setFocusableInTouchMode(true);
                navController.navigate(R.id.action_chooseProfile_to_newProfile);
            }
        });
        return view;
    }

    public void initViews() {
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ChooseProfileAdapter());
    }
}