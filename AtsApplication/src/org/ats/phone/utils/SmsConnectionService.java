package org.ats.phone.utils;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by user on 15.03.17.
 */
public class SmsConnectionService {

    private static IInterfaceRegistrationCode iInterfaceRegistrationCode;

    public static IInterfaceRegistrationCode getInterfaceRegistrationCode() {
        if (iInterfaceRegistrationCode == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://bytehand.com:3800/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            iInterfaceRegistrationCode = retrofit.create(IInterfaceRegistrationCode.class);
        }
        return iInterfaceRegistrationCode;
    }

    public interface IInterfaceRegistrationCode {

        @GET("/send")
        Call<String> smsNotification(@Query("id") int iId, @Query("key") String sKey ,
                                     @Query("to") String sPhone, @Query("from") String sSignature,
                                     @Query("text") String sTextMessage);

    }

}
