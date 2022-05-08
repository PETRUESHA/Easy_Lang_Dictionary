package com.example.easy_lang_dictionary.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {

    @PrimaryKey
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    public User() {}
    public User(long id, String name, String surname, String email, String password, String language, String level_of_language) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.language = language;
        this.level_of_language = level_of_language;
    }

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

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

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
}
