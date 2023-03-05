package org.example;


import org.client.BluetoothClient;
import org.game.Game;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println();

        LocalDevice localDevice = LocalDevice.getLocalDevice();
        localDevice.setDiscoverable(DiscoveryAgent.GIAC);
        System.out.println(localDevice.getBluetoothAddress());

        BluetoothClient bluetoothClient = new BluetoothClient();
        bluetoothClient.connect("btspp://940853355BD8:4");

        Game game = new Game(bluetoothClient);
        game.startGame();
    }
}