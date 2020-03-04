package hu.elte.prjgbackend.models;

import java.util.List;

public class Game {

    private List<Team> teams;

    private long timeSinceStart;

    private Boolean isRunning;

    Game(){
        timeSinceStart = 0;
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public long getTimeSinceStart() {
        return this.timeSinceStart;
    }

    public Boolean getIsRunning() {
        return this.isRunning;
    }

    public void setIsRunning(Boolean isRunning) {
        this.isRunning = isRunning;
    }


}