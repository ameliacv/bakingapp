package com.amel.bakingapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amel.bakingapp.ConstUtil;
import com.amel.bakingapp.R;
import com.amel.bakingapp.data.Recipe;
import com.amel.bakingapp.data.Steps;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepDetailFragment extends Fragment {

    private static final String PLAYER_POSITION = "PlayerPosition";
    private MediaSessionCompat mediaSession;
    private SimpleExoPlayer player;
    private Steps mStep;
    private long currentPosition = 0;

    @BindView(R.id.recipeVideo)
    SimpleExoPlayerView mRecipeVideo;
    @BindView(R.id.instructionRecipe)
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
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getLong(PLAYER_POSITION);
        } else {
            if (getArguments() != null) {
                mStep = (Steps) getArguments().getSerializable(ConstUtil.STEP);
                if(mStep != null){
                    mIngredientsDetail.setText(mStep.getDescription());
                    if(mStep.getVideoUrl() != null){
                        initExoPlayer();
                    }

                }
            }
        }
        return view;
    }

    private void initExoPlayer() {
        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);


        player = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
        mRecipeVideo.setPlayer(player);
        DefaultBandwidthMeter bandwidthMeter1 = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(),
                Util.getUserAgent(getActivity(), "BakingApp"), bandwidthMeter1);
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource videoSource = new ExtractorMediaSource(Uri.parse(mStep.getVideoUrl()),
                dataSourceFactory, extractorsFactory, mainHandler, null);
        player.prepare(videoSource);

        player.setPlayWhenReady(true);

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (player != null) {
            outState.putLong(PLAYER_POSITION, player.getCurrentPosition());
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (player != null) {
            player.stop();
            player.release();
        }
    }

}
