package com.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MachineTest {

    @Test
    public void testGetTechnologyValue() {
        Machine machine = new Machine();
        
        machine.setTechnology(1);
        assertEquals(machine.getTechnologyValue(), "NA");

        machine.setTechnology(2);
        assertEquals(machine.getTechnologyValue(), "2D");

        machine.setTechnology(3);
        assertEquals(machine.getTechnologyValue(), "3D");

    }
}
