package com.example.manhvan.datn_mocsneaker.MyService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Dataservice {
    @FormUrlEncoded
    @POST("themnhanvien.php")
    Call<String> ThemNhanVien(@Field("staff_name")String staff_name,@Field("satff_phone")String satff_phone,
                              @Field("staff_address")String staff_address,@Field("date_of_birth")String date_of_birth,
                              @Field("id_card_number")String id_card_number,@Field("user_name")String user_name,
                              @Field("user_password")String user_password);
}
