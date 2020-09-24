package com.example.manhvan.datn_mocsneaker.MyService;

public class APIService {
    private static String base_url="http://192.168.24.7:8080/datn_mocsneakerapi/";
    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
