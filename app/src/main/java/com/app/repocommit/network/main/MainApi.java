package com.app.repocommit.network.main;

import com.app.repocommit.models.Post;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {

    @GET("posts")
    Flowable<List<Post>> getPosts(@Query("userId") int id);
}
