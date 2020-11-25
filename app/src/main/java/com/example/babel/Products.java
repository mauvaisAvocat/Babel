
package com.example.babel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.babel.io.ProductVetApiAdapter;
import com.example.babel.ui.adapter.ProductsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Products extends AppCompatActivity implements Callback<ArrayList<ProductList>> {

    private SwipeRefreshLayout strProduct;
    private RecyclerView recyclerView;
    private ProductsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        strProduct = findViewById(R.id.srl_product);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view_products);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ProductsAdapter(this);
        recyclerView.setAdapter(adapter);

        Call<ArrayList<ProductList>> call = ProductVetApiAdapter.getApiService().getProducts();
        call.enqueue(this);

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

    @Override
    public void onResponse(Call<ArrayList<ProductList>> call, Response<ArrayList<ProductList>> response) {
        if (response.isSuccessful()){
            ArrayList<ProductList> products = response.body();
            Log.d("onRespondeProducts", "Size of products => " + products.size());
            adapter.setDataSet(products);
        }
    }

    @Override
    public void onFailure(Call<ArrayList<ProductList>> call, Throwable t) {

    }
}