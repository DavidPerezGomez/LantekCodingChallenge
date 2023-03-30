package com.example;

import java.io.IOException;

public final class Main {
    
    private Main() {}

    public static void main(String[] args) {
        PropertiesReader propertiesReader = null;
        try {
            propertiesReader = new PropertiesReader("/application.properties");
        } catch (IOException e) {
            System.out.println("An error ocurred while initializing the application. Aborting execution.");
            System.exit(1);
        }

        App app = null;
        try {
            System.out.println("Loading machine data from the web...");
            app = new App(propertiesReader);

            System.out.println("Machine data loaded successfully.");
        } catch (Exception e) {
            System.out.println("An error ocurred while loading the data. Aborting execution.");
            System.exit(1);
        }

        try {
            app.mainLoop();
        } catch (IOException e) {
            System.out.println("An error ocurred while reading user input. Aborting execution.");
            System.exit(1);
        }
    }
    
}
