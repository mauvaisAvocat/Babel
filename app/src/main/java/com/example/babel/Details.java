package com.example.babel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Details extends AppCompatActivity {

    private TextView tvPago;
    private ImageView imgCard;
    private TextView tvEnvio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Spinner spinner = (Spinner) findViewById(R.id.sizes_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sizes_array,
                android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        tvPago = findViewById(R.id.pago_tv);
        imgCard = (ImageView)findViewById(R.id.card_img);
        tvPago.setText("Contamos pagos en linea de la más alta seguridad a través de Stripe, " +
                "la pasarela de pago que presta los servicios más seguros y actuales " +
                "en el mercado, admitiendo alternativas como Google Pay, Paypal a través " +
                "de Google Pay y la gran mayoría de las tarjetas de crédito y débito del " +
                "mercado.");
        tvEnvio = findViewById(R.id.envio_tv);
        tvEnvio.setText("Envíos a todo México\n" +
                        "Costro apróximado:\n" +
                        "$100 / envío\n" +
                        "6 - 10 DÍAS HABILES");
    }

    public void btnPago(View v){
        if (imgCard.getVisibility() == View.INVISIBLE && tvPago.getVisibility() == View.INVISIBLE){
            imgCard.setVisibility(View.VISIBLE);
            tvPago.setVisibility(View.VISIBLE);

        }else {
            imgCard.setVisibility(View.INVISIBLE);
            tvPago.setVisibility(View.INVISIBLE);
        }

    }

    public void btnEnvio(View v){
        if (tvEnvio.getVisibility() == View.INVISIBLE){
            tvEnvio.setVisibility(View.VISIBLE);

        }else {
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
}