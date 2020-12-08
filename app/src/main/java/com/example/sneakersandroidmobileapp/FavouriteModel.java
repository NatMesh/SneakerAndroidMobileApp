package com.example.sneakersandroidmobileapp;

public class FavouriteModel {
    private int id;
    private  int userId;
    private  int sneakerId;

    public FavouriteModel() {
    }

    public FavouriteModel(int id, int userId, int sneakerId) {
        this.id = id;
        this.userId = userId;
        this.sneakerId = sneakerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSneakerId() {
        return sneakerId;
    }

    public void setSneakerId(int sneakerId) {
        this.sneakerId = sneakerId;
    }

    @Override
    public String toString() {
        return "FavouriteModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", sneakerId=" + sneakerId +
                '}';
    }
}
