package com.example.manhvan.datn_mocsneaker.Model;

import android.util.Log;

import com.example.manhvan.datn_mocsneaker.MyService.APIService;
import com.example.manhvan.datn_mocsneaker.MyService.Dataservice;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoThemMoiSanPham {
    private ThemMoiSPInterface themMoiSPInterface;

    public MoThemMoiSanPham(ThemMoiSPInterface themMoiSPInterface) {
        this.themMoiSPInterface = themMoiSPInterface;
    }

    public void xuLyUpLoad(String realpath){

        java.io.File file=new java.io.File(realpath);
        String file_path=file.getAbsolutePath();
        String[] mangtenfile=file_path.split("\\.");
        file_path=mangtenfile[0]+System.currentTimeMillis()+"."+mangtenfile[1];
        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form_data"),file);
        MultipartBody.Part body=MultipartBody.Part.createFormData("upload_file",file_path,requestBody);
        Dataservice dataservice= APIService.getService();
        Call<String> callback=dataservice.upLoadIMG(body);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response!=null){
                    String messeage=response.body();
//                    Log.d("PathSend",messeage);
                    String path="/datn_mocsneakerapi/image/"+messeage;
//                    Log.d("PathSend",path);
                    themMoiSPInterface.onS(path);
                    return;
                }
                themMoiSPInterface.onF();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void xuLy2(int maNV,String tenSP,String noiDung,int giaBan,String path){
        Dataservice dataservice=APIService.getService();
        Call<String> callback=dataservice.themMoiSP(maNV,tenSP,path,noiDung,giaBan);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                //Log.d("MSP",response.body().toString()+"");
                int maSp=Integer.parseInt(response.body());
//                Log.d("Mã sản phẩm thêm",maSp+"");
                themMoiSPInterface.onS1(maSp);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                Log.d("MSP",t.toString()+"");
            }
        });
    }

    public void xuLyUpLoadCTAnh(final String realpath, final int maSP){

        java.io.File file=new java.io.File(realpath);
        String file_path=file.getAbsolutePath();
        String[] mangtenfile=file_path.split("\\.");
        file_path=mangtenfile[0]+System.currentTimeMillis()+"."+mangtenfile[1];
        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form_data"),file);
        MultipartBody.Part body=MultipartBody.Part.createFormData("upload_file",file_path,requestBody);
        final Dataservice dataservice= APIService.getService();
        Call<String> callback=dataservice.upLoadIMG(body);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response!=null){
                    String messeage=response.body();
//                    Log.d("PathSend",messeage);
                    String path="/datn_mocsneakerapi/image/"+messeage;
                    Log.d("PathSend",path);
                    Call<String> call1CTAnh=dataservice.themCTanh(maSP,path);
                    call1CTAnh.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.body().trim().equals("thanhcong")){
                                themMoiSPInterface.themTC();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    //themMoiSPInterface.onS(path);
                    return;
                }
                //themMoiSPInterface.onF();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void themSoLuongSP(int maSP,int size39,int size40,int size41,int size42,int size43){
        Dataservice dataservice=APIService.getService();
        Call<String> callbak=dataservice.themSoLuongSize(maSP,size39,size40,size41,size42,size43);
        callbak.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().toString().equals("thanhcong")){
                    themMoiSPInterface.themSize();
                    return;
                }
                themMoiSPInterface.onF();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("LoiThem",t.toString());
            }
        });
    }

    public interface ThemMoiSPInterface{
        public void onS(String path);
        public void onF();
        public void onS1(int maSP);
        public void themTC();
        public void themSize();
    }
}
