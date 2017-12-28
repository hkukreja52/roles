package com.xtempo.q2payrole;

import com.google.gson.annotations.SerializedName;
import com.xtempo.networklibrary.network.data.inherit.ResponseValidator;

/**
 * Created by Harsha on 11/28/2017.
 */

public class UserReq extends ResponseValidator {
    @SerializedName("corp_id")
    String corp_id;
    @SerializedName("user_id")
    String user_id;
    @SerializedName("first_name")
    String first_name;
    @SerializedName("page_no")
    String page_no;
    @SerializedName("page_size")
    String page_size;
    @SerializedName("search_string")
    String search_string;
    @SerializedName("role_id")
    String role_id;
    @SerializedName("service_id")
    String service_id;

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getPage_no() {
        return page_no;
    }

    public void setPage_no(String page_no) {
        this.page_no = page_no;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public void setCorp_id(String corp_id) {
        this.corp_id = corp_id;
    }

    public String getCorp_id() {
        return corp_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPage_size() {
        return page_size;
    }

    public void setPage_size(String page_size) {
        this.page_size = page_size;
    }

    public String getSearch_string() {
        return search_string;
    }

    public void setSearch_string(String search_string) {
        this.search_string = search_string;
    }
}

