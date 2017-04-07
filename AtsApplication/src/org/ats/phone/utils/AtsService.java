package org.ats.phone.utils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Created by user on 02.04.17.
 */
public class AtsService {

    public static void startService(){

        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(12102), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.createContext("/test", new MyHandler());
        server.start();
    }

    static class MyHandler implements HttpHandler {

        public void handle(HttpExchange t) throws IOException {
            String response = "{ \"phones\": [\"79818926024\"]}";
     //       String response="{\"fgdgfdggdfgf\" : [\" 79818926024 \"] , \"text\" : \" TEXT\" }";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
