package com.xtempo.q2payrole;

import com.google.gson.annotations.SerializedName;
import com.xtempo.networklibrary.network.data.inherit.Response;

import java.util.ArrayList;

/**
 * Created by Harsha on 11/28/2017.
 */

public class UserResponse extends Response {
    @SerializedName("users")
    private ArrayList<User> user;

    public ArrayList<User> getUser() {
        return user;
    }


    public void setUser(ArrayList<User> user) {
        this.user = user;
    }
}
