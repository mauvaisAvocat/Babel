package com.example.babel.io;

import com.example.babel.DetailProduct;
import com.example.babel.Login;
import com.example.babel.ProductList;
import com.example.babel.WishProduct;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductVetApiService {

    @GET("products")
    Call<ArrayList<ProductList>> getProducts();

    @GET("details/products/{product_id}")
    Call<ArrayList<DetailProduct>> getDetails(@Path("product_id") int product_id);

    @GET("wishlist/")
    Call<ArrayList<WishProduct>> getWishList(@Header("Authorization") String token);

    @GET("wishlist/destroy/{id_product}")
    Call<Void> getWishListDelete(@Path("id_product") int id_product,
                                 @Header("Authorization") String token);

    @GET("wishlist/add/{product_id}")
    Call<Void> addWishProduct(@Path("product_id") int product_id,
                              @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("login")
    Call<Login> getUser(@Field("email") String email,
                        @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<Void> addRegister(@Field("name") String name,
                           @Field("ap") String ap,
                           @Field("am") String am,
                           @Field("email") String email,
                           @Field("password") String password);

    @POST("logout")
    Call<Void> logoutUser(@Header("Authorization") String token);

    @GET("wishlist/reset")
    Call<Void> emptywishlist(@Header("Authorization") String token);

    @GET("search?")
    Call<ArrayList<ProductList>> getSearch(@Query("search") String product);

}
