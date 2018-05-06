package biz.bikehub.rentbike.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import biz.bikehub.rentbike.network.Services;

public class Order {

    private String orderid;
    private String userid;
    private String stationid;
    private String bikeid;
    private String orderdate;
    private String starttime;
    private String endtime;
    private String startingprice;
    private String endprice;
    private String status;
    private String endstationid;

    private static String TAG = "RentBike";
    private String name;

    public Order() {
    }

    public Order(String orderid, String userid, String stationid, String bikeid, String orderdate, String starttime, String endtime, String startingprice, String endprice, String status, String endstationid) {
        this.orderid = orderid;
        this.userid = userid;
        this.stationid = stationid;
        this.bikeid = bikeid;
        this.orderdate = orderdate;
        this.starttime = starttime;
        this.endtime = endtime;
        this.startingprice = startingprice;
        this.endprice = endprice;
        this.status = status;
        this.endstationid = endstationid;
    }

    public String getOrderid() {
        return orderid;
    }

    public Order setOrderid(String orderid) {
        this.orderid = orderid;
        return this;
    }

    public String getUserid() {
        return userid;
    }

    public Order setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public String getStationid() {
        return stationid;
    }

    public Order setStationid(String stationid) {
        this.stationid = stationid;
        return this;
    }

    public String getBikeid() {
        return bikeid;
    }

    public Order setBikeid(String bikeid) {
        this.bikeid = bikeid;
        return this;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public Order setOrderdate(String orderdate) {
        this.orderdate = orderdate;
        return this;
    }

    public String getStarttime() {
        return starttime;
    }

    public Order setStarttime(String starttime) {
        this.starttime = starttime;
        return this;
    }

    public String getEndtime() {
        return endtime;
    }

    public Order setEndtime(String endtime) {
        this.endtime = endtime;
        return this;
    }

    public String getStartingprice() {
        return startingprice;
    }

    public Order setStartingprice(String startingprice) {
        this.startingprice = startingprice;
        return this;
    }

    public String getEndprice() {
        return endprice;
    }

    public Order setEndprice(String endprice) {
        this.endprice = endprice;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusText() {
        String s ="";
        switch (this.status){
            case "0": s = "Pendiente";
                    break;
            case "1": s = "En curso";
                    break;
            case "2": s = "Finalizado";
                    break;
        }

        return s;
    }

    public Order setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getEndstationid() {
        return endstationid;
    }

    public Order setEndstationid(String endstationid) {
        this.endstationid = endstationid;
        return this;
    }

    public static Order from(JSONObject jsonOrder) {
        try {
            return new Order(
                    jsonOrder.getString("orderid"),
                    jsonOrder.getString("userid"),
                    jsonOrder.getString("stationid"),
                    jsonOrder.getString("bikeid"),
                    jsonOrder.getString("orderdate"),
                    jsonOrder.getString("starttime"),
                    jsonOrder.getString("endtime"),
                    jsonOrder.getString("startingprice"),
                    jsonOrder.getString("endprice"),
                    jsonOrder.getString("status"),
                    jsonOrder.getString("endstationid"));

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Order> from(JSONArray jsonOrders) {
        List<Order> orders = new ArrayList<>();
        int length = jsonOrders.length();
        for(int i = 0; i < length; i++) {
            try {
                orders.add(Order.from(jsonOrders.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    public static Order from(Bundle bundle) {
        return new Order(
                bundle.getString("orderid"),
                bundle.getString("userid"),
                bundle.getString("stationid"),
                bundle.getString("bikeid"),
                bundle.getString("orderdate"),
                bundle.getString("starttime"),
                bundle.getString("endtime"),
                bundle.getString("startingprice"),
                bundle.getString("endprice"),
                bundle.getString("status"),
                bundle.getString("endstationid"));
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("orderid", getOrderid());
        bundle.putString("userid", getUserid());
        bundle.putString("stationid", getStationid());
        bundle.putString("bikeid", getBikeid());
        bundle.putString("orderdate", getOrderdate());
        bundle.putString("starttime", getStarttime());
        bundle.putString("endtime", getEndtime());
        bundle.putString("startingprice", getStartingprice());
        bundle.putString("endprice", getEndprice());
        bundle.putString("status", getStatus());
        bundle.putString("endstationid", getEndstationid());
        return bundle;
    }


}
