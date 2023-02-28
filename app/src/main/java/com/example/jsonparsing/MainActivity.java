package com.example.jsonparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mNameTextView;
    private TextView mAgeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameTextView = findViewById(R.id.name_text_view);
        mAgeTextView = findViewById(R.id.age_text_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderAPI api = retrofit.create(JsonPlaceholderAPI.class);

        Call<User> call = api.getUser();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    mNameTextView.setText("Code: " + response.code());
                    return;
                }

                User user = response.body();

                mNameTextView.setText("Name: " + user.getName());
                mAgeTextView.setText("Age: " + user.getAge());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mNameTextView.setText(t.getMessage());
            }
        });
    }
}