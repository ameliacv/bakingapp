package com.amel.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amel.bakingapp.R;
import com.amel.bakingapp.data.Ingredients;
import com.amel.bakingapp.data.Recipe;
import com.amel.bakingapp.data.Steps;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by amelia on 11/09/2017.
 */

public class RecipeStepListAdapter extends RecyclerView.Adapter<RecipeStepListAdapter.ViewHolder> {
    private static final String TAG = "RecipeStepListAdapter";
    private final List<Steps> mValues;
    private final RecipeStepListener mListener;

    public RecipeStepListAdapter(List<Steps> items, RecipeStepListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public RecipeStepListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.segment_recipe_step_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Steps steps = mValues.get(position);
        if (steps != null) {
            holder.mNameStep.setText(steps.getShortDescription());
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecipeStepClick(steps);
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
        public TextView mNameStep;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameStep = (TextView) mView.findViewById(R.id.textStep);
        }
    }


    public interface RecipeStepListener {
        void onRecipeStepClick(Steps steps);
    }
}
