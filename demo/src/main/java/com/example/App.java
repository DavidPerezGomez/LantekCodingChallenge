package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.example.model.Machine;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
    
    private PropertiesReader propertiesReader;

    private List<Machine> machineList;

    public App(PropertiesReader propertiesReader) throws Exception {
        this.propertiesReader = propertiesReader;

        fetchMachineList(
            this.propertiesReader.getUri(),
            this.propertiesReader.getUser(),
            this.propertiesReader.getPassword()
        );
    }

    public void mainLoop() throws IOException {
        boolean running = true;

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

        String input;

        printCommands();
        while (running) {
            prompt();
            input = reader.readLine();
            running = handleInput(input);
        }
    }

    private boolean printCommands() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Commands:\n");
        stringBuilder.append("2, 2D\tList 2D machines.\n");
        stringBuilder.append("3, 3D\tList 3D machines.\n");
        stringBuilder.append("a, all\tList all machines.\n");
        stringBuilder.append("h, help\tPrint this message.\n");
        stringBuilder.append("q, exit\tClose the application.\n");
        System.out.print(stringBuilder.toString());
        return true;
    }

    private void prompt() {
        System.out.print("> ");
    }

    private boolean handleInput(String input) {
        boolean continueRunning = false;

        switch (input.toLowerCase()) {
            case "q":
            case "exit":
                continueRunning = exit();
                break;
            case "2":
            case "2d":
                continueRunning = list2DMachines();
                break;
            case "3":
            case "3d":
                continueRunning = list3DMachines();
                break;
            case "a":
            case "all":
                continueRunning = listAllMachines();
                break;
            case "h":
            case "help":
            default:
                continueRunning = printCommands();
                break;
        }

        return continueRunning;
    }

    private boolean exit() {
        System.out.println("Closing application.");
        return false;
    }

    private boolean list2DMachines() {
        System.out.println(
            formatMachineList(
                getFilteredMachines((machine) -> machine.getTechnology() == 2)
            )
        );
        return true;
    }

    private boolean list3DMachines() {
        System.out.println(
            formatMachineList(
                getFilteredMachines((machine) -> machine.getTechnology() == 3)
            )
        );
        return true;
    }

    private boolean listAllMachines() {
        System.out.println(formatMachineList(this.machineList));
        return true;
    }

    private List<Machine> getFilteredMachines(Predicate<Machine> filter) {
        return this.machineList.stream()
            .filter(filter)
            .collect(Collectors.toList());
    }

    private String formatMachineList(List<Machine> machineList) {
        int space = 4;
        String headerId = "ID";
        String headerName = "Name";
        String headerManufacturer = "Manufacturer";
        String headerTechnology = "Technology";
        String separator = "---";

        int idColumnWidth = headerId.length();
        int nameColumnWidth = headerName.length();
        int manColumnWidth = headerManufacturer.length();
        int techColumnWidth = headerTechnology.length();

        for (Machine machine : machineList) {
            idColumnWidth = Math.max(machine.getId().length(), idColumnWidth);
            nameColumnWidth = Math.max(machine.getName().length(), nameColumnWidth);
            manColumnWidth = Math.max(machine.getManufacturer().length(), manColumnWidth);
            techColumnWidth = Math.max(machine.getTechnologyValue().length(), techColumnWidth);
        }

        idColumnWidth += space;
        nameColumnWidth += space;
        manColumnWidth += space;
        techColumnWidth += space;

        StringBuilder stringBuilder = new StringBuilder();
        formatLine(stringBuilder, Arrays.asList(
            new SimpleEntry<>(headerId, idColumnWidth),
            new SimpleEntry<>(headerName, nameColumnWidth),
            new SimpleEntry<>(headerManufacturer, manColumnWidth),
            new SimpleEntry<>(headerTechnology, techColumnWidth)
        ));
        formatLine(stringBuilder, Arrays.asList(
            new SimpleEntry<>(separator, idColumnWidth),
            new SimpleEntry<>(separator, nameColumnWidth),
            new SimpleEntry<>(separator, manColumnWidth),
            new SimpleEntry<>(separator, techColumnWidth)
        ));

        for (Machine machine : machineList) {
            formatLine(stringBuilder, Arrays.asList(
                new SimpleEntry<>(machine.getId(), idColumnWidth),
                new SimpleEntry<>(machine.getName(), nameColumnWidth),
                new SimpleEntry<>(machine.getManufacturer(), manColumnWidth),
                new SimpleEntry<>(machine.getTechnologyValue(), techColumnWidth)
            ));
        }

        return stringBuilder.toString();
    }

    private void formatLine(StringBuilder stringBuilder, List<SimpleEntry<String, Integer>> columnList) {
        for (SimpleEntry<String, Integer> column : columnList) {
            stringBuilder.append(StringUtils.rightPad(column.getKey(), column.getValue()));
        }
        stringBuilder.append("\n");
    }

    private void fetchMachineList(String uri, String user, String password) throws Exception {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header("Authorization", createAuthorizationHeaderValue(user, password))
                .GET()
                .build();

            HttpClient client = HttpClient.newBuilder()
                .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            
            this.machineList = new ObjectMapper().readValue(response.body(), new TypeReference<List<Machine>>(){});
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new Exception();
        }
    }

    private String createAuthorizationHeaderValue(String user, String password) {
        String credentials = user + ":" + password;
        String encodedCredentials = Base64.getEncoder().encodeToString((credentials).getBytes());
        return "Basic " + encodedCredentials;
    }
}
