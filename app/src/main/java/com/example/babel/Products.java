package com.example.babel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity {

    private ListView lvProduct;
    private SwipeRefreshLayout strProduct;
    private List<ProductList> collection;
    private ProductAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        lvProduct = findViewById(R.id.lv_products);
        strProduct = findViewById(R.id.srl_product);

        collection = new ArrayList<>();

        for (int i = 0; i  < 12; i++){
            ProductList pl = new ProductList();
            pl.setUrlImagen("https://nosomosnonos.com/wp-content/uploads/2019/11/Bob-Esponja-al-rescate.jpg");
            pl.setNombre("BOB SQUAREPANTS | BABEL TEE-SHIRT");
            pl.setPrecio(350);
            collection.add(pl);
        }
        /*
        Inicializamos el adaptador
         */
        adapter = new ProductAdapter(
                Products.this,
                collection
        );
        lvProduct.setAdapter(adapter);

        strProduct.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Quitamos loader
                strProduct.setRefreshing(false);
            }
        });
    }

    public void btnDetails(View v) {
        startActivity(new Intent(Products.this, Details.class));
    }
}