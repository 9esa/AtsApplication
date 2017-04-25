package org.ats.phone.utils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.ats.phone.Main;
import org.ats.phone.dao.DriverEntity;
import org.ats.phone.dao.TradeOrderEntity;
import org.hibernate.internal.CriteriaImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * Created by user on 02.04.17.
 */
public class AtsService {

    public static void startServiceClient(){

        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(12103), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.createContext("/clientRequest", new HttpHandler() {
            public void handle(HttpExchange httpExchange) throws IOException {

                String response = "";
                String sQuery = httpExchange.getRequestURI().getQuery();
                String sNumberOfRequest = sQuery.substring(sQuery.indexOf("input_result=") + 13, sQuery.indexOf("numa=") - 1);

                ArrayList<TradeOrderEntity> listOfOrders = (ArrayList<TradeOrderEntity>) Main.getSession().createCriteria(TradeOrderEntity.class).list();

                response = "{ \"returned_code\": 1}";

                for(TradeOrderEntity oTradeOrderEntity : listOfOrders){
                    if(sNumberOfRequest.equals(String.valueOf(oTradeOrderEntity.getId()))){
                        response = "{ \"phones\": [\" " + oTradeOrderEntity.getDriverByDriverId().getPhone() + "\"]}";
                        break;
                    }
                }

                //       String response="{\"fgdgfdggdfgf\" : [\" 79818926024 \"] , \"text\" : \" TEXT\" }";
                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });
        server.start();
    }

    public static void startServiceWhoCalls(){
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(12102), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.createContext("/whoCalls", new HttpHandler() {
            public void handle(HttpExchange httpExchange) throws IOException {

                String sQuery = httpExchange.getRequestURI().getQuery();
                String sPhoneNumber = sQuery.substring(sQuery.indexOf("numa=") + 5);

                ArrayList<DriverEntity> listOfDrivers = (ArrayList<DriverEntity>) Main.getSession().createCriteria(DriverEntity.class).list();

                boolean bIsDriver = false;
                String response = "";
                for(DriverEntity oDriverEntity : listOfDrivers){
                    if(oDriverEntity.getPhone().contains(sPhoneNumber)){
                        bIsDriver = true;
                    }
                }

                //todo убрать отрицание
                if(!bIsDriver){
                    //Активация меню выбран водитель
                    response = "{ \"returned_code\": 2}";
                }else{
                    //Активация меню выбран клиент
                    response = "{ \"returned_code\": 1}";
                }

                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

            }
        });

        server.start();
    }

    public static void  startServiceDriver(){
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(12104), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.createContext("/driverRequest", new HttpHandler() {
            public void handle(HttpExchange httpExchange) throws IOException {

                String response = "{ \"phones\": [\"79818926024\"]}";
                //       String response="{\"fgdgfdggdfgf\" : [\" 79818926024 \"] , \"text\" : \" TEXT\" }";
                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

            }
        });

        server.start();
    }


}
