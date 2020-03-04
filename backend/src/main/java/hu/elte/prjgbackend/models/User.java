package hu.elte.prjgbackend.models;

import java.awt.image.*;

public class User{

    private String userName;

    private BufferedImage profilePicture;

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public BufferedImage getProfilePicture(){
        return profilePicture;
    }

    public void setProfilePicture(BufferedImage profilePicture){
        this.profilePicture = profilePicture;
    }

}