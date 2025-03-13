package com.example.magasklad.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty
    @Size(min = 2, max = 50, message = "Длина должна быть от 2 до 10 символов")
    String FIO;
    @NotEmpty
    @Size(min = 2, max = 10, message = "Длина должна быть от 2 до 10 символов")
    String student_group;
    @Min(1)
    @Max(4)
    int course;
    @Value("${some.value}")
    boolean isDeleted = false;

    public Student() {}

    public Student(Long id, String FIO, String group, int course) {
        this.id = id;
        this.FIO = FIO;
        this.student_group = group;
        this.course = course;
    }

    public Student(String FIO, String group, int course) {
        this.FIO = FIO;
        this.student_group = group;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty @Size(min = 2, max = 50, message = "Длина должна быть от 2 до 10 символов") String getFIO() {
        return FIO;
    }

    public void setFIO(@NotEmpty @Size(min = 2, max = 50, message = "Длина должна быть от 2 до 10 символов") String FIO) {
        this.FIO = FIO;
    }

    public @NotEmpty @Size(min = 2, max = 10, message = "Длина должна быть от 2 до 10 символов") String getGroup() {
        return student_group;
    }

    public void setGroup(@NotEmpty @Size(min = 2, max = 10, message = "Длина должна быть от 2 до 10 символов") String student_group) {
        this.student_group = student_group;
    }

    @Min(1)
    @Max(4)
    public int getCourse() {
        return course;
    }

    public void setCourse(@Min(1) @Max(4) int course) {
        this.course = course;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}