package com.example.babel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WishList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void btnContinuar(View v){
        startActivity(
                new Intent(
                        WishList.this, Products.class
                )
        );
    }
}