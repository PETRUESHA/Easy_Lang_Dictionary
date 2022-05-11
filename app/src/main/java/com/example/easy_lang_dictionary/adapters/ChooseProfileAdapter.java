package com.example.easy_lang_dictionary.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.activities.MainActivity;
import com.example.easy_lang_dictionary.activities.MainActivity2;
import com.example.easy_lang_dictionary.room.User;

import java.util.List;

public class ChooseProfileAdapter extends RecyclerView.Adapter<ChooseProfileAdapter.ViewPattern> {
    private List<User> users;
    private Context ctx;

    public ChooseProfileAdapter(List<User> users_list) {
        users = users_list;
    }

    @NonNull
    @Override
    public ViewPattern onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_pattern, parent, false);
        ctx = parent.getContext();
        return new ViewPattern(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPattern holder, int position) {
        User user = users.get(position);


        holder.Profile_name.setText(user.getProfile_name());
        holder.Name.setText(user.getName());
        holder.Surname.setText(user.getSurname());
        holder.Language.setText(user.getLanguage());
        holder.Level_of_language.setText(user.getLevel_of_language());

        holder.entryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("RRR", Integer.toString(users.size()));
                Intent intent = new Intent(ctx, MainActivity2.class);
                intent.putExtra("user", user);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewPattern extends RecyclerView.ViewHolder {
        
        TextView Profile_name, Name, Surname, Language, Level_of_language;
        AppCompatImageButton entryButton;

        public ViewPattern(@NonNull View itemView) {
            super(itemView);
            Profile_name = itemView.findViewById(R.id.textViewProfileName);
            Name = itemView.findViewById(R.id.textViewName);
            Surname = itemView.findViewById(R.id.textViewSurname);
            Language = itemView.findViewById(R.id.textViewLanguage);
            Level_of_language = itemView.findViewById(R.id.textViewLevel);
            entryButton = itemView.findViewById(R.id.entryButton);
        }
    }
}
