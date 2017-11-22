package com.example.alien.tryout;

/**
 * Created by Alien on 12/08/2017.
 */

public class User {

    private String userName;
    private String imgURL;
    private String githubURL;


    public User(String userName, String gitUrl, String img_Url){
        this.userName = userName;
        this.githubURL = gitUrl;
        this.imgURL = img_Url;

    }

    public String getUserName() {
        return userName;
    }

    public String getGitHubUrl() {
        return githubURL;
    }

    public String getImgURL() {
        return imgURL;
    }

}
