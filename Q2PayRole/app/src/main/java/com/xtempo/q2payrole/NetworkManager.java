package com.xtempo.q2payrole;

import android.content.Context;
import android.support.annotation.NonNull;

import com.xtempo.networklibrary.NetworkLibrary;
import com.xtempo.networklibrary.network.data.inherit.Response;
import com.xtempo.networklibrary.network.manager.NetworkHelper;

import java.util.AbstractMap;

/**
 * Created by Harsha on 11/2/2017.
 */

public class NetworkManager extends NetworkHelper {
    private static final String TAG = NetworkManager.class.getSimpleName();
    private static NetworkManager instance;

    public NetworkManager(Context context) {
        super(context);
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new NetworkManager(context);
            NetworkLibrary.init(context, Type.INPUT_INSUFFICIENT, Type.TOKEN_EXPIRED, Type.USER_UNAUTHORIZED);
            NetworkLibrary.addHeader(new AbstractMap.SimpleEntry<>("X-API-KEY","eyJlbWFpbCI6IjIiLCJpYXQiOjE1MTQyNzk0NTR9.LdbjumyQ0nGfkUKmZGP9AR-XDHd17b7DDc8WAJQk9iA"));
        }
    }

    public static void ViewAllRole(@NonNull RoleRequest rolerequest) {
        if (instance != null) {
            instance.createAndMakeGsonRequest(MethodType.POST, "/support/roles/view", rolerequest, RoleResponse.class, null);
        }
    }

    public static void add_a_new_role(@NonNull InsertRoleRequest insertRole) {
        if (instance != null) {
            instance.createAndMakeGsonRequest(MethodType.POST, "/support/roles/upsert_role_master", insertRole, Response.class, null);
        }
    }
    public static void view_all_corporates(@NonNull Corporate corporate) {
        if(instance!=null){
            instance.createAndMakeGsonRequest(MethodType.POST,"/support/corporates/view",corporate,CorporateResponse.class,null);
        }

    }
    public static void ViewUsers(@NonNull UserReq userReq) {
        if(instance!=null){
            instance.createAndMakeGsonRequest(MethodType.POST,"/support/user/view", userReq,UserResponse.class,null);
        }
    }
    public static void InsertUserRole(@NonNull InsertRoleUserRequest insertRoleUserRequest){
        if(instance!=null){
            instance.createAndMakeGsonRequest(MethodType.POST,"/support/roles/insert_role_user",insertRoleUserRequest,UserResponse.class,null);
        }
    }
    public static void DeleteUserRole(@NonNull UserReq userReq){
        instance.createAndMakeGsonRequest(MethodType.POST,"/support/roles/delete_role_user)",userReq,UserResponse.class,null);
    }

}
