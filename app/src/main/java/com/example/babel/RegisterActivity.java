package com.example.babel;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Widgets
    EditText etFecha;
    ImageButton ibObtenerFecha;

    // Componentes de la vista de Layout
    private ConstraintLayout constraintLayoutFormulary;
    private EditText etName;
    private EditText etmiddleName;
    private EditText etsurName;
    private Spinner spinner;
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

        constraintLayoutFormulary   = findViewById(R.id.res_container);
        etName                      = findViewById(R.id.username);
        etmiddleName                = findViewById(R.id.middleName);
        etsurName                   = findViewById(R.id.lastName);
        etEmail                     = findViewById(R.id.email);
        etPassword                  = findViewById(R.id.password);
        etConfirmPass               = findViewById(R.id.confirmPassword);
        btnRegister                 = findViewById(R.id.loginButton);
        btnBackMian                 = findViewById(R.id.registerButton);

        //Widget EditText donde se mostrara la fecha obtenida
        etFecha = (EditText) findViewById(R.id.et_mostrar_fecha_picker);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        ibObtenerFecha = (ImageButton) findViewById(R.id.ib_obtener_fecha);
        //Evento setOnClickListener - clic
        ibObtenerFecha.setOnClickListener(this);


        spinner = (Spinner) findViewById(R.id.spinner1);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sexArray, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        alert           = new AlertDialog.Builder(RegisterActivity.this);
        seryConection   = Volley.newRequestQueue(RegisterActivity.this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ib_obtener_fecha) {
            obtenerFecha();
        }
    }

    private void obtenerFecha() {
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10) ? CERO + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10) ? CERO + String.valueOf(mesActual) : String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        }, anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

    public void btnRegisterToMain(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void btnRegisterDB(View v){
        /*TODO VALIDAR QUE EL PASSWORD SEA IGUAL AL CONFIRM PASSWORD*/

        /* Ocultamos los datos del formulario */
        constraintLayoutFormulary.setVisibility(View.GONE);

        /* Invalidamos los botones */
        btnRegister.setEnabled(false);
        btnBackMian.setEnabled(false);

       /*
       PETICIÓN DE VOLLEY
       1. Método de envío
       2. URL del servicio
       3. Método de acciones cuando el server responde (lo que sea)
       4. Método cuando se tira un error del sever
       5. (OPCIONAL) parámetros si se uso POST como método de envío
        */
        seryRequest = new StringRequest(
                Request.Method.POST,
                "https://babel-tee.azurewebsites.net/api/v1/register",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*if (Integer.parseInt(response) == 201){

                        }*/
                        Toast.makeText(RegisterActivity.this, "RESP: " + response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "ERR: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        /* Ejecutamos la petición desde el servidor */
        seryConection.add(seryRequest);
    }
}