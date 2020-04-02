package com.app.repocommit;


import com.app.repocommit.models.Post;
import com.app.repocommit.models.User;
import com.app.repocommit.network.main.MainApi;
import com.app.repocommit.testdata.PostTestData;
import com.app.repocommit.testdata.LiveDataTestUtil;
import com.app.repocommit.testdata.ResourceTestData;
import com.app.repocommit.ui.auth.AuthResource;
import com.app.repocommit.ui.main.Resource;
import com.app.repocommit.ui.main.post.PostViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import io.reactivex.Flowable;
import io.reactivex.Single;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class ProfileViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    MainApi mainApiClient;

    @Mock
    SessionManager sessionManager;

    @Mock
    Observer<Resource<List<Post>>> observer;

    private PostViewModel postViewModel;
    @Mock
    LifecycleOwner lifecycleOwner;
    Lifecycle lifecycle;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        lifecycle = new LifecycleRegistry(lifecycleOwner);
        postViewModel = new PostViewModel(sessionManager, mainApiClient);

        MutableLiveData<AuthResource<User>> returnedUserValue = new MutableLiveData<>();
        returnedUserValue.setValue(ResourceTestData.getTestUserData());

        MutableLiveData<Resource<List<Post>>> returnedUserPosts = new MutableLiveData<>();
        returnedUserPosts.setValue(PostTestData.TEST_AUTH_RESOURCE());

        when(sessionManager.getAuthUser()).thenReturn(returnedUserValue);
        when(mainApiClient
                .getPosts(1))
                .thenReturn(Flowable.<List<Post>>empty());
        postViewModel.observePost().observeForever(observer);
    }

    @Test
    public void testApiFetchDataSuccess() {
        // Mock API response
        postViewModel.observePost();
        verify(observer).onChanged(Resource.loading((List<Post>) null));
        //verify(observer).onChanged(Resource.success(PostTestData.TEST_AUTH_RESOURCE()));
    }

}
