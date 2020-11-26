package com.example.babel.io;

import com.example.babel.ProductList;
import com.example.babel.DetailProduct;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductVetApiService {

    @GET("products")
    Call<ArrayList<ProductList>> getProducts();

    @GET("details/products/{product_id}")
    Call<ArrayList<DetailProduct>> getDetails(@Path("product_id") int product_id);

}
