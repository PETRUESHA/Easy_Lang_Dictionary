package com.example.easy_lang_dictionary.fragments_MainActivity2;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.activities.MainActivity2;
import com.example.easy_lang_dictionary.adapters.SearchAdapter;
import com.example.easy_lang_dictionary.databinding.FragmentSearchBinding;
import com.example.easy_lang_dictionary.retrofit.Api;
import com.example.easy_lang_dictionary.retrofit.Description;
import com.example.easy_lang_dictionary.retrofit.Heading;
import com.example.easy_lang_dictionary.room.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;
    private final User user = MainActivity2.user;
    private Map<String, Integer> lang_codes;
    private RecyclerView recyclerView;
    private EditText editSearch;
    private String prefix;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        lang_codes = new HashMap<>();
        lang_codes.put("English", 1033);
        lang_codes.put("Russian", 1049);
        lang_codes.put("French", 1036);
        lang_codes.put("Spanish", 1034);
        lang_codes.put("German", 1031);

        editSearch = binding.editTextSearch;
        AppCompatImageButton searchButton = binding.searchButton;
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getSearch()){
                    Gson gson = new GsonBuilder()
                            .setLenient()
                            .create();

                    Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).baseUrl(MainActivity2.BASE_URL).build();
                    Api api = retrofit.create(Api.class);

                    Call<JsonObject> req = api.getWordList("Bearer " + MainActivity2.token_key, prefix, lang_codes.get(user.getLanguage()), lang_codes.get("Russian"), 20);
                    req.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if (response.isSuccessful()) {
                                Description description = gson.fromJson(response.body(), Description.class);
                                ArrayList<Heading> headings = description.getHeadings();
                                recyclerView.setAdapter(new SearchAdapter(headings));
                            }
                            else {
                                Log.d("RRR", Integer.toString(response.code()));
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Log.d("RRR", t.toString());
                        }
                    });
                }
            }
        });

        return view;
    }

    Boolean getSearch() {
        prefix = editSearch.getText().toString().trim();
        if (prefix.isEmpty()) {
            editSearch.setError(getString(R.string.required));
            editSearch.requestFocus();
            return false;
        } else return true;
    }
}