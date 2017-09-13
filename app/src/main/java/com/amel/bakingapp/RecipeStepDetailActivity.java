package com.amel.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amel.bakingapp.data.Steps;
import com.amel.bakingapp.fragments.RecipeStepDetailFragment;
import com.amel.bakingapp.fragments.RecipeStepFragment;

import butterknife.ButterKnife;

public class RecipeStepDetailActivity extends AppCompatActivity {

    private Steps mSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mSteps = (Steps) intent.getSerializableExtra(ConstUtil.STEP);
        if (savedInstanceState == null) {
            RecipeStepDetailFragment recipeStepDetailFragment = new RecipeStepDetailFragment();

            Bundle args = new Bundle();
            args.putSerializable(ConstUtil.STEP, mSteps);
            recipeStepDetailFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_step_detail, recipeStepDetailFragment)
                    .commit();
        }
    }
}
