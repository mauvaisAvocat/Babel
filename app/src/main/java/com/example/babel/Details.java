package com.example.babel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babel.io.ProductVetApiAdapter;
import com.example.babel.ui.adapter.DetailProduct;
import com.example.babel.ui.adapter.DetailsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details extends AppCompatActivity implements Callback<ArrayList<DetailProduct>> {

    private DetailsAdapter adapter;
    private TextView tvPago;
    private ImageView imgCard;
    private TextView tvEnvio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        RecyclerView recyclerView = findViewById(R.id.recycle_view_details);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DetailsAdapter(this);
        recyclerView.setAdapter(adapter);

        Call<ArrayList<DetailProduct>> call = ProductVetApiAdapter.getApiService().getDetails();
        call.enqueue(this);

        Intent intent = getIntent();
        int product_id = (int)intent.getDoubleExtra("product_id", 0);
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

    public void btnCarrito(View v){
        startActivity(
                new Intent(
                        Details.this, WishList.class
                )
        );
    }

    @Override
    public void onResponse(Call<ArrayList<DetailProduct>> call, Response<ArrayList<DetailProduct>> response) {
        if (response.isSuccessful()){
            ArrayList<DetailProduct> details = response.body();
            Log.d("onRespondeDetails", "Size of details => " + details.size());
            adapter.setDataSet(details);
        }
    }

    @Override
    public void onFailure(Call<ArrayList<DetailProduct>> call, Throwable t) {
        System.out.println("OnFailure........................................");
        System.out.println(t.getMessage());
    }
}