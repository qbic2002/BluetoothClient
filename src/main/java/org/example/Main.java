package org.example;


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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        LocalDevice localDevice = LocalDevice.getLocalDevice();
        System.out.println(localDevice.getBluetoothAddress());

        localDevice.setDiscoverable(DiscoveryAgent.GIAC);
        DiscoveryAgent discoveryAgent = localDevice.getDiscoveryAgent();

        RemoteDevice[] remoteDevices = discoveryAgent.retrieveDevices(1);
        List<String> names = Arrays.stream(remoteDevices).map((rd) -> {
            try {
                return rd.getFriendlyName(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();
        System.out.println(names);
        List<String> addrs = Arrays.stream(remoteDevices).map(RemoteDevice::getBluetoothAddress).toList();
        System.out.println(addrs);

        StreamConnection streamConnection = (StreamConnection) Connector.open("btspp://940853355BD8:4");
        OutputStream outputStream = streamConnection.openOutputStream();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
//            input += '\n';

            outputStream.write(input.getBytes());

//            outputStream.flush();
        }

        outputStream.close();
        streamConnection.close();
    }
}