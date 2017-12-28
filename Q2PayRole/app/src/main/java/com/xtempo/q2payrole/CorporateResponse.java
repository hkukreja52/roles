package com.xtempo.q2payrole;

import com.google.gson.annotations.SerializedName;
import com.xtempo.networklibrary.network.data.inherit.Response;

import java.util.ArrayList;

/**
 * Created by Harsha on 11/21/2017.
 */

public class CorporateResponse extends Response {
    @SerializedName("corporates")
    private ArrayList<Corporate> corporates;

    public ArrayList<Corporate> getCorporates() {
        return corporates;
    }

    public void setCorporates(ArrayList<Corporate> corporates) {
        this.corporates = corporates;
    }
}