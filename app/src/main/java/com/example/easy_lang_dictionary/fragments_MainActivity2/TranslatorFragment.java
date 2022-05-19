package com.example.easy_lang_dictionary.fragments_MainActivity2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.os.Environment;
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
import com.example.easy_lang_dictionary.Word_list;
import com.example.easy_lang_dictionary.activities.MainActivity2;
import com.example.easy_lang_dictionary.databinding.FragmentChooseProfileBinding;
import com.example.easy_lang_dictionary.databinding.FragmentTranslatorBinding;
import com.example.easy_lang_dictionary.retrofit.Api;
import com.example.easy_lang_dictionary.retrofit.Translate;
import com.example.easy_lang_dictionary.retrofit.Translation;
import com.example.easy_lang_dictionary.room.App;
import com.example.easy_lang_dictionary.room.Converters;
import com.example.easy_lang_dictionary.room.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
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
    private final User user = MainActivity2.user;
    private String lang1, lang2, word;
    private final String token = MainActivity2.token_key;
    private Map<String, Integer> lang_codes;
    private Integer lang1_code, lang2_code;
    private EditText inputText;
    private TextView textViewTranslation;
    private String dictName;
    private String soundFileName;
    private String soundFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTranslatorBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        textViewTranslation = binding.textViewTranslation;
        inputText = binding.editInputText;

        AppCompatButton addButton = binding.addButton;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getWord()) {
                    ArrayList<Word_list> word_lists = Converters.fromString(user.getWord_lists());
                    String[] names = new String[word_lists.size()];
                    String translate = textViewTranslation.getText().toString();
                    for (int i = 0; i < word_lists.size(); i++) {
                        names[i] = word_lists.get(i).getName();
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AlertDialogStyle);
                    builder.setTitle("Choose word list");
                    builder.setItems(names, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            word_lists.get(item).updateWords(word);
                            word_lists.get(item).updateTranslates(translate);
                            user.setWord_lists(Converters.fromArrayList(word_lists));
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    App.getInstance(getContext()).getDatabase().userDao().update(user);
                                }
                            }).start();
                        }
                    }).show();
                }
            }
        });

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
        spinner1.setSelection(Arrays.asList(languages).indexOf(user.getLanguage()));

        Spinner spinner2 = binding.spinner4;
        ArrayAdapter<String> adapter2 = new ArrayAdapter(getContext(), R.layout.spinner, languages);
        adapter2.setDropDownViewResource(R.layout.spinner_drop_item);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(4);

        ImageButton soundButton = binding.soundButton;
        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // Для работы нужно записывать в wav файл
            public void onClick(View v) {

            }
        });

        ImageButton switchButton = binding.switchButton;
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id_1 = spinner1.getSelectedItemPosition();
                int id_2 = spinner2.getSelectedItemPosition();
                spinner1.setSelection(id_2);
                spinner2.setSelection(id_1);
            }
        });


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

        Call<JsonObject> translate = api.getTranslate("Bearer " + MainActivity2.token_key, text, srcLang, dstLang);
        translate.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Translate tran = gson.fromJson(response.body(), Translate.class);
                    Translation translation = tran.getTranslation();
                    /*dictName = translation.getDictionaryName();
                    soundFileName = translation.getSoundName();*/
                    String tr = translation.getTranslation();
                    textViewTranslation.setText(tr);

                    /*Call<String> sound = api.getSound("Bearer " + MainActivity2.token_key, dictName, soundFileName);
                    sound.enqueue(new Callback<String>() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                soundFile = response.body();
                                String encoded = Base64.getEncoder().encodeToString(soundFile.getBytes());                 Необходимо понять как записать в .wav файл
                                File path = Environment.getExternalStoragePublicDirectory(
                                        Environment.DIRECTORY_MOVIES);
                                File file = new File(path, "/" + "test.wav");
                                try (FileWriter writer = new FileWriter("test.wav", false)) {
                                    writer.write(encoded);
                                    writer.flush();
                                }
                                catch (IOException ex) {
                                    Log.d("RRR", ex.getMessage());
                                }
                            }
                            else {
                                Log.d("RRR", String.valueOf(response.code()));
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("RRR", "failure" + t);
                        }
                    }); */
                } else {
                    Log.d("RRR", Integer.toString(response.code()));
                    textViewTranslation.setText(R.string.error_messege);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                textViewTranslation.setText(R.string.error_messege);
            }
        });
    }

    Boolean getWord() {
        word = inputText.getText().toString().trim();
        if (word.isEmpty()) {
            inputText.setError(getString(R.string.required));
            inputText.requestFocus();
            return false;
        } else return true;
    }
}