package com.example.babel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babel.io.ProductVetApiAdapter;
import com.example.babel.ui.adapter.ProductsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private ProductsAdapter adapter;
    private String product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        product = getIntent().getStringExtra("product");
        System.out.println(product);
        RecyclerView recyclerView = findViewById(R.id.recycle_view_searching);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ProductsAdapter(this);
        recyclerView.setAdapter(adapter);

        searching();
    }

    private void searching(){
        Call<ArrayList<ProductList>> call = ProductVetApiAdapter.getApiService().getsearch(product);
        call.enqueue(new Callback<ArrayList<ProductList>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductList>> call, Response<ArrayList<ProductList>> response) {
                if (response.isSuccessful()){
                    ArrayList<ProductList> busqueda = response.body();
                    adapter.setDataSet(busqueda);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductList>> call, Throwable t) {

            }
        });
    }
}