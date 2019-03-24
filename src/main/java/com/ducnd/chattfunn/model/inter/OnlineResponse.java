package com.ducnd.chattfunn.model.inter;

public class OnlineResponse {
    private int id;
    private boolean isOnline;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
