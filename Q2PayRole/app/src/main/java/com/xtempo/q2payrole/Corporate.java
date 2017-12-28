package com.xtempo.q2payrole;

import com.google.gson.annotations.SerializedName;
import com.xtempo.networklibrary.network.data.inherit.Response;
import com.xtempo.networklibrary.network.data.inherit.ResponseValidator;

/**
 * Created by Harsha on 11/21/2017.
 */

public class Corporate extends ResponseValidator {


    @SerializedName("corp_id")
    public String corp_id;
    @SerializedName("corp_name")
    public String corp_name;

    public String getCorp_name() {
        return corp_name;
    }

    public void setCorp_name(String corp_name) {
        this.corp_name = corp_name;
    }

    public String getCorp_id() {
        return corp_id;
    }

    public void setCorp_id(String corp_id) {
        this.corp_id = corp_id;
    }
}
