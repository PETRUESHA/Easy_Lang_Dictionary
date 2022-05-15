package com.example.easy_lang_dictionary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.Word_list;
import com.example.easy_lang_dictionary.activities.MainActivity2;
import com.example.easy_lang_dictionary.fragments_MainActivity2.NewWordListFragment;
import com.example.easy_lang_dictionary.fragments_MainActivity2.WordListFragment;

import java.util.ArrayList;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.Pattern> {
    private ArrayList<Word_list> word_lists;
    private NavController navController;
    private String name;

    public DictionaryAdapter(ArrayList<Word_list> lists) {
        this.word_lists = lists;
    }


    @NonNull
    @Override
    public Pattern onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_pattern, parent, false);
        navController = MainActivity2.navController;
        return new Pattern(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Pattern holder, int position) {
        Word_list word_list = word_lists.get(position);
        holder.title.setText(word_list.getName());
        holder.entryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WordListFragment.word_list = word_list;
                navController.navigate(R.id.action_fragment_dictionary_to_wordListFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return word_lists.size();
    }

    public class Pattern extends RecyclerView.ViewHolder {
        TextView title;
        AppCompatImageButton entryButton;

        public Pattern(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            entryButton = itemView.findViewById(R.id.entryButton);
        }
    }
}

