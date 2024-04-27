package com.example.githubuserapp8.retrofit;

import com.example.githubuserapp8.response.GithubSearchResponse;
import com.example.githubuserapp8.response.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({"Authorization: token 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"})
    @GET("search/users")
    Call<GithubSearchResponse> searchUsers(@Query("q") String query);

    @Headers({"Authorization: token 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"})
    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

}
