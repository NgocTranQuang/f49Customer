package com.vn.f49kh.service

import com.app.f49.model.login.LoginDTO
import com.vn.f49kh.model.BaseResponse
import com.vn.f49kh.model.CuaHangDTO
import com.vn.f49kh.model.chitiet.ChiTietDTO
import com.vn.f49kh.model.chitiet.ChiTietDongLaiDTO
import com.vn.f49kh.model.dinhgiataisan.UploadImageDTO
import com.vn.f49kh.model.image.ImageDTO
import com.vn.f49kh.model.taisan.TaiSanTypeDTO
import io.reactivex.Observable
import retrofit2.http.*


interface

ApiService {
    companion object {
        const val API_LOGIN = "api/Login/"
        const val API_TAISAN = "api/TaiSan/"
        const val API_DONG_LAI = "api/DongLai/"
        const val API_MOBILE_APP = "api/MobileApp/"
        const val API_BANNER_APP = "api/BannerApp/"
        const val API_HANG_THANH_LY = "api/HangThanhLy/"
        const val API_FIREBASE = "api/ManageFireBase/"

    }

    /**
     * Login
     */
    @FormUrlEncoded
    @POST("token")
    fun login(@Field("grant_type") grant_type: String, @Field("username") username: String, @Field("password") password: String): Observable<LoginDTO>


    /*Upload image*/
    @POST(API_MOBILE_APP + "UploadImage")
    fun uploadImage(@Body rq: UploadImageDTO): Observable<BaseResponse<String?>>

    @PUT(API_MOBILE_APP + "PutDangKyCamDo")
    fun putDangKyCamdo(
        @Query("balance") balance: Double,
        @Query("fullName") fullName: String,
        @Query("asset") asset: String,
        @Query("brand") brand: String,
        @Query("phone") phone: String,
        @Query("description") description: String
    ): Observable<BaseResponse<Boolean?>>

    @PUT(API_MOBILE_APP + "PutCamDoGiaDung")
    fun putCamDoGiaDung(
        @Query("fullName") fullName: String,
        @Query("phone") phone: String,
        @Query("infoProduct") description: String
    ): Observable<BaseResponse<Boolean?>>

    @PUT(API_MOBILE_APP + "PutDinhGiaTaiSan")
    fun putDinhGiaTaiSan(
        @Query("infoProduct") fullName: String,
        @Query("fullName") phone: String,
        @Query("phone") description: String,
        @Query("imgPath") imgPath: String
    ): Observable<BaseResponse<Boolean?>>


    @GET(API_MOBILE_APP + "GetLoaiTaiSan")
    fun getLoaiTaiSan(): Observable<BaseResponse<MutableList<TaiSanTypeDTO>?>>

    @GET(API_MOBILE_APP + "GetDanhSachCuaHang")
    fun getDanhSachCuaHang(): Observable<BaseResponse<MutableList<CuaHangDTO>?>>

    @PUT(API_LOGIN + "PutChangePassWord")
    fun changePassword(
        @Query("cmnd") cmnd: String,
        @Query("oldPassword") oldPassword: String,
        @Query("newPassword") newPassword: String
    ): Observable<BaseResponse<Boolean?>>


    @GET(API_TAISAN + "GetDanhSachTaiSan")
    fun getDanhSachTaiSan(@Query("idKhachHang") idKhachHang: Int?): Observable<BaseResponse<MutableList<TaiSanTypeDTO>?>>

    @GET(API_DONG_LAI + "GetDanhHopDongDongLai")
    fun getDanhSachDongLai(@Query("soCMND") soCMND: String?): Observable<BaseResponse<MutableList<TaiSanTypeDTO>?>>

    @GET(API_DONG_LAI + "GetChiTietHopDong")
    fun getChiTietDongLai(@Query("idHopDong") idHopDong: Int?): Observable<BaseResponse<ChiTietDongLaiDTO?>>


    @GET(API_HANG_THANH_LY + "GetDanhSachHangThanhLy")
    fun getDanhSachHangThanhLy(): Observable<BaseResponse<MutableList<TaiSanTypeDTO>?>>

//    @GET(API_HANG_THANH_LY + "GetChiTietHangThanhLy")
//    fun getChiTietThanhLy(@Query("idHangThanhLy") idHangThanhLy: Int?): Call<ResponseBody>

    @GET(API_HANG_THANH_LY + "GetChiTietHangThanhLy")
    fun getChiTietThanhLy(@Query("idHangThanhLy") idHangThanhLy: Int?): Observable<BaseResponse<ChiTietDTO?>>

    @PUT(API_MOBILE_APP + "LienHe")
    fun putLienHe(
        @Query("fullName") fullName: String?, @Query("province") province: String?, @Query("phone") phone: String?, @Query(
            "email"
        ) email: String?, @Query("context") context: String?
    ): Observable<BaseResponse<Boolean?>>

    @GET(API_MOBILE_APP + "GetDanhSachHangTheoHangMuc")
    fun getDanhMucSanPham(@Query("typeLoaiTaiSan") typeLoaiTaiSan: String, @Query("keyWord") keyWord: String?): Observable<BaseResponse<MutableList<String>?>>

    @GET(API_BANNER_APP + "GetImage")
    fun getBannerImage(): Observable<BaseResponse<MutableList<ImageDTO>?>>

    /*
    * Push firebase token
    * */

    @PUT(API_FIREBASE + "PutFireBase")
    fun pushFirebaseToken(@Query("soCMND") email: String?, @Query("token") token: String?, @Query("deviceName") deviceName: String?, @Query("flg") flg: Boolean?): Observable<BaseResponse<Int>>

}
