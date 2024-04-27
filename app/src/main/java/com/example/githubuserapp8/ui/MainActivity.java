package com.example.githubuserapp8.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubuserapp8.R;
import com.example.githubuserapp8.response.GithubSearchResponse;
import com.example.githubuserapp8.response.User;
import com.example.githubuserapp8.retrofit.ApiConfig;
import com.example.githubuserapp8.retrofit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterRecycle adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        ApiService apiService = ApiConfig.getApiService();
        Call<GithubSearchResponse> call = apiService.searchUsers("Gunt");

        call.enqueue(new Callback<GithubSearchResponse>() {
            @Override
            public void onResponse(Call<GithubSearchResponse> call, Response<GithubSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> users = response.body().getUsers();
                    adapter = new AdapterRecycle(users);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } else {
                    Toast.makeText(MainActivity.this, "Tidak Mendapatkan User", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GithubSearchResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
