package com.amel.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amel.bakingapp.data.Recipe;
import com.amel.bakingapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by CrossTechno on 9/8/2017.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    private static final String TAG = "RecipeListAdapter";
    private final List<Recipe> mValues;
    private final RecipeListListener mListener;

    public RecipeListAdapter(List<Recipe> items, RecipeListListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public RecipeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.segment_recipe_item, parent, false);
        return new RecipeListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecipeListAdapter.ViewHolder holder, int position) {
        final Recipe recipe = mValues.get(position);
        if (recipe != null) {
            holder.mTitle.setText(recipe.getName());
            holder.mServing.setText(recipe.getServings());

            Picasso.with(holder.mImage.getContext())
                    .load(R.drawable.recipeicon)
                    .fit().centerCrop().into(holder.mImage);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecipeClick(recipe);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TextView mTitle, mServing;
        public ImageView mImage;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) mView.findViewById(R.id.titleRecipe);
            mServing = (TextView) mView.findViewById(R.id.servingRecipe);
            mImage = (ImageView) mView.findViewById(R.id.imageRecipe);
        }

    }

    public interface RecipeListListener {
        void onRecipeClick(Recipe recipe);
    }
}

