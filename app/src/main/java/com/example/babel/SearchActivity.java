package com.example.babel;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

public class SearchActivity extends AppCompatActivity {
    private ProductsAdapter adapter;
    private String product;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        swipeRefreshLayout = findViewById(R.id.srl_search);
        product = getIntent().getStringExtra("product");
        System.out.println(product);
        RecyclerView recyclerView = findViewById(R.id.recycle_view_searching);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ProductsAdapter(this);
        recyclerView.setAdapter(adapter);

        searching();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setEnabled(false);
            }
        });
    }

    private void searching(){
        Call<ArrayList<ProductList>> call = ProductVetApiAdapter.getApiService().getSearch(product);
        call.enqueue(new Callback<ArrayList<ProductList>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductList>> call, Response<ArrayList<ProductList>> response) {
                if (response.isSuccessful()){
                    ArrayList<ProductList> busqueda = response.body();
                    Log.d("onResponseSearch", "Size searching => " + busqueda.size());
                    adapter.setDataSet(busqueda);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductList>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}