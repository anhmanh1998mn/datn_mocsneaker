package com.example.manhvan.datn_mocsneaker.MyService;

import com.example.manhvan.datn_mocsneaker.entity.ChiTietDonNhapLay;
import com.example.manhvan.datn_mocsneaker.entity.DonHang;
import com.example.manhvan.datn_mocsneaker.entity.DonNhapHang;
import com.example.manhvan.datn_mocsneaker.entity.KhachHang;
import com.example.manhvan.datn_mocsneaker.entity.KichCoSP;
import com.example.manhvan.datn_mocsneaker.entity.MaNguoiLapDH;
import com.example.manhvan.datn_mocsneaker.entity.NamThongKe;
import com.example.manhvan.datn_mocsneaker.entity.NhanVien;
import com.example.manhvan.datn_mocsneaker.entity.OrderDetail;
import com.example.manhvan.datn_mocsneaker.entity.ProductImage;
import com.example.manhvan.datn_mocsneaker.entity.SanPhamMoi;
import com.example.manhvan.datn_mocsneaker.entity.SanPhamThongKe;
import com.example.manhvan.datn_mocsneaker.entity.ThongTinKhachHang;
import com.example.manhvan.datn_mocsneaker.entity.ThongTinNV;

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
    @POST("danhsachgiaynam.php")
    Call<List<SanPhamMoi>> GetGiayNam(@Field("idnhan") int idnhan);

