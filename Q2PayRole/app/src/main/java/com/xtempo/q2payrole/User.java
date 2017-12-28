package com.xtempo.q2payrole;

import com.google.gson.annotations.SerializedName;
import com.xtempo.networklibrary.network.data.inherit.ResponseValidator;

/**
 * Created by Harsha on 12/6/2017.
 */

public class User extends ResponseValidator {
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("corp_id")
    private String corp_id;
    @SerializedName("corp_name")
    private String corp_name;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;

    public String getUser_id() {
        return user_id;
    }

    public String getCorp_id() {
        return corp_id;
    }

    public void setCorp_id(String corp_id) {
        this.corp_id = corp_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCorp_name() {
        return corp_name;
    }

    public void setCorp_name(String corp_name) {
        this.corp_name = corp_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
