package com.app.repocommit.ui.main.post;

import android.util.Log;

import com.app.repocommit.SessionManager;
import com.app.repocommit.models.Post;
import com.app.repocommit.network.main.MainApi;
import com.app.repocommit.ui.main.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {

    private final SessionManager sessionManager;
    private final MainApi mainApi;

    private MediatorLiveData<Resource<List<Post>>> posts;

    private static final String TAG = "PostViewModel";

    @Inject
    public PostViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
    }

    public LiveData<Resource<List<Post>>> observePost() {
        if (posts == null) {
            posts = new MediatorLiveData<>();
            //to show loading state while starting of the request.
            posts.setValue(Resource.loading((List<Post>) null));

            //convert rx to livedata
            final LiveData<Resource<List<Post>>> source;
            source = LiveDataReactiveStreams
                    .fromPublisher(mainApi
                            .getPosts(sessionManager.getAuthUser().getValue().data.getId())
                            .onErrorReturn(new Function<Throwable, List<Post>>() {
                                @Override
                                public List<Post> apply(Throwable throwable)
                                        throws Exception {

                                    Log.e(TAG, "apply: ", throwable);

                                    Post post = new Post(-1,1,"-1","-1");
                                    ArrayList<Post> posts = new ArrayList<>();
                                    posts.add(post);
                                    return posts;
                                }
                            })
                            .map(new Function<List<Post>, Resource<List<Post>>>() {
                                @Override
                                public Resource<List<Post>> apply(List<Post> posts)
                                        throws Exception {

                                    if (posts.size() > 0) {
                                        if (posts.get(0).getId() == -1) {
                                            return Resource.error("Some error", null);
                                        }}
                                        return Resource.success(posts);
                                    }
                            }).subscribeOn(Schedulers.io()));
            posts.addSource(source, new Observer<Resource<List<Post>>>() {
                @Override
                public void onChanged(Resource<List<Post>> listResource) {
                    posts.setValue(listResource);
                    posts.removeSource(source);
                }
            });
        }
        return posts;
    }
}
