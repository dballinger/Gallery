package com.braxisltd.gallery.application;

import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class GalleryApplication {

    private Connection connection;
    private ApplicationConfig config;

    public GalleryApplication(ApplicationConfig config) {
        this.config = config;
    }

    public GalleryApplication start() throws IOException {
        ApplicationContainer container = new ApplicationContainer(config);
        connection = new SocketConnection(container);
        SocketAddress address = new InetSocketAddress(config.getPort());
        connection.connect(address);
        System.out.println("Started...");
        return this;
    }

    public void stop() throws IOException {
        connection.close();
        System.out.println("Stopped.");
    }
}
