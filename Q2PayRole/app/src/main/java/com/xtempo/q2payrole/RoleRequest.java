package com.xtempo.q2payrole;

import com.google.gson.annotations.SerializedName;
import com.xtempo.networklibrary.network.data.inherit.ResponseValidator;

/**
 * Created by Harsha on 10/31/2017.
 */

public class RoleRequest extends ResponseValidator {
    @SerializedName("user_id")
    String user_id;
    @SerializedName("corp_id")
    String corp_id;
    @SerializedName("page_no")
    String page_no;
    @SerializedName("page_size")
    String page_size;
    @SerializedName("search_string")
    String search_string;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setCorp_id(String corp_id) {
        this.corp_id = corp_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getCorp_id() {
        return corp_id;
    }


   /* public String setUser_id() {
        return user_id;
    }

    public String setCorp_id() {
        return corp_id;
    }
*/
    public String setPage_no() {
        return page_no;
    }

    public String setPage_size() {
        return page_size;
    }

    public String setSearch_string() {
        return search_string;
    }
}
