package com.example.babel;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.babel.io.ProductVetApiAdapter;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor preferencesEditor;
    private String token;
    private AlertDialog.Builder alert;
    private CircleImageView circleImageView;
    private TextView tvUserName;
    //private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("babelapp", MODE_PRIVATE);
        String name = preferences.getString("name", null);
        String profilePicture = preferences.getString("profilePicture", null);
        circleImageView = findViewById(R.id.img_profile);
        //imageView = findViewById(R.id.img_main);
        tvUserName = findViewById(R.id.username_main);
        tvUserName.setText(name);
        Picasso.get()
                .load(profilePicture)
                .error(R.drawable.nube)
                .placeholder(R.drawable.relojarena)
                .fit()
                .into(circleImageView);
        alert = new AlertDialog.Builder(MainActivity.this);
    }

    private void logout(){
        preferences = getSharedPreferences("babelapp", MODE_PRIVATE);
        token = "Bearer " + preferences.getString("token", null);
        Call<Void> call = ProductVetApiAdapter.getApiService().logoutUser(token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    alert.setTitle("Hey!")
                            .setMessage("¿Quieres cerrar sesión?")
                            .setIcon(R.drawable.babellogo)
                            .setCancelable(false)
                            .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    preferencesEditor = preferences.edit();
                                    /* Limpiamos nuestras variables de preferencias del archivo local
                                     Para borrar uno por uno:
                                     preferencesEditor.remove("CLAVE")
                                     Para borrar todos
                                     */
                                    preferencesEditor.clear();
                                    preferencesEditor.commit();
                                    startActivity(
                                            new Intent(MainActivity.this, LoginActivity.class)
                                    );
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void btnDetailsProducts(View v) {
        Intent i = new Intent(MainActivity.this, Details.class);
        i.putExtra("product_id", 8);
        startActivity(i);
    }

    public void btnMainProducts(View v) {
        startActivity(new Intent(this, Products.class));
    }

   public void btnAddWL(View v){
        preferences = getSharedPreferences("babelapp", MODE_PRIVATE);
        token = "Bearer " + preferences.getString("token", null);
        Call<Void> call = ProductVetApiAdapter.getApiService().addWishProduct(8, token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MainActivity.this, "Se ha añadido a wishlist", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
   }

    public void btnMainLogout(View v) {
        logout();
    }

    public void btnMainWishList(View v) {
        startActivity(new Intent(this, WishList.class));
    }
}