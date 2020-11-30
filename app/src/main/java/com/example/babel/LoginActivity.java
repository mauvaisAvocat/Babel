package com.example.babel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.babel.io.ProductVetApiAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;
    private AlertDialog.Builder alert;
    private String email;
    private String password;
    private Button btnLogin;

    /*private RequestQueue requestQueue;
    private StringRequest stringRequest;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.usernameLogin);
        etPassword = findViewById(R.id.passwordLogin);


        alert = new AlertDialog.Builder(LoginActivity.this);
        btnLogin = (Button) findViewById(R.id.login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUser();
            }
        });
    }

    private void getUser(){
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        System.out.println(email + " " + password);
        Call<Login> call = ProductVetApiAdapter.getApiService().getUser(email, password);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()){
                    Login user = response.body();
                    alert.setTitle("WTF")
                            .setMessage(user.toString())
                            .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(
                                            new Intent(
                                                    LoginActivity.this, MainActivity.class
                                            )
                                    );
                                }
                            })
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });
    }

    public void btnLoginToRegister(View v) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

}