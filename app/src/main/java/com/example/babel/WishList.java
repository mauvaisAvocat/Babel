package com.example.babel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class WishList extends AppCompatActivity {
    private WishListAdapter adapter;
    //private SwipeRefreshLayout srlWishList;
    private SharedPreferences preferences;
    private String token;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        preferences = getSharedPreferences("babelapp", MODE_PRIVATE);

        //srlWishList = findViewById(R.id.srl_wishList);
        token = "Bearer " + preferences.getString("token", null);
        alert = new AlertDialog.Builder(WishList.this);
        RecyclerView recyclerView = findViewById(R.id.recycle_view_wishlist);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new WishListAdapter(this);
        recyclerView.setAdapter(adapter);

        getWishList();

       /*srlWishList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlWishList.setEnabled(false);
                getWishList();
            }
        });*/

    }

    private void getWishList(){
        Call<ArrayList<WishProduct>> call = ProductVetApiAdapter.getApiService().getWishList(token);
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


    public void btnContinuar(View v){
        startActivity(
                new Intent(
                        WishList.this, Products.class
                )
        );
    }

    public void btnVaciarWishList(View v){
        Call<Void> call = ProductVetApiAdapter.getApiService().emptywishlist(token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                alert.setTitle("Hey")
                        .setMessage("¿Quieres vaciar toda la wishList?")
                        .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(
                                        new Intent(WishList.this, WishList.class)
                                );
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .setIcon(R.drawable.babellogo)
                        .setCancelable(false)
                        .show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}