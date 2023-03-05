package org.game;

import org.client.BluetoothClient;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class Game {
    private BluetoothClient bluetoothClient;
    private String name;
    Scanner scanner;

    public Game(BluetoothClient bluetoothClient) {
        this.bluetoothClient = bluetoothClient;
    }

    private String askName() {
        scanner = new Scanner(System.in);
        System.out.println("Введите имя команды:");
        name = scanner.nextLine();
        return name;
    }

    private void notifyServer() throws IOException {
        bluetoothClient.send(String.valueOf(new Date().getTime()) + " " + name);
    }
    public void startGame() throws IOException, InterruptedException {
        askName();

        while (true) {
            printTeamName();
            System.out.println("Когда готов ответ, нажмите на Enter");
            scanner.nextLine();
            notifyServer();
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
    }

    private void printTeamName() {
        System.out.println("-----" + name + "-----");
    }
}
