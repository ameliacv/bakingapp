package com.amel.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CrossTechno on 8/25/2017.
 */

public class Recipe implements Serializable{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("servings")
    @Expose
    private String servings;
    @SerializedName("ingredients")
    @Expose
    private List<Ingredients> ingredientses;
    @SerializedName("steps")
    @Expose
    private List<Steps> stepses;

    public List<Ingredients> getIngredientses() {
        return ingredientses;
    }

    public void setIngredientses(List<Ingredients> ingredientses) {
        this.ingredientses = ingredientses;
    }

    public List<Steps> getStepses() {
        return stepses;
    }

    public void setStepses(List<Steps> stepses) {
        this.stepses = stepses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
