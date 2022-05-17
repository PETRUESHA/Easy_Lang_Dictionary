package com.example.easy_lang_dictionary.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.Word_list;
import com.example.easy_lang_dictionary.activities.MainActivity2;
import com.example.easy_lang_dictionary.retrofit.Heading;
import com.example.easy_lang_dictionary.room.App;
import com.example.easy_lang_dictionary.room.Converters;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchPattern> {
    private ArrayList<Heading> headings;
    private String heading, translation;


    public SearchAdapter(ArrayList<Heading> headings) {
        this.headings = headings;
    }


    @NonNull
    @Override
    public SearchPattern onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_pattern, parent, false);
        return new SearchPattern(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchPattern holder, int position) {
        Heading head = headings.get(position);
        heading = head.getHeading();
        translation = head.getTranslation();
        holder.word.setText(heading);
        holder.translate.setText(translation);
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Word_list> word_lists = Converters.fromString(MainActivity2.user.getWord_lists());
                String[] names = new String[word_lists.size()];
                for (int i = 0; i < word_lists.size(); i++) {
                    names[i] = word_lists.get(i).getName();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.AlertDialogStyle);
                builder.setTitle("Choose word list");
                builder.setItems(names, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        word_lists.get(item).updateWords(heading);
                        word_lists.get(item).updateTranslates(translation);
                        MainActivity2.user.setWord_lists(Converters.fromArrayList(word_lists));
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                App.getInstance(v.getContext()).getDatabase().userDao().update(MainActivity2.user);
                            }
                        }).start();
                    }
                }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return headings.size();
    }

    public class SearchPattern extends RecyclerView.ViewHolder {
        TextView word, translate;
        AppCompatImageButton addButton;

        public SearchPattern(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.textView13);
            translate = itemView.findViewById(R.id.textView14);
            addButton = itemView.findViewById(R.id.plusButton);
        }
    }
}
