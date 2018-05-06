package biz.bikehub.rentbike.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Station {

    private String stationid;
    private String name;
    private String address;
    private String capacity;
    private String urlimage;

    public Station() {
    }

    public Station(String stationid, String name, String address, String capacity, String urlimage) {
        this.stationid = stationid;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.urlimage = urlimage;
    }

    public String getStationid() {
        return stationid;
    }

    public Station setStationid(String stationid) {
        this.stationid = stationid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Station setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Station setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCapacity() {
        return capacity;
    }

    public Station setCapacity(String capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public Station setUrlimage(String urlimage) {
        this.urlimage = urlimage;
        return this;
    }

    public static Station from(JSONObject jsonStation) {
        try {
            return new Station(
                    jsonStation.getString("stationid"),
                    jsonStation.getString("name"),
                    jsonStation.getString("address"),
                    jsonStation.getString("capacity"),
                    jsonStation.getString("urlimage"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Station> from(JSONArray jsonStations) {
        List<Station> stations = new ArrayList<>();
        int length = jsonStations.length();
        for(int i = 0; i < length; i++) {
            try {
                stations.add(Station.from(jsonStations.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return stations;
    }

    public static Station from(Bundle bundle) {
        return new Station(
                bundle.getString("stationid"),
                bundle.getString("name"),
                bundle.getString("address"),
                bundle.getString("capacity"),
                bundle.getString("urlimge"));
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("stationid", getStationid());
        bundle.putString("name", getName());
        bundle.putString("address", getAddress());
        bundle.putString("capacity", getCapacity());
        bundle.putString("urlimage", getUrlimage());
        return bundle;
    }
}
