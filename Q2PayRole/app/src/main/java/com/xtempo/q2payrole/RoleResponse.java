package com.xtempo.q2payrole;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Harsha on 10/31/2017.
 */

public class RoleResponse extends com.xtempo.networklibrary.network.data.inherit.Response {
    @SerializedName("roles")
    private ArrayList<Role> roles;


    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }
}