//    @GET("danhsachgiaynu.php")
//    Call<List<SanPhamMoi>> GetGiayNu();
//
//    @GET("danhsachgiaydoi.php")
//    Call<List<SanPhamMoi>> GetGiayDoi();

    @FormUrlEncoded
    @POST("suanhanvien.php")
    Call<String> SuaNhanVien(@Field("id") int id,@Field("staff_name") String staff_name,@Field("staff_phone")String staff_phone,
                             @Field("staff_address")String staff_address,@Field("date_of_birth")String date_of_birth,
                             @Field("id_card_number")String id_card_number);
    @FormUrlEncoded
    @POST("khoatiakhoannhanvien.php")
    Call<String> KhoaTaiKhoan(@Field("id") int id);

    @FormUrlEncoded
    @POST("chitietsanpham.php")
    Call<List<ProductImage>> GetProductImage(@Field("idnhan")int idnhan);

    @FormUrlEncoded
    @POST("kiemtradangky.php")
    Call<String> checkRegister(@Field("customer_phone")String customer_phone);

    @FormUrlEncoded
    @POST("dangkytaikhoan.php")
    Call<String> dangKyTaiKhoan(@Field("customer_name")String hoTen,@Field("customer_phone")String soDT,
                                @Field("customer_address")String diaChi,@Field("user_password")String matKhau);
    @FormUrlEncoded
    @POST("login.php")
    Call<String> loGin(@Field("user_name")String taiKhoan,@Field("user_password")String matKhau);

    @FormUrlEncoded
    @POST("thongtinnguoidung.php")
    Call<List<ThongTinNV>> thongTinNhanVien(@Field("role")String role,@Field("phone")String phone);

    @FormUrlEncoded
    @POST("thongtinnguoidung.php")
    Call<List<ThongTinKhachHang>> thongTinKhachHang(@Field("role")String role,@Field("phone")String phone);

    @FormUrlEncoded
    @POST("doimatkhau.php")
    Call<String> doiMatKhau(@Field("id")String id,@Field("user_password")String user_password);

    @GET("danhsachkhachhang.php")
    Call<List<KhachHang>> danhSachKhachHang();

    @FormUrlEncoded
    @POST("timkiemkhachhang.php")
    Call<List<KhachHang>> timKiemKhachHang(@Field("staff_name")String ten);

    @FormUrlEncoded
    @POST("sizetheosanpham.php")
    Call<List<KichCoSP>> kichCoTheoSanPham(@Field("idnhan")int idnhan);

    @FormUrlEncoded
    @POST("danhsachsanpham.php")
    Call<List<SanPhamMoi>> timKiemSanPham(@Field("product_name")String tenSanPham);

    @FormUrlEncoded
    @POST("sanphamxemthem.php")
    Call<List<SanPhamMoi>> sanPhamXemThem(@Field("loaiSP")int loaiSP);

    //Thêm đơn nhập hàng
    @FormUrlEncoded
    @POST("themdonnhaphang.php")
    Call<String> maDonNhap(@Field("staff_id") int maNV);

    @FormUrlEncoded
    @POST("themchitietdonnhap.php")
    Call<String> chiTietDonNhap(@Field("donNhap_id")int idDonNhap,@Field("sanPham_id") int idSanPham,
                                @Field("soLuong") int soLuong,@Field("kichCo") String kichCo);

    //Hiện danh sách nhâp hàng theo trạng thái
    @FormUrlEncoded
    @POST("hiendanhsachnhaphang.php")
    Call<List<DonNhapHang>> danhSachDonNhap(@Field("status")int status);

    //Hiện chi tiết đơn nhập hàng
    @FormUrlEncoded
    @POST("hienchitietdonnhaphang.php")
    Call<List<ChiTietDonNhapLay>> layChiTietDonNhap(@Field("idDonNhap") int maDonNhap);

    //Duyệt đơn nhập hàng
    @FormUrlEncoded
    @POST("duyettrangthaidonnhap.php")
    Call<String> duyetDonNhapHang(@Field("idDonNhap") int maDonNhap,@Field("status") int trangThai);

    //cập nhật số lượng sán phẩm nhập trong chi tiết đơn nhập lấy về
    @FormUrlEncoded
    @POST("capnhatchitietdonnhaplaysl.php")
    Call<String> capNhatChiTietDNLay(@Field("idCTDN") int maChiTiet,@Field("quantity") int soLuong);

    //Thêm đơn hàng
    @FormUrlEncoded
    @POST("themdonhang.php")
    Call<String> themDonHang(@Field("address")String diaChi,@Field("customer_id")int idNguoiLap,@Field("role") int quyen);

    //Thêm chi tiết đơn hàng
    @FormUrlEncoded
    @POST("themchitietdonhang.php")
    Call<String> themChiTietDonHang(@Field("donHang_id") int maDonHang,@Field("sanPham_id") int maSanPham,
                                    @Field("soLuong")int soLuong,@Field("kichCo") String kichCo);

    //lấy mã người dùng lập đơn hàng
    @FormUrlEncoded
    @POST("getmanguoidunglapdonhang.php")
    Call<List<MaNguoiLapDH>> getMaNguoiLapDonHnag(@Field("phone")String phone, @Field("role")int quyen);

    //Show list order
    @FormUrlEncoded
    @POST("showlistorder.php")
    Call<List<DonHang>> showListOrder(@Field("maND") int maND,@Field("quyenND")int quyen,@Field("trangThaiDH") int trangThai);

    //show order detail
    @FormUrlEncoded
    @POST("getDataOrderDetail.php")
    Call<List<OrderDetail>> getDataOrderDetail(@Field("idDonHang")int maDH);

    //Hủy đơn hnagf
    @FormUrlEncoded
    @POST("huydonhang.php")
    Call<String> huyDonHang(@Field("idDonHang")int maDH);

    // Lấy danh sách đơn hàng kiểm tra admin
    @FormUrlEncoded
    @POST("admindsdonhangkiemtra.php")
    Call<List<DonHang>> showListOrder1(@Field("quyenND")int quyen,@Field("trangThaiDH") int trangThai);

    //Cập nhật trạng thái đơn hàng
    @FormUrlEncoded
    @POST("capnhattrangthaidonhang.php")
    Call<String> capNhatDonHang(@Field("idDonHang")int maDH,@Field("trangThai")int trangThai);

    //Lấy tổng tiền bán hàng trong tháng
    @FormUrlEncoded
    @POST("laydsdonhangtrongthangnam.php")
    Call<String> tinhTongTienBan(@Field("thang")String thang,@Field("nam")String nam);

    //Lấy tổng tiền nhập hàng trong tháng
    @FormUrlEncoded
    @POST("tongtiendonnhaphang.php")
    Call<String> tinhTongTienNhap(@Field("thang")String thang,@Field("nam")String nam);

    //Lấy danh sách sản phẩm bán được nhiều nhất trong tháng
    @FormUrlEncoded
    @POST("sanphambannhieuthongke.php")
    Call<List<SanPhamThongKe>> sanPhamBanNhieu(@Field("thang")String thang, @Field("nam")String nam);

    //Lấy danh sách sản phẩm bán được ít nhất trong tháng
    @FormUrlEncoded
    @POST("sanphambanitthongke.php")
    Call<List<SanPhamThongKe>> sanPhamBanIt(@Field("thang")String thang, @Field("nam")String nam);

    //Lấy tổng bán theo từng tháng thống kê năm
    @FormUrlEncoded
    @POST("thongketheonamtongban.php")
    Call<List<NamThongKe>> thongKeBan(@Field("nam")String nam);

    //Lấy tổng nhap theo từng tháng thống kê năm
    @FormUrlEncoded
    @POST("thongketheonamnhap.php")
    Call<List<NamThongKe>> thongKeNhap(@Field("nam")String nam);
}
