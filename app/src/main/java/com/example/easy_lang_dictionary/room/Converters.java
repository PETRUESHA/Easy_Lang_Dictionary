package com.example.easy_lang_dictionary.room;

import androidx.room.TypeConverter;

import com.example.easy_lang_dictionary.Word_list;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static ArrayList<Word_list> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Word_list>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Word_list> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}

