package com.example.babel.io;

import com.example.babel.Tools;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
       /* HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);*/

        /* Asociamos el interceptor a las peticiones */
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiYzY0NjAzMTk3MjFkNzhkZjMyMjc0YjY4YjQ1YzI3ZDU1NzMxOTBkOGNlYjJiMTc3YjUwZmI2YzI4OWE0YjNhZTQ5YTNlZDIyYjVmMmVlNWIiLCJpYXQiOjE2MDY2MDM3NTUsIm5iZiI6MTYwNjYwMzc1NSwiZXhwIjoxNjM4MTM5NzU1LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.etI7pS5Ydh_YrWXK-DczRxgqAV3J73-vS_xjmSMnfOedznibyGPdGzYs06XGFh6hWkqcVm0bHJuAadJNeCT7n2ZeWNghqBlp0MPX9x9Yw1pa9RJS_8tiWE22IRHUH2ymcmmZ29-X3l99SeBaFCWBv6JH3Mgb7CzJ7UY8paSfjIIX7M5RtGAr1bt1R5jZ1Ygw9zvVV-F-Y598xHeWr3GM8rig5WPzBeLbpebNfAl4c9ZwP1JfYH7TmNWgtmNezKmLnNwnoHyQAwk-_wqssZpMi56O9meku_Pwg0iNKktCgh1avXslVfbJ-T9_EbyXZwxwFCBqjF7ZhS0GxuMiWdUHS4rGOnilScScfuDEOvzUk3ZjHTmXvOExEWuZUoKEihPOdHUphB8KWdZa7aw0NVyV00b7YxmKECen77JTF0cLVk18xmjw7AASCP1oJQ9qotvqANcS612Nh6ZhvYb0ufXCMITXn_GazAdJxsyNroS0NSHSKtD3TQh3CGj4WuZws_Y47lNWy98m-yk7Ib8ctvd7JoEpii1-3oqP_rqq4Ht1UrUgWIO44MrF8aKYsEntWbalgUOgPlAuqldrvD6y7Z27F-2TcYHgBjyWatfLZqvBZ1AuXUoZtGMYbK9MrTWAWKKYT1T4ZdMFBp2iBflSkBxHgO9Qbkm_R2gm9e9Kphy_1MQ")
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        String baseURL = "https://babel-tee.azurewebsites.net/api/v1/";

        if (API_SERVICE == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
            API_SERVICE = retrofit.create(ProductVetApiService.class);
        }
        return API_SERVICE;
    }
}
