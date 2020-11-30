package com.example.babel;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.babel.io.ProductVetApiAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

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

    }

    public void btnRegisterToMain(View v) {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }

    public void btnRegisterDB(View v) {
        /* Variables que necesitaremos para los parámetros */
        String name, ap, am, email, password;
        name = etName.getText().toString();
        ap = etmiddleName.getText().toString();
        am = etsurName.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        /* Ocultamos los datos del formulario */
        etName.setVisibility(View.GONE);
        llSurname.setVisibility(View.GONE);
        etEmail.setVisibility(View.GONE);
        llPassword.setVisibility(View.GONE);

        /* Invalidamos los botones */
        btnRegister.setEnabled(false);
        btnBackMian.setEnabled(false);

        if (password.trim().length() < 8 || etConfirmPass.getText().toString().trim().length() < 8){
            alert.setTitle("Hey")
                    .setMessage("Password debe contener 8 carácteres como minímo")
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
            // Enviar cursor a ese campo
            etPassword.setText("");
            etConfirmPass.setText("");
            etPassword.requestFocus();
            return;
        }

        if (etPassword.getText().toString().equals(etConfirmPass.getText().toString())) {

            Call<Void> call = ProductVetApiAdapter.getApiService().addRegister(name, ap, am, email, password);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show();
                        startActivity(
                                new Intent(
                                        RegisterActivity.this, LoginActivity.class
                                )
                        );
                    }
                    alert.setTitle("Hey")
                            .setMessage("Ya esta previamente registrado ese email, ingrese uno distinto porfavor")
                            .setNeutralButton("Aceptar", null)
                            .setIcon(R.drawable.babellogo)
                            .setCancelable(false)
                            .show();
                    etName.setVisibility(View.VISIBLE);
                    llSurname.setVisibility(View.VISIBLE);
                    etEmail.setVisibility(View.VISIBLE);
                    llPassword.setVisibility(View.VISIBLE);
                    btnBackMian.setEnabled(true);
                    btnRegister.setEnabled(true);
                    etEmail.setText("");
                    etEmail.requestFocus();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        }else {
            alert.setTitle("Hey!")
                    .setMessage("Passwords diferentes")
                    .setNeutralButton("Aceptar", null)
                    .setIcon(R.drawable.babellogo)
                    .setCancelable(false)
                    .show();

            etName.setVisibility(View.VISIBLE);
            llSurname.setVisibility(View.VISIBLE);
            etEmail.setVisibility(View.VISIBLE);
            llPassword.setVisibility(View.VISIBLE);
            btnBackMian.setEnabled(true);
            btnRegister.setEnabled(true);
            etPassword.setText("");
            etConfirmPass.setText("");
            etPassword.requestFocus();
            return;
        }
    }
}