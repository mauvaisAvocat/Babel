package com.example.babel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private final String URL = "https://babel-tee.azurewebsites.net/api/v1/login";

    /* Componentes gráficos (xml) */
    private EditText etName;
    private EditText etPassword;
    private Button btnRegister;
    private Button btnLogin;
    private AlertDialog.Builder alert;

    /* Elementos conexión remota  */
    private RequestQueue servConection;
    private StringRequest servRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.usernameLogin);
        etPassword = findViewById(R.id.passwordLogin);

        alert = new AlertDialog.Builder(LoginActivity.this);


        requestQueue = Volley.newRequestQueue(LoginActivity.this);

    }

    public void btnLoginToRegister(View v) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void btnLoginRequest(View v) {
        stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        alert.setTitle("Bienvenido")
                                .setMessage(response)
                                .setNeutralButton("Continuar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startActivity(
                                                new Intent(
                                                        LoginActivity.this,
                                                        MainActivity.class
                                                )
                                        );
                                    }
                                })
                                .setCancelable(false)
                                .setIcon(R.drawable.babellogo)
                                .show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", etEmail.getText().toString());
                params.put("password", etPassword.getText().toString());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}