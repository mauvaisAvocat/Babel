package com.example.babel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

    /* Variable de preferencias locales */
    private SharedPreferences preferences;

    /* Editar las preferencias locales */
    private SharedPreferences.Editor preferencesEditor;
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

        /* Si ya existe algun valor en las preferencias locales enviamos al home */
        //preferences = getSharedPreferences("babelapp", MODE_PRIVATE);

        /* Sino tenemos el valor de las preferencias, debemos indicar cual será el valor defecto */
        /*int id = preferences.getInt("id", 0);
        String token = preferences.getString("token", null);
        if (id != 0 && token != null){
            startActivity(
                    new Intent(
                            LoginActivity.this, MainActivity.class
                    )
            );
        }*/
    }

    private void getUser(){
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        Call<Login> call = ProductVetApiAdapter.getApiService().getUser(email, password);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()){
                    Login user = response.body();
                    /* Guardamos las preferencias de manera local
                    * Las preferencias se guardan en un espacio de trabajo
                    * 1. Nombre del espacio de trabajo
                    * 2. Modo de uso (privado/ publico/ compartido) */
                    preferences = getSharedPreferences("babelapp", MODE_PRIVATE);
                    /* Editamos las preferencias dentro de mi espacio */
                    preferencesEditor = preferences.edit();
                    /* Agregamos el id y token del usuario en nuestro espacio de variables */
                    preferencesEditor.putInt("id", user.getId());
                    preferencesEditor.putString("token", user.getToken());
                    /* Escribimos los cambios en el archivo de configuración */
                    preferencesEditor.commit();
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