package com.app.repocommit.testdata;

import com.app.repocommit.models.Post;
import com.app.repocommit.models.User;
import com.app.repocommit.ui.auth.AuthResource;
import com.app.repocommit.ui.main.Resource;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PostTestData {
    public static Resource<List<Post>> TEST_AUTH_RESOURCE() {

        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(1, 1, "title1", "body1"));
        posts.add(new Post(2, 2, "title2", "body2"));
        return new Resource<List<Post>>(Resource.Status.SUCCESS,
                posts,
                "test post message");
    }

}
