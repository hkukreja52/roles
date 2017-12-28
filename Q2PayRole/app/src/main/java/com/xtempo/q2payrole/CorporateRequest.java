package com.xtempo.q2payrole;

import com.google.gson.annotations.SerializedName;
import com.xtempo.networklibrary.network.data.inherit.ResponseValidator;
import com.xtempo.networklibrary.network.parsing.Required;

import java.lang.reflect.Array;

/**
 * Created by Harsha on 12/11/2017.
 */

public class CorporateRequest extends ResponseValidator{
    @Required
    @SerializedName("corp_id")
    private String corp_id;
    @Required
    @SerializedName("corp_name")
    private String corp_name;
    @Required
    @SerializedName("pincode")
    private String pincode;
    @Required
    @SerializedName("address")
    private String address;
    @Required
    @SerializedName("pancard_no")
    private String pancard_no;
    @Required
    @SerializedName("domain")
    private Array[] domain;


    public String getCorp_id() {
        return corp_id;
    }

    public void setCorp_id(String corp_id) {
        this.corp_id = corp_id;
    }

    public String getCorp_name() {
        return corp_name;
    }

    public void setCorp_name(String corp_name) {
        this.corp_name = corp_name;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPancard_no() {
        return pancard_no;
    }

    public void setPancard_no(String pancard_no) {
        this.pancard_no = pancard_no;
    }


    public Array[] getDomain() {
        return domain;
    }


}
