package com.example.easy_lang_dictionary.retrofit;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Description {

    @SerializedName("SourceLanguage")
    @Expose
    private Integer sourceLanguage;
    @SerializedName("TargetLanguage")
    @Expose
    private Integer targetLanguage;
    @SerializedName("InvertedDirection")
    @Expose
    private Boolean invertedDirection;
    @SerializedName("Prefix")
    @Expose
    private String prefix;
    @SerializedName("HasNextPage")
    @Expose
    private Boolean hasNextPage;
    @SerializedName("StartPos")
    @Expose
    private String startPos;
    @SerializedName("Headings")
    @Expose
    private ArrayList<Heading> headings = null;

    public Integer getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(Integer sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public Integer getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(Integer targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public Boolean getInvertedDirection() {
        return invertedDirection;
    }

    public void setInvertedDirection(Boolean invertedDirection) {
        this.invertedDirection = invertedDirection;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public String getStartPos() {
        return startPos;
    }

    public void setStartPos(String startPos) {
        this.startPos = startPos;
    }

    public ArrayList<Heading> getHeadings() {
        return headings;
    }

    public void setHeadings(ArrayList<Heading> headings) {
        this.headings = headings;
    }

}