package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Machine {
    private String id;

    private String name;

    private String manufacturer;

    private int technology;

    public String getTechnologyValue() {
        if (technology == 2) {
            return "2D";
        } else if (technology == 3) {
            return "3D";
        } else {
            return "NA";
        }
    }
}
