package org.ats.phone.utils;

import org.ats.phone.Main;
import org.ats.phone.dao.TradeOrderEntity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 17.04.17.
 */
public class SmsService {

    public void startWatcher(){

        Timer timer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                ArrayList<TradeOrderEntity> listOfOrders = (ArrayList<TradeOrderEntity>) Main.getSession().createCriteria(TradeOrderEntity.class)
                        .list();

                int iDay = 0;
                int iYear = 0;

                for (TradeOrderEntity tradeOrderEntity : listOfOrders){
                    iDay = tradeOrderEntity.getTradeByTradeId().getDateOfTrade().getDay();
                    iYear = tradeOrderEntity.getTradeByTradeId().getDateOfTrade().getYear();

                    if(iDay == new Date().getDay() && iYear == new Date().getYear()){
                        sentMessageClient(tradeOrderEntity);
                        sentMessageDriver(tradeOrderEntity);
                    }

                }
            }
        };

        Date dCurrentDate = new Date();

        dCurrentDate.setHours(1);
        dCurrentDate.setMinutes(20);
        dCurrentDate.setSeconds(0);

        timer.schedule(myTask, dCurrentDate, TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

    }

    private void sentMessageClient(TradeOrderEntity tradeOrderEntity){

        String sValue = "Сегодня ожидается поставка вашего заказа:" + tradeOrderEntity + ". Cпасибо за выбор нас.";

        System.out.println("СМС клиенту");
        System.out.println(sValue);
        System.out.println(tradeOrderEntity.getClientByUserId().getPhoneNumber());
//        new SmsConnectionService().getInterfaceRegistrationCode()
//                .smsNotification(Constant.I_ID_SMS_SERVICE, Constant.KEY_SMS_SERVICE, tradeOrderEntity.getClientByUserId().getPhoneNumber(), Constant.SIGNATURE_SMS_SERVICE, sValue)
//                .enqueue(new Callback<String>() {
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        System.out.print("ok");
//                    }
//                    public void onFailure(Call<String> call, Throwable throwable) {
//                        System.out.print("bad");
//                    }
//                });
    }

    private void sentMessageDriver(TradeOrderEntity tradeOrderEntity){

        String sValue = "Номер клиента:" + tradeOrderEntity.getNumberOfTrade() + ". Адрес клиента: " + tradeOrderEntity.getAddress() + ".";

        System.out.println("СМС водителю");
        System.out.println(sValue);
        System.out.println(tradeOrderEntity.getDriverByDriverId().getPhone());

//        new SmsConnectionService().getInterfaceRegistrationCode()
//                .smsNotification(Constant.I_ID_SMS_SERVICE, Constant.KEY_SMS_SERVICE, tradeOrderEntity.getDriverByDriverId().getPhone(), Constant.SIGNATURE_SMS_SERVICE, sValue)
//                .enqueue(new Callback<String>() {
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        System.out.print("ok");
//                    }
//                    public void onFailure(Call<String> call, Throwable throwable) {
//                        System.out.print("bad");
//                    }
//                });
    }

}
