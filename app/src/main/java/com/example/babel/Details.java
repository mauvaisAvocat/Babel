package com.example.babel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babel.io.ProductVetApiAdapter;
import com.example.babel.ui.adapter.DetailsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details extends AppCompatActivity {

    private DetailsAdapter adapter;
    private TextView tvPago;
    private ImageView imgCard;
    private TextView tvEnvio;
    int product_id;
    private SharedPreferences preferences;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        product_id = intent.getIntExtra("product_id", 1);

        preferences = getSharedPreferences("babelapp", MODE_PRIVATE);
        token = "Bearer "  + preferences.getString("token", null);
        System.out.println(token);

        RecyclerView recyclerView = findViewById(R.id.recycle_view_details);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DetailsAdapter(this);
        recyclerView.setAdapter(adapter);

        tvPago = (TextView) findViewById(R.id.pago_tv);
        imgCard = (ImageView) findViewById(R.id.card_img);
        tvPago.setText("Contamos pagos en linea de la más alta seguridad a través de Stripe, " +
                "la pasarela de pago que presta los servicios más seguros y actuales " +
                "en el mercado, admitiendo alternativas como Google Pay, Paypal a través " +
                "de Google Pay y la gran mayoría de las tarjetas de crédito y débito del " +
                "mercado.");
        tvEnvio = (TextView) findViewById(R.id.envio_tv);
        tvEnvio.setText("Envíos a todo México\n" +
                "Costro apróximado:\n" +
                "$100 / envío\n" +
                "6 - 10 DÍAS HABILES");
        getDetails();
    }

    private void getDetails(){
        Call<ArrayList<DetailProduct>> call = ProductVetApiAdapter.getApiService().getDetails(product_id);
        call.enqueue(new Callback<ArrayList<DetailProduct>>() {
            @Override
            public void onResponse(Call<ArrayList<DetailProduct>> call, Response<ArrayList<DetailProduct>> response) {
                if (response.isSuccessful()){
                    ArrayList<DetailProduct> details = response.body();
                    adapter.setDataSet(details);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DetailProduct>> call, Throwable t) {

            }
        });
    }

    private void addWishProduct(){
        Call<Void> call = ProductVetApiAdapter.getApiService().addWishProduct(product_id, token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(Details.this, "Se ha añadido a wishlist", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void btnPago(View v) {
        if (imgCard.getVisibility() == View.INVISIBLE && tvPago.getVisibility() == View.INVISIBLE || imgCard.getVisibility() == View.GONE && tvPago.getVisibility() == View.GONE) {
            imgCard.setVisibility(View.VISIBLE);
            tvPago.setVisibility(View.VISIBLE);

        } else {
            imgCard.setVisibility(View.GONE);
            tvPago.setVisibility(View.GONE);
        }

    }

    public void btnEnvio(View v) {
        if (tvEnvio.getVisibility() == View.INVISIBLE) {
            tvEnvio.setVisibility(View.VISIBLE);

        } else {
            tvEnvio.setVisibility(View.INVISIBLE);
        }

    }

    public void btnAgregarWishlist(View v){
        addWishProduct();
    }

}