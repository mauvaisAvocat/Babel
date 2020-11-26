
package com.example.babel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

import static com.example.babel.Tools.ANSI_RESET;
import static com.example.babel.Tools.ANSI_YELLOW;

public class Products extends AppCompatActivity implements Callback<ArrayList<ProductList>> {

    private SwipeRefreshLayout strProduct;
    private ProductsAdapter adapter;
    private TextView tvIdProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        tvIdProduct = findViewById(R.id.tv_details_id);
        strProduct = findViewById(R.id.srl_product);
        RecyclerView recyclerView = findViewById(R.id.recycle_view_products);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ProductsAdapter(this);
        recyclerView.setAdapter(adapter);

        Call<ArrayList<ProductList>> call = ProductVetApiAdapter.getApiService().getProducts();
        call.enqueue(this);

        Tools.cleanScreen();
        System.out.println(ANSI_YELLOW + "caca 1........................................" + ANSI_RESET);
        System.out.println(call.toString());

        strProduct.setOnRefreshListener(() -> {
            Tools.cleanScreen();
            System.out.println(ANSI_YELLOW + "refresh 1........................................" + ANSI_RESET);

            // Quitamos loader
            strProduct.setRefreshing(false);
        });
    }

    public void btnDetails(View v) {
        Intent i = new Intent(Products.this, Details.class);
        //i.putExtra("id_product", tvIdProduct.getText().toString());
        startActivity(i);
        //startActivity(new Intent(Products.this, Details.class));
    }

    @Override
    public void onResponse(@NonNull Call<ArrayList<ProductList>> call, Response<ArrayList<ProductList>> response) {
        Tools.cleanScreen();
        System.out.println(ANSI_YELLOW + "OnResponse........................................" + ANSI_RESET);


        if (response.isSuccessful()){
            ArrayList<ProductList> products = response.body();
            assert products != null;
            Log.d("onRespondeProducts", "Size of products => " + products.size());
            adapter.setDataSet(products);
        }
    }

    @Override
    public void onFailure(@NonNull Call<ArrayList<ProductList>> call, @NonNull Throwable t) {
        Tools.cleanScreen();
        System.out.println(ANSI_YELLOW + "OnFailure........................................" + ANSI_RESET);

    }
}