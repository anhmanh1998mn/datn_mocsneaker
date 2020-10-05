package com.example.manhvan.datn_mocsneaker.MyService;

public class APIService {
//    private static String base_url="http://192.168.42.44/datn_mocsneakerapi/";
private static String base_url="http://192.168.1.63:8080/datn_mocsneakerapi/";
    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
