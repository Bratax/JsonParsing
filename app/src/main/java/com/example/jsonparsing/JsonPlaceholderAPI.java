package com.example.jsonparsing;

import retrofit2.Call;
import retrofit2.http.GET;

public class JsonPlaceholderAPI {
    @GET("users/1")
    Call<User> getUser() {
        return null;
    }
}
