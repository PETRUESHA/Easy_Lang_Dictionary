package com.example.easy_lang_dictionary.fragments_MainActivity2;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.Word_list;
import com.example.easy_lang_dictionary.activities.MainActivity2;
import com.example.easy_lang_dictionary.adapters.DictionaryAdapter;
import com.example.easy_lang_dictionary.adapters.WordListAdapter;
import com.example.easy_lang_dictionary.databinding.FragmentWordListBinding;
import com.example.easy_lang_dictionary.room.App;
import com.example.easy_lang_dictionary.room.Converters;
import com.example.easy_lang_dictionary.room.User;

import java.util.ArrayList;


public class WordListFragment extends Fragment {
    private FragmentWordListBinding binding;
    public static Word_list word_list;
    private Dialog dialog;
    private EditText editWord, editTranslate;
    private final User user = MainActivity2.user;
    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWordListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        MainActivity2.title.setText(word_list.getName());

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new WordListAdapter(word_list.getWords(), word_list.getTranslates()));

        dialog = new Dialog(getContext());
        dialog.setTitle("Add new word");
        dialog.setContentView(R.layout.add_new_word_dialog);
        editWord = dialog.findViewById(R.id.editTextTextWord);
        editTranslate = dialog.findViewById(R.id.editTextTextTranslate);
        AppCompatImageButton completeDialogButton = dialog.findViewById(R.id.entryButton2);
        completeDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addWord()) {
                    recyclerView.setAdapter(new WordListAdapter(word_list.getWords(), word_list.getTranslates()));
                    dialog.dismiss();
                }
            }
        });

        AppCompatImageButton addNewButton = binding.imageButtonAddNew;
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        AppCompatButton confirmButton = binding.confirmButton2;
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        App.getInstance(getContext()).getDatabase().userDao().update(user);
                    }
                }).start();
                DictionaryFragment.recyclerView.setAdapter(new DictionaryAdapter(Converters.fromString(user.getWord_lists())));
                MainActivity2.bottomNavigationView.setVisibility(View.VISIBLE);
                navController.navigate(R.id.action_wordListFragment_to_fragment_dictionary);
            }
        });
        return view;
    }

    Boolean addWord() {
        String word = editWord.getText().toString().trim();
        String translate = editTranslate.getText().toString().trim();
        Boolean pass = true;
        if (word.isEmpty()) {
            editWord.setError(getString(R.string.required));
            editWord.requestFocus();
            pass = false;
        }
        if (translate.isEmpty()) {
            editTranslate.setError(getString(R.string.required));
            editTranslate.requestFocus();
            pass = false;
        }
        if (pass) {
            word_list.updateWords(word);
            word_list.updateTranslates(translate);
            ArrayList<Word_list> wlsts = Converters.fromString(user.getWord_lists());
            wlsts.add(word_list);
            user.setWord_lists(Converters.fromArrayList(wlsts));
            return true;
        }
        else return false;
    }
}