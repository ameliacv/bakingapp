package com.amel.bakingapp.fragments;

/**
 * Created by CrossTechno on 9/9/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amel.bakingapp.R;
import com.amel.bakingapp.adapter.RecipeListAdapter;
import com.amel.bakingapp.data.Ingredients;
import com.amel.bakingapp.data.IngredientsColumns;
import com.amel.bakingapp.data.IngredientsProvider;
import com.amel.bakingapp.data.Recipe;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeFragment extends Fragment {

    private static final String TAG = "RecipeFragment";
    private Gson gson;
    private List<Recipe> recipeList;
    private RecipeListAdapter mAdapter;
    private RecipeListAdapter.RecipeListListener mListener;
    private GridLayoutManager layoutManager;

    @BindView(R.id.listRecipe)
    RecyclerView mList;
    @BindView(R.id.swipeList)
    SwipeRefreshLayout mSwipeList;
    @BindInt(R.integer.grid_column_count)
    int gridColumnCount;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, view);
        initGson();
        init();
        return view;
    }

    private void initGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    private void initVolley() {
        mSwipeList.setRefreshing(true);
        recipeList.clear();
        mAdapter.notifyDataSetChanged();

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mSwipeList.setRefreshing(false);
                        Log.d(TAG, response);
                        List<Recipe> recipes = Arrays.asList(gson.fromJson(response, Recipe[].class));
                        ContentValues cv = new ContentValues();
                        for (Recipe recipe : recipes) {
                            recipeList.add(recipe);
                            mAdapter.notifyDataSetChanged();

                            for(Ingredients ingredients : recipe.getIngredientses()){
                                cv.put(IngredientsColumns.INGREDIENT, ingredients.getIngredient());
                                cv.put(IngredientsColumns.MEASURE, ingredients.getMeasure());
                                cv.put(IngredientsColumns.QUANTITY, ingredients.getQuantity());
                            }

                        }
                        Uri newUri = getContext().getContentResolver().insert(IngredientsProvider.Ingredients.CONTENT_URI, cv);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mSwipeList.setRefreshing(false);
            }
        });
        queue.add(stringRequest);
    }


    private void init() {
        recipeList = new ArrayList<>();
        mAdapter = new RecipeListAdapter(recipeList, mListener);
        layoutManager = new GridLayoutManager(getContext(), gridColumnCount);

        mAdapter.setHasStableIds(true);
        mList.setLayoutManager(layoutManager);
        mList.setAdapter(mAdapter);
        mList.setHasFixedSize(true);

        initVolley();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RecipeListAdapter.RecipeListListener) {
            mListener = (RecipeListAdapter.RecipeListListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListPrimaryListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}