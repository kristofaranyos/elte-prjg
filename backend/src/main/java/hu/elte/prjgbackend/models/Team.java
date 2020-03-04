package hu.elte.prjgbackend.models;

import java.awt.image.BufferedImage;

import java.util.List;

public class Team {

    private String teamName;

    private List<User> teamMembers;

    private List<BufferedImage> uploadedImages;

    private float latitude;

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<User> getTeamMembers() {
        return this.teamMembers;
    }

    public void setTeamMembers(List<User> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public List<BufferedImage> getUploadedImages() {
        return this.uploadedImages;
    }

    public void setUploadedImages(List<BufferedImage> uploadedImages) {
        this.uploadedImages = uploadedImages;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

}