package com.example.babel;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

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
        /* Vinculamos componentes */
        etName      = findViewById(R.id.usernameLogin);
        etPassword  = findViewById(R.id.passwordLogin);
        btnRegister = findViewById(R.id.registerLogin);
        btnLogin    = findViewById(R.id.log_login);
        alert = new AlertDialog.Builder(LoginActivity.this);
        servConection = Volley.newRequestQueue(LoginActivity.this);
    }

    public void btnLoginToRegister(View v) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void btnLoginDB(View v){
        /*TODO VALIDAR CAMPOS*/

        /* Ocultamos y moctramos elementos */
        etName.setVisibility(View.GONE);
        etPassword.setVisibility(View.GONE);
        btnRegister.setEnabled(false);
        btnLogin.setEnabled(false);

        /* Inicializamos la petición */

        servRequest = new StringRequest(
                Request.Method.POST,
                "https://babel-tee.azurewebsites.net/api/v1/login",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /* Apartir  de la cadena de respuesta, intentamos generar un archivo JSON */
                        try {
                            /* Creamos un pbjeto JSON */
                            JSONObject respuesta = new JSONObject(response);
                        }
                        catch (Exception e){

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "ERR: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        /* Agregamos a las peticiones del servidor */
        servConection.add(servRequest);
    }
}