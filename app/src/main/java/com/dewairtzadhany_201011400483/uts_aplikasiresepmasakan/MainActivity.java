package com.dewairtzadhany_201011400483.uts_aplikasiresepmasakan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private DatabaseHandler databaseHelper;
    EditText username, password;
    SharedPreferences sharedPreferences;

    Button btnRegister;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        databaseHelper = new DatabaseHandler(this);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", "");
        username.setText(savedUsername);

        btnRegisterListener();
        btnLoginListener();
    }

    private void btnRegisterListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!databaseHelper.checkUser(username.getText().toString().trim())) {
                    UserModel user = new UserModel();
                    user.setUsername(username.getText().toString().trim());
                    user.setPassword(password.getText().toString().trim());
                    databaseHelper.addUser(user);
                    Toast.makeText(MainActivity.this, "User created successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void btnLoginListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameKey = username.getText().toString();
                if (databaseHelper.checkUser(username.getText().toString().trim(), password.getText().toString().trim())) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", usernameKey);
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("username", usernameKey);
                    MainActivity.this.startActivity(intent);
                    finish();
                    Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Username or password is wrong, please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}