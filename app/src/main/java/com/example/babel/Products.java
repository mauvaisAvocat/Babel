package com.example.babel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Products extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);


    }

    public void btnDetails(View v){
        startActivity(
                new Intent(
                        Products.this, Details.class
                )
        );
    }
}