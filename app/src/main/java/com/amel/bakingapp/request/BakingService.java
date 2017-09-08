package com.amel.bakingapp.request;

import com.amel.bakingapp.data.Recipe;

import java.util.List;

import javax.xml.transform.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by CrossTechno on 9/6/2017.
 */

public interface BakingService {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipe();

}
