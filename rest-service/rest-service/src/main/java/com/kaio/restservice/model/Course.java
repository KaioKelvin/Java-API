package com.kaio.restservice.model;

import java.time.LocalDate;

public class Course {

    private int id;
    private String name;
    private String area;
    private String period;
    private String duration;
    private int price;
    private LocalDate creationDate;


    public Course(int id, String name, String area, String period, String duration, int price, LocalDate creationDate) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.period = period;
        this.duration = duration;
        this.price = price;
        this.creationDate = creationDate;
    }

    //Get and Set
    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getCreationDate() { return creationDate; }

    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }

}
