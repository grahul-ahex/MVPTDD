package com.ahextech.mpvtdd.network;

import com.ahextech.mpvtdd.network.POJO.LoginModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.ahextech.mpvtdd.network.Constants.BASE_URL;

/**
 * Created by ahextech on 28/2/18.
 */

public interface APIService {

    @POST(BASE_URL)
    Call<LoginModel> authenticate(@Body LoginModel loginModel);

}
