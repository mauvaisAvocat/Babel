package com.example.babel.io;

import com.example.babel.ProductList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductVetApiService {
    @GET("products")
    Call<ArrayList<ProductList>> getProducts();
}
