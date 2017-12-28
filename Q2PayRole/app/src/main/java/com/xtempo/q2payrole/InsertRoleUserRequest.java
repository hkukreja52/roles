package com.xtempo.q2payrole;

import com.google.gson.annotations.SerializedName;
import com.xtempo.networklibrary.network.data.inherit.ResponseValidator;
import com.xtempo.networklibrary.network.parsing.Required;

/**
 * Created by Harsha on 12/7/2017.
 */

public class InsertRoleUserRequest extends ResponseValidator {
    @Required
    @SerializedName("corp_id")
    private String corp_id;
    @Required
    @SerializedName("role_id")
    private String role_id;
    @Required
    @SerializedName("user_id")
    private String user_id;
    public String getCorp_id() {
        return corp_id;
    }

    public void setCorp_id(String corp_id) {
        this.corp_id = corp_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
