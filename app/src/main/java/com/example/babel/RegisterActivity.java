package com.example.babel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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

public class RegisterActivity extends AppCompatActivity {
    private final String URL = "https://babel-tee.azurewebsites.net/api/v1/register";

    // Componentes de la vista de Layout
    private LinearLayout llSurname;
    private LinearLayout llPassword;
    private EditText etName;
    private EditText etmiddleName;
    private EditText etsurName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPass;
    private AlertDialog.Builder alert;
    private Button btnRegister;
    private Button btnBackMian;

    // Componentes para conectarme remotamente a un servicio
    private RequestQueue seryConection;
    private StringRequest seryRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        llSurname = findViewById(R.id.apellidosDiv);
        llPassword = findViewById(R.id.passwordsContainer);

        etName = (EditText) findViewById(R.id.username);
        etmiddleName = (EditText) findViewById(R.id.middleName);
        etsurName = (EditText) findViewById(R.id.lastName);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        etConfirmPass = (EditText) findViewById(R.id.confirmPassword);

        btnRegister = findViewById(R.id.login);
        btnBackMian = findViewById(R.id.register);

        alert = new AlertDialog.Builder(RegisterActivity.this);
        seryConection = Volley.newRequestQueue(RegisterActivity.this);

    }

    public void btnRegisterToMain(View v) {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }

    public void btnRegisterDB(View v) {
        /* Ocultamos los datos del formulario */
        etName.setVisibility(View.GONE);
        llSurname.setVisibility(View.GONE);
        etEmail.setVisibility(View.GONE);
        llPassword.setVisibility(View.GONE);

        /* Invalidamos los botones */
        btnRegister.setEnabled(false);
        btnBackMian.setEnabled(false);

        if (etPassword.getText().toString().equals(etConfirmPass.getText().toString())) {
            seryRequest = new StringRequest(
                    Request.Method.POST,
                    URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            alert.setTitle("Bienvenido")
                                    .setMessage("Registro completado")
                                    .setNeutralButton("Continuar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            startActivity(
                                                    new Intent(
                                                            RegisterActivity.this,
                                                            LoginActivity.class
                                                    )
                                            );
                                        }
                                    })
                                    .setCancelable(false)
                                    .setIcon(R.drawable.babellogo)
                                    .show();

                            etName.setVisibility(View.VISIBLE);
                            llSurname.setVisibility(View.VISIBLE);
                            etEmail.setVisibility(View.VISIBLE);
                            llPassword.setVisibility(View.VISIBLE);
                            btnBackMian.setEnabled(true);
                            btnRegister.setEnabled(true);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            /* Mostramos el error que exista */
                            alert.setTitle("ERROR INESPERADO")
                                    .setMessage(error.toString())
                                    .setNeutralButton("Aceptar", null)
                                    .setCancelable(false)
                                    .setIcon(R.drawable.babellogo)
                                    .show();
                            etName.setVisibility(View.VISIBLE);
                            llSurname.setVisibility(View.VISIBLE);
                            etEmail.setVisibility(View.VISIBLE);
                            llPassword.setVisibility(View.VISIBLE);
                            btnBackMian.setEnabled(true);
                            btnRegister.setEnabled(true);
                        }
                    }
            ) {
                /* Enviamos los parámetros a PHP con los nombres y valores que el servicio que necesite */
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    /* Usando el método put vamos a indicar las variables del servicio */
                    params.put("name", etName.getText().toString());
                    params.put("ap", etmiddleName.getText().toString());
                    params.put("am", etsurName.getText().toString());
                    params.put("email", etEmail.getText().toString());
                    params.put("password", etPassword.getText().toString());
                    return params;
                }
            };
            /* Ejecutamos la petición desde el servidor */
            seryConection.add(seryRequest);
        } else {
            etName.setText("");
            etmiddleName.setText("");
            etsurName.setText("");
            etEmail.setText("");
            etPassword.setText("");

            alert.setTitle("ERROR INESPERADO")
                    .setMessage("Las contraseñas deben de coíncidir")
                    .setNeutralButton("Aceptar", null)
                    .setCancelable(false)
                    .setIcon(R.drawable.babellogo)
                    .show();

            etName.setVisibility(View.VISIBLE);
            llSurname.setVisibility(View.VISIBLE);
            etEmail.setVisibility(View.VISIBLE);
            llPassword.setVisibility(View.VISIBLE);
            btnBackMian.setEnabled(true);
            btnRegister.setEnabled(true);
        }
    }
}