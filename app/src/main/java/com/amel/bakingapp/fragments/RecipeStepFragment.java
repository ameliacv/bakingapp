package com.amel.bakingapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amel.bakingapp.R;
import com.amel.bakingapp.Util;
import com.amel.bakingapp.adapter.RecipeListAdapter;
import com.amel.bakingapp.adapter.RecipeStepListAdapter;
import com.amel.bakingapp.data.Ingredients;
import com.amel.bakingapp.data.Recipe;
import com.amel.bakingapp.data.Steps;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepFragment extends Fragment {
    private Recipe mRecipe;
    private RecipeStepListAdapter mAdapter;
    private List<Steps> stepsList;
    private RecipeStepListAdapter.RecipeStepListener mListener;
    private LinearLayoutManager mLayoutManager;
    List<Ingredients> ingredient = new ArrayList<>();
    List<Steps> step = new ArrayList<>();


    @BindView(R.id.ingredientsDetail)
    TextView mIngredientsDetail;
    @BindView(R.id.listStepRecipe)
    RecyclerView mListStepRecipe;

    public RecipeStepFragment() {
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
        View view = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        ButterKnife.bind(this, view);
        init();
        if (savedInstanceState != null) {

        } else {
            if (getArguments() != null) {
                mRecipe = (Recipe) getArguments().getSerializable(Util.RECIPE);
            }
        }

        if (mRecipe != null) {
            ingredient = mRecipe.getIngredientses();
            step = mRecipe.getStepses();
            stepsList.addAll(step);
            mAdapter.notifyDataSetChanged();
            if (ingredient != null) {

                    for (Ingredients ingredients : ingredient) {
                        mIngredientsDetail.setText(ingredients.getIngredient());
                    }

            }
        }

        return view;
    }

    private void init() {
        stepsList = new ArrayList<>();

        mAdapter = new RecipeStepListAdapter(stepsList, mListener);
        mAdapter.setHasStableIds(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mListStepRecipe.setLayoutManager(mLayoutManager);
        mListStepRecipe.setAdapter(mAdapter);
        mListStepRecipe.setHasFixedSize(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RecipeStepListAdapter.RecipeStepListener) {
            mListener = (RecipeStepListAdapter.RecipeStepListener) context;
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