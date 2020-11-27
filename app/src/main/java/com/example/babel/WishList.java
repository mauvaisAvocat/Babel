package com.example.babel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babel.io.ProductVetApiAdapter;
import com.example.babel.ui.adapter.WishListAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishList extends AppCompatActivity implements Callback<ArrayList<WishProduct>> {
    private WishListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        String auth = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiYzc0ZjJjNWQ0ZjRhNjc0ODEzNWIxMTlhYzZjZDU2ODQ2MTJhMjUwNWZmMDliNDM0YzQ5YjQwNDMxZjM3YTk5NzllOGZlYWYzZWM0OWM3NzIiLCJpYXQiOjE2MDY0MzA4NzUsIm5iZiI6MTYwNjQzMDg3NSwiZXhwIjoxNjM3OTY2ODc1LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.ng2QASJ4Rv7ghfVQ3daL29YsoVGAa5XTmTPQo-xTWthGT-nhoJBYCxZegUneVQf5TXaUZPQXnabUjXzNVfVVeBdPFO6cItXLK_6W4CSL9jRZ0xiQLQ2UwhJGOEP-t8BOfjRDDR_myDDnoDhj3ZgmYFH1H5P0A4V_O700UWgjEh-SWbVtUPO2wtd5YZzcQQFauezKYIODzrtHx0mWahAZc9eDOdYX54zO2fV4QxYywpTfxHxcLCsgwQv3aBo5n2V6VSgQuJXQ5fcf2rOgnpsGzdyOjMCaf2Emq8qo5jAq5w-jychLGSF5roi3cdQMwNuJCJO56WFe0QoJj7N2M7EAyRZr50H52coYRWqMpWbBCM-r4WbwzFw6nDn3_uYnK6nst_MEN0et_PRe8q4m-mnHFxdAmrIVg0KlzPIzOpyBreLf5EwJeWDyBMzLvI9AaBDyeML7k0CNbOcLPr_HZlyWBIpe9pZ32zisceBdXwdGj3FeHJ6RM9c7C7VOEuTCVuR7kjLd2xtTsq9vYe7ZqLCqkedRNtqXNe2mF6WP_vDQuY_-zxDDwRvSOaHLe12spRbmTV20KFavGt47n9okcxmMYwIt8h-RZByR63yKyyMqEYPPUbulo13d0MbZh58zGKINzTeaB_0Pfdy26IwmX-OPj434OKWgmhekjvniiwc9IQY";

        RecyclerView recyclerView = findViewById(R.id.recycle_view_wishlist);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new WishListAdapter(this);
        recyclerView.setAdapter(adapter);

        System.out.println(auth);

        Call<ArrayList<WishProduct>> call = ProductVetApiAdapter.getApiService().getWishList(auth);
        call.enqueue(this);
    }

    public void btnContinuar(View v){
        startActivity(
                new Intent(
                        WishList.this, Products.class
                )
        );
    }

    @Override
    public void onResponse(Call<ArrayList<WishProduct>> call, Response<ArrayList<WishProduct>> response) {
        if (response.isSuccessful()){
            ArrayList<WishProduct> wishList = response.body();
            Log.d("onResponseWishList", "Size of WishList => " + wishList.size());
            adapter.setDataSet(wishList);
        }
    }

    @Override
    public void onFailure(Call<ArrayList<WishProduct>> call, Throwable t) {
        System.out.println(t.getMessage());
    }
}