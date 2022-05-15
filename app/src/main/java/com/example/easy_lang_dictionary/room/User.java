package com.example.easy_lang_dictionary.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.easy_lang_dictionary.Word_list;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "profile_name")
    private String profile_name;

    @ColumnInfo(name = "surname")
    private String surname;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "language")
    private String language;

    @ColumnInfo(name = "level_of_language")
    private String level_of_language;

    @ColumnInfo(name = "word_lists")
    private String word_lists;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getProfile_name() { return profile_name; }

    public void setProfile_name(String profile_name) { this.profile_name = profile_name; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getLanguage() { return language; }

    public void setLanguage(String language) { this.language = language; }

    public String getLevel_of_language() { return level_of_language; }

    public void setLevel_of_language(String level_of_language) { this.level_of_language = level_of_language; }

    public String getWord_lists() { return word_lists; }

    public void setWord_lists(String word_lists) { this.word_lists = word_lists; }
}
