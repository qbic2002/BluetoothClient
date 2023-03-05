package org.client;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.OutputStream;

public class BluetoothClient {
    private StreamConnection streamConnection = null;
    private OutputStream outputStream = null;
    public void connect(String url) throws IOException {
        streamConnection = (StreamConnection) Connector.open(url);
        outputStream = streamConnection.openOutputStream();
    }

    public void send(String content) throws IOException {
        if (outputStream == null) {
            throw new IOException("OutputStream is null");
        }

        outputStream.write(content.getBytes());
    }
}
