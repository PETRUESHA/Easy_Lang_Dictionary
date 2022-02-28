package com.example.easy_lang_dictionary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.fragments_MainActivity.ChooseProfile;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChooseProfileAdapter extends RecyclerView.Adapter<ChooseProfileAdapter.ViewPattern> {


    @NonNull
    @Override
    public ViewPattern onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_pattern, parent, false);
        return new ViewPattern(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPattern holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewPattern extends RecyclerView.ViewHolder {
        
        TextView tv;

        public ViewPattern(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView3);
        }
    }
}
