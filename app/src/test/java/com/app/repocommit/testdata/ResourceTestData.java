package com.app.repocommit.testdata;

import com.app.repocommit.models.User;
import com.app.repocommit.ui.auth.AuthResource;

public class ResourceTestData {

    public static AuthResource<User> getTestUserData() {

        return new AuthResource<>(AuthResource.AuthStatus.AUTHENTICATED,
                new User(1,
                        "a",
                        "a@mail.com",
                        "a.com"),
                "user 1");
    }
}
