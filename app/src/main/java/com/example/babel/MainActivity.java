package com.example.babel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void btnMainRegister(View v) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void btnMainWishList(View v) {
        startActivity(new Intent(this, WishList.class));
    }
}