package com.android.mymall;

import android.support.annotation.NonNull;

/**
 * Created by Ashwin on 27-02-2018.
 */

public class UserId {

    public String userId;

    public <T extends UserId> T withId(@NonNull final String id) {
        this.userId = id;
        return (T) this;
    }
}
