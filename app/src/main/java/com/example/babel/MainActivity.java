package com.example.babel;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.babel.io.ProductVetApiAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor preferencesEditor;
    private String token;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alert = new AlertDialog.Builder(MainActivity.this);
    }

    private void logout(){
        preferences = getSharedPreferences("babelapp", MODE_PRIVATE);
        token = "Bearer " + preferences.getString("token", null);
        Call<Void> call = ProductVetApiAdapter.getApiService().logoutUser(token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    alert.setTitle("Hey!")
                            .setMessage("¿Quieres cerrar sesión?")
                            .setIcon(R.drawable.babellogo)
                            .setCancelable(false)
                            .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    preferencesEditor = preferences.edit();
                                    /* Limpiamos nuestras variables de preferencias del archivo local
                                     Para borrar uno por uno:
                                     preferencesEditor.remove("CLAVE")
                                     Para borrar todos
                                     */
                                    preferencesEditor.clear();
                                    preferencesEditor.commit();
                                    startActivity(
                                            new Intent(MainActivity.this, LoginActivity.class)
                                    );
                                }
                            })
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void btnDetailsProducts(View v) {
        startActivity(new Intent(this, Details.class));
    }

    public void btnMainProducts(View v) {
        startActivity(new Intent(this, Products.class));
    }

    public void btnMainLogin(View v) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btnMainLogout(View v) {
        logout();
    }

    public void btnMainWishList(View v) {
        startActivity(new Intent(this, WishList.class));
    }
}