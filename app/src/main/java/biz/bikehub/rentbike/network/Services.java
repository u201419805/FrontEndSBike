package biz.bikehub.rentbike.network;

public class Services {

    private static String BASE_URL = "http://192.168.43.26:8000/";

    public static String login(){
        return BASE_URL + "login";
    }

    public static String getAllStations(){
        return BASE_URL + "stations";
    }

    public static String newOrder(){
        return BASE_URL + "newOrder";
    }

    public static String lastOrder(){
        return BASE_URL + "lastOrder";
    }

    public static String allOrdersByStation(){
        return BASE_URL + "allOrdersByStation";
    }

    public static String findStation(){
        return BASE_URL + "findStation";
    }


}
