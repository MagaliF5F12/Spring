package com.example.magasklad.Models;

public class Student {
    int id;
    String FIO;
    String group;
    int course;
    Boolean isDeleted = false;

    public Student(int id, String FIO, String group, int course) {
        this.id = id;
        this.FIO = FIO;
        this.group = group;
        this.course = course;
    }

    public Student(String FIO, String group, int course) {
        this.FIO = FIO;
        this.group = group;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        if(course < 1 || course > 4) throw new IllegalArgumentException("Значение курса вышло за границы диапазона [1-4]");
        this.course = course;
    }

    public Boolean getIsDeleted() { return isDeleted; }

    public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }
}
