package biz.bikehub.rentbike;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import biz.bikehub.rentbike.models.User;

public class RentBikeApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }

}
