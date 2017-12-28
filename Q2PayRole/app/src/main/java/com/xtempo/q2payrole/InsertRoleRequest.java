package com.xtempo.q2payrole;

import com.google.gson.annotations.SerializedName;
import com.xtempo.networklibrary.network.data.inherit.ResponseValidator;
import com.xtempo.networklibrary.network.parsing.Required;

/**
 * Created by Harsha on 11/20/2017.
 */

public class InsertRoleRequest extends ResponseValidator{
    @SerializedName("role_id")
    String Role_id;
    @Required
    @SerializedName("role")
    String Role;
    @SerializedName("corp_id")
    String CORP_ID;
    @Required
    @SerializedName("description")
    String DESCRIPTION;

    public void setRole_id(String role_id) {
        Role_id = role_id;
    }

    public void setRole(String role) {
        Role = role;
    }

    public void setCORP_ID(String CORP_ID) {
        this.CORP_ID = CORP_ID;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }
}
