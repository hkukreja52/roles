package com.xtempo.q2payrole;

import com.google.gson.annotations.SerializedName;
import com.xtempo.networklibrary.network.data.inherit.ResponseValidator;
import com.xtempo.networklibrary.network.parsing.Required;

import java.io.Serializable;

/**
 * Created by Harsha on 10/31/2017.
 */

public class Role extends ResponseValidator implements Serializable{
    @SerializedName("role_id")
    String role_id;
    @Required
    @SerializedName("role")
    String role;
    @SerializedName("corp_id")
    String corp_id;
    @SerializedName("corp_name")
    String corp_name;
    @Required
    @SerializedName("role_desc")
    String role_desc;
    @SerializedName("rn")
    String rn;
    @SerializedName("user_id")
    String user_id;

    public String getUser_id() {
        return user_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public String getRole() {
        return role;
    }

    public String getCorp_id() {
        return corp_id;
    }

    public String getCorp_name() {
        return corp_name;
    }

    public String getRole_desc() {
        return role_desc;
    }

    public String getRn() {
        return rn;
    }
}
