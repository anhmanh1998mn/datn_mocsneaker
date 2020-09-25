package com.example.manhvan.datn_mocsneaker.MyService;

import com.example.manhvan.datn_mocsneaker.entity.NhanVien;
import com.example.manhvan.datn_mocsneaker.entity.SanPhamMoi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @FormUrlEncoded
    @POST("themnhanvien.php")
    Call<String> ThemNhanVien(@Field("staff_name")String staff_name,@Field("satff_phone")String satff_phone,
                              @Field("staff_address")String staff_address,@Field("date_of_birth")String date_of_birth,
                              @Field("id_card_number")String id_card_number,@Field("user_name")String user_name,
                              @Field("user_password")String user_password);

    @GET("danhsachnhanvien.php")
    Call<List<NhanVien>> GetNhanVien();

    @FormUrlEncoded
    @POST("timkiemnhanvien.php")
    Call<List<NhanVien>> GetNVTimKiem(@Field("staff_name")String tenNV);

    @GET("danhsachsanphammoi.php")
    Call<List<SanPhamMoi>> GetSPMoi();

    @GET("snaphamnoibat.php")
    Call<List<SanPhamMoi>> GetSPNB();

    @FormUrlEncoded
    @POST("suanhanvien.php")
    Call<String> SuaNhanVien(@Field("id") int id,@Field("staff_name") String staff_name,@Field("staff_phone")String staff_phone,
                             @Field("staff_address")String staff_address,@Field("date_of_birth")String date_of_birth,
                             @Field("id_card_number")String id_card_number);
    @FormUrlEncoded
    @POST("khoatiakhoannhanvien.php")
    Call<String> KhoaTaiKhoan(@Field("id") int id);
}
