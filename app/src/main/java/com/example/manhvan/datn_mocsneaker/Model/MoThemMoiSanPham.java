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
                    Log.d("PathSend",messeage);
                    themMoiSPInterface.onS();
                    return;
                }
                themMoiSPInterface.onF();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public interface ThemMoiSPInterface{
        public void onS();
        public void onF();
    }
}
