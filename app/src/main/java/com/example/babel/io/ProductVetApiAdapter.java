package com.example.babel.io;

import com.example.babel.Tools;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.babel.Tools.ANSI_RESET;
import static com.example.babel.Tools.ANSI_YELLOW;

public class ProductVetApiAdapter {

    private static ProductVetApiService API_SERVICE;

    public static ProductVetApiService getApiService(){
        Tools.cleanScreen();
        System.out.println(ANSI_YELLOW + "Get Api response ........................................" + ANSI_RESET);
        /* Creamos un interceptor y l eindicamos el log level a usar */
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        /* Asociamos el interceptor a las peticiones */
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        String baseURL = "https://babel-tee.azurewebsites.net/api/v1/";

        if (API_SERVICE == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            API_SERVICE = retrofit.create(ProductVetApiService.class);
        }
        return API_SERVICE;
    }
}
