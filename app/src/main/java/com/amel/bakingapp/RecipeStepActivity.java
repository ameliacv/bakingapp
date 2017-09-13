package com.amel.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amel.bakingapp.adapter.RecipeStepListAdapter;
import com.amel.bakingapp.data.Recipe;
import com.amel.bakingapp.data.Steps;
import com.amel.bakingapp.fragments.RecipeStepDetailFragment;
import com.amel.bakingapp.fragments.RecipeStepFragment;

import java.util.List;

import butterknife.BindBool;
import butterknife.ButterKnife;

public class RecipeStepActivity extends AppCompatActivity implements RecipeStepListAdapter.RecipeStepListener{
    private String TAG = "RecipeStepActivity";

    private Recipe recipe;
    private List<Steps> steps;

    @BindBool(R.bool.two_pane_mode)
    boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        recipe = (Recipe) intent.getSerializableExtra(ConstUtil.RECIPE);
        steps = recipe.getStepses();

        if (savedInstanceState == null) {
            RecipeStepFragment recipeStepFragment = new RecipeStepFragment();

            Bundle args = new Bundle();
            args.putSerializable(ConstUtil.RECIPE, recipe);
            recipeStepFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_step, recipeStepFragment)
                    .commit();
//            int orientation = getResources().getConfiguration().orientation;
//            Log.d(TAG,twoPane+"");
            if (twoPane) {
                RecipeStepFragment recipeStepFragment1 = new RecipeStepFragment();

                Bundle StepArgs = new Bundle();
                StepArgs.putSerializable(ConstUtil.RECIPE, recipe);
                recipeStepFragment1.setArguments(StepArgs);

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container_step, recipeStepFragment1)
                        .commit();


                Steps getStep = steps.get(0);

                RecipeStepDetailFragment detailStepFragment = new RecipeStepDetailFragment();

                Bundle stepArgs = new Bundle();
                stepArgs.putSerializable(ConstUtil.STEP, getStep);
                detailStepFragment.setArguments(stepArgs);

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container_step_detail, detailStepFragment)
                        .commit();
            }

        }
    }

    @Override
    public void onRecipeStepClick(Steps steps) {
        Intent intent = new Intent(this, RecipeStepDetailActivity.class);
        intent.putExtra(ConstUtil.STEP, steps);
        startActivity(intent);
    }
}
