package model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String Title;
    private int Year;
    private int Pages;


    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + Title + ", Year: " + Year + ", Pages: " + Pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Libro libro)) return false;
        return id == libro.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

}