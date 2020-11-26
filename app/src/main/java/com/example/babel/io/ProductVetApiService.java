package com.example.babel.io;

import com.example.babel.ProductList;
import com.example.babel.ui.adapter.DetailProduct;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductVetApiService {
    @GET("products")
    Call<ArrayList<ProductList>> getProducts();

    @GET("details/products/1")
    Call<ArrayList<DetailProduct>> getDetails();
}
