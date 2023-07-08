package com.dewairtzadhany_201011400483.uts_aplikasiresepmasakan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FriendsActivity extends AppCompatActivity {

    private Button btnGetData;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        listView = findViewById(R.id.listviewData);

                FriendsMethod methods = RetrofitClient.getRetrofitInstance().create(FriendsMethod.class);
                Call< FriendsModel > call = methods.getAllData();
                call.enqueue(new Callback< FriendsModel >() {
                    @Override
                    public void onResponse(Call < FriendsModel > call, Response< FriendsModel > response) {
                        ArrayList< FriendsModel.data > data = response.body().getData();
                        String[] names = new String[data.size()];
                        for (int i = 0; i < data.size(); i++) {
                            names[i] = "Nama : " + data.get(i).getFirst_name() + " " + data.get(i).getLast_name() + "\nEmail : " + data.get(i).getEmail();
                        }
                        listView.setAdapter(new ArrayAdapter< String >(getApplicationContext(), android.R.layout.simple_list_item_1, names));
                    }
                    @Override
                    public void onFailure(Call < FriendsModel > call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                    }
                });

    }
}