package com.example.alien.tryout;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Alien on 12/08/2017.
 */

public class User {

    private String UserName;
    private String Img_Url;
    private String GitHub_Url;


    public User(String userName, String gitUrl, String img_Url){
        this.UserName = userName;
        this.Img_Url = img_Url;
        this.GitHub_Url = gitUrl;
    }



    public String getUserName() {
        return UserName;
    }

    public String getImg_Url() {
        return Img_Url;
    }

    public String getGitHub_Url() {
        return GitHub_Url;
    }



}
