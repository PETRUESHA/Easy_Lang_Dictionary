package com.example.easy_lang_dictionary.fragments_MainActivity2;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.Word_list;
import com.example.easy_lang_dictionary.activities.MainActivity2;
import com.example.easy_lang_dictionary.adapters.ChooseProfileAdapter;
import com.example.easy_lang_dictionary.adapters.DictionaryAdapter;
import com.example.easy_lang_dictionary.databinding.FragmentChooseProfileBinding;
import com.example.easy_lang_dictionary.databinding.FragmentDictionaryBinding;
import com.example.easy_lang_dictionary.room.App;
import com.example.easy_lang_dictionary.room.Converters;
import com.example.easy_lang_dictionary.room.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class DictionaryFragment extends Fragment {
    private FragmentDictionaryBinding binding;
    private NavController navController;
    public static RecyclerView recyclerView;
    private ArrayList<Word_list> word_lists;
    private final User user = MainActivity2.user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDictionaryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(false);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        word_lists = Converters.fromString(user.getWord_lists());

        recyclerView.setAdapter(new DictionaryAdapter(word_lists));

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        AppCompatButton buttonCreate = binding.buttonCreate;
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_fragment_dictionary_to_newWordListFragment);
            }
        });

        return view;
    }
}