package com.app.repocommit.ui.main.post;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.repocommit.R;
import com.app.repocommit.models.Post;
import com.app.repocommit.ui.main.Resource;
import com.app.repocommit.viewmodel.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;

/**
 * fragment displays user posts
 */
public class PostFragment extends DaggerFragment {

    private RecyclerView recyclerView;
    private PostViewModel postViewModel;

    private static final String TAG = "PostFragment";

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        postViewModel = ViewModelProviders
                .of(this, providerFactory)
                .get(PostViewModel.class);
        subscribePostObservers();
    }

    private void subscribePostObservers() {
        postViewModel.observePost().removeObservers(getViewLifecycleOwner());
        postViewModel.observePost().observe(this, new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if(listResource!=null){
                Log.d(TAG, "onChanged POST SIZE ->"+listResource.data);
            }}
        });
    }
}
