package com.example.babel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.babel.io.ProductVetApiAdapter;
import com.example.babel.ui.adapter.WishListAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishList extends AppCompatActivity {
    private WishListAdapter adapter;
    private SwipeRefreshLayout srlWishList;
    private int id_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        Intent intent = getIntent();
        id_product = intent.getIntExtra("id_product", 0);

        srlWishList = findViewById(R.id.srl_wishList);
        //String auth = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiYzY0NjAzMTk3MjFkNzhkZjMyMjc0YjY4YjQ1YzI3ZDU1NzMxOTBkOGNlYjJiMTc3YjUwZmI2YzI4OWE0YjNhZTQ5YTNlZDIyYjVmMmVlNWIiLCJpYXQiOjE2MDY2MDM3NTUsIm5iZiI6MTYwNjYwMzc1NSwiZXhwIjoxNjM4MTM5NzU1LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.etI7pS5Ydh_YrWXK-DczRxgqAV3J73-vS_xjmSMnfOedznibyGPdGzYs06XGFh6hWkqcVm0bHJuAadJNeCT7n2ZeWNghqBlp0MPX9x9Yw1pa9RJS_8tiWE22IRHUH2ymcmmZ29-X3l99SeBaFCWBv6JH3Mgb7CzJ7UY8paSfjIIX7M5RtGAr1bt1R5jZ1Ygw9zvVV-F-Y598xHeWr3GM8rig5WPzBeLbpebNfAl4c9ZwP1JfYH7TmNWgtmNezKmLnNwnoHyQAwk-_wqssZpMi56O9meku_Pwg0iNKktCgh1avXslVfbJ-T9_EbyXZwxwFCBqjF7ZhS0GxuMiWdUHS4rGOnilScScfuDEOvzUk3ZjHTmXvOExEWuZUoKEihPOdHUphB8KWdZa7aw0NVyV00b7YxmKECen77JTF0cLVk18xmjw7AASCP1oJQ9qotvqANcS612Nh6ZhvYb0ufXCMITXn_GazAdJxsyNroS0NSHSKtD3TQh3CGj4WuZws_Y47lNWy98m-yk7Ib8ctvd7JoEpii1-3oqP_rqq4Ht1UrUgWIO44MrF8aKYsEntWbalgUOgPlAuqldrvD6y7Z27F-2TcYHgBjyWatfLZqvBZ1AuXUoZtGMYbK9MrTWAWKKYT1T4ZdMFBp2iBflSkBxHgO9Qbkm_R2gm9e9Kphy_1MQ";
        RecyclerView recyclerView = findViewById(R.id.recycle_view_wishlist);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new WishListAdapter(this);
        recyclerView.setAdapter(adapter);

        //System.out.println(auth);
        getWishList();

        srlWishList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlWishList.setEnabled(false);
                getWishList();
            }
        });

        deleteWishProduct();

    }

    private void getWishList(){
        Call<ArrayList<WishProduct>> call = ProductVetApiAdapter.getApiService().getWishList();
        call.enqueue(new Callback<ArrayList<WishProduct>>() {
            @Override
            public void onResponse(Call<ArrayList<WishProduct>> call, Response<ArrayList<WishProduct>> response) {
                if (response.isSuccessful()){
                    ArrayList<WishProduct> wishlist = response.body();
                    adapter.setDataSet(wishlist);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<WishProduct>> call, Throwable t) {

            }
        });
    }

    private void deleteWishProduct(){
        Call<Void> call = ProductVetApiAdapter.getApiService().getWishListDelete(id_product);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(WishList.this, "Se ha eliminado de la WishList", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void btnContinuar(View v){
        startActivity(
                new Intent(
                        WishList.this, Products.class
                )
        );
    }

}