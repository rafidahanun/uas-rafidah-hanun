package com.dewairtzadhany_201011400483.uts_aplikasiresepmasakan;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FriendsMethod {
    @GET("api/users?page=1")
    Call<FriendsModel> getAllData();
}
