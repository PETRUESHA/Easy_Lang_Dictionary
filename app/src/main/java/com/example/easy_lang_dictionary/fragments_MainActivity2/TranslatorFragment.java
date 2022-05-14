package com.example.easy_lang_dictionary.fragments_MainActivity2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.activities.MainActivity2;
import com.example.easy_lang_dictionary.databinding.FragmentChooseProfileBinding;
import com.example.easy_lang_dictionary.databinding.FragmentTranslatorBinding;
import com.example.easy_lang_dictionary.retrofit.Api;
import com.example.easy_lang_dictionary.retrofit.Translate;
import com.example.easy_lang_dictionary.retrofit.Translation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TranslatorFragment extends Fragment {
    private FragmentTranslatorBinding binding;
    private final String[] languages = {"English", "French", "Spanish", "German", "Russian"};
    private String lang1, lang2;
    private final String token = MainActivity2.token_key;
    private Map<String, Integer> lang_codes;
    private Integer lang1_code, lang2_code;
    private EditText inputText;
    private TextView textViewTranslation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTranslatorBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        textViewTranslation = binding.textViewTranslation;
        inputText = binding.editInputText;
        lang_codes = new HashMap<>();
        lang_codes.put("English", 1033);
        lang_codes.put("Russian", 1049);
        lang_codes.put("French", 1036);
        lang_codes.put("Spanish", 1034);
        lang_codes.put("German", 1031);

        Spinner spinner1 = binding.spinner3;


        ArrayAdapter<String> adapter1 = new ArrayAdapter(getContext(), R.layout.spinner, languages);
        adapter1.setDropDownViewResource(R.layout.spinner_drop_item);
        spinner1.setAdapter(adapter1);
        spinner1.setSelection(0);

        Spinner spinner2 = binding.spinner4;
        ArrayAdapter<String> adapter2 = new ArrayAdapter(getContext(), R.layout.spinner, languages);
        adapter2.setDropDownViewResource(R.layout.spinner_drop_item);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(4);


        ImageButton translateButton = binding.translateButton;
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lang1 = spinner1.getSelectedItem().toString();
                lang2 = spinner2.getSelectedItem().toString();
                lang1_code = lang_codes.get(lang1);
                lang2_code = lang_codes.get(lang2);
                String text = inputText.getText().toString().trim();
                translate(lang1_code, lang2_code, text);
            }
        });

        return view;
    }

    void translate(int srcLang, int dstLang, String text) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).baseUrl(MainActivity2.BASE_URL).build();
        Api api = retrofit.create(Api.class);

        Call<JsonObject> translate = api.getTranslate(text, srcLang, dstLang);
        translate.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Translate tran = gson.fromJson(response.body(), Translate.class);
                    Translation translation = tran.getTranslation();
                    String tr = translation.getTranslation();
                    textViewTranslation.setText(tr);
                }
                else {
                    textViewTranslation.setText(R.string.error_messege);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                textViewTranslation.setText(R.string.error_messege);
            }
        });
    }
}