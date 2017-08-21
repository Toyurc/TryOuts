package com.example.alien.tryout;

/**
 * Created by Alien on 12/08/2017.
 */

public class User {

    //TODO Improper Variable naming
    private String UserName;
    private String Img_Url;
    private String GitHub_Url;


    public User(String userName, String gitUrl, String img_Url){
        this.UserName = userName;
        this.GitHub_Url = gitUrl;
        this.Img_Url = img_Url;

    }

    public String getUserName() {
        return UserName;
    }

    public String getGitHub_Url() {
        return GitHub_Url;
    }

    public String getImg_Url() {
        return Img_Url;
    }

}
