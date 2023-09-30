package org.peaksoft.model;

import java.time.LocalDate;

public class Car {
    private Long id;
    private String model;
    private LocalDate yearOfRelease;
    private String color;

    public Car() {
    }

    public Car(String model, LocalDate yearOfRelease, String color) {
        this.model = model;
        this.yearOfRelease = yearOfRelease;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getYearOfRelease() {
        return yearOfRelease;
    }

    public String getColor() {
        return color;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYearOfRelease(LocalDate yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "[ Car :" +
                ",id :" + id +
                ", model :" + model +
                ", yearOfRelease :" + yearOfRelease +
                ", color :" + color +
                ']';
    }
}
