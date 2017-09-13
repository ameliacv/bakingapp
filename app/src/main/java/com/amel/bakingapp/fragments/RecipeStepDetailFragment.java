package com.amel.bakingapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amel.bakingapp.R;
import com.amel.bakingapp.data.Recipe;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepDetailFragment extends Fragment {

    private RecipeStepDetailListener mListener;
    private MediaSessionCompat mediaSession;

    @BindView(R.id.recipeVideo)
    SimpleExoPlayerView mRecipeVideo;
    @BindView(R.id.ingredientsDetail)
    TextView mIngredientsDetail;

    public RecipeStepDetailFragment() {
        // Required empty public constructor
    }

    public static RecipeStepDetailFragment newInstance(String param1, String param2) {
        RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RecipeStepDetailListener) {
            mListener = (RecipeStepDetailListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface RecipeStepDetailListener {
        void onStepDetail(Recipe recipe);
    }
}
