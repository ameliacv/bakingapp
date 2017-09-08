package com.amel.bakingapp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.amel.bakingapp.adapter.RecipeListAdapter;
import com.amel.bakingapp.data.Recipe;
import com.amel.bakingapp.request.BakingService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecipeListAdapter mRecipeAdapter;

    @BindView(R.id.containerList)
    LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeRetrofit();

    }

    private void initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BakingService bakingService = retrofit.create(BakingService.class);

        Call<List<Recipe>> listCall = bakingService.getRecipe();
        listCall.equals(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                Log.i(TAG, response.body().toString());
                if (response.isSuccessful()) {
                    for (Recipe recipe : response.body()) {
                        Log.d(TAG, recipe.getName());
                    }
                }else{
                    Snackbar.make(mContainer, "FloatingActionButton is clicked", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e("Error fetching repos", t.getMessage());
            }
        });

    }
}
