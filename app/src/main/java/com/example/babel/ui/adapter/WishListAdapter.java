package com.example.babel.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babel.R;
import com.example.babel.WishProduct;
import com.example.babel.io.ProductVetApiAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    private ArrayList<WishProduct> localDataSet;
    private Context context;
    //private LayoutInflater inflater;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvWishListID;
        private TextView tvWishListName;
        private TextView tvWishListPrice;
        private TextView tvWishListDescription;
        private ImageView imgWishList;
        private Button btnDelete;

        public ViewHolder(View v) {
            super(v);
            tvWishListID = (TextView) v.findViewById(R.id.tv_wl_idproduct);
            tvWishListName = (TextView) v.findViewById(R.id.tv_wl_nameproduct);
            tvWishListPrice = (TextView) v.findViewById(R.id.tv_wl_priceproduct);
            tvWishListDescription = (TextView) v.findViewById(R.id.tv_wl_descproduct);
            imgWishList = (ImageView) v.findViewById(R.id.img_wishlist);
            btnDelete = (Button) v.findViewById(R.id.btn_wl_delete);
        }
    }

    public WishListAdapter(Context context){
        this.context = context;
        localDataSet = new ArrayList<>();
    }

    public void setDataSet(ArrayList<WishProduct> dataset){
        localDataSet = dataset;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WishListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wish_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListAdapter.ViewHolder holder, int position) {
        Picasso.get()
                .load(localDataSet.get(position).getImage())
                .placeholder(R.drawable.relojarena)
                .fit()
                .error(R.drawable.nube)
                .into(holder.imgWishList);
        holder.tvWishListID.setText("ID: " + localDataSet.get(position).getProduct_id());
        holder.tvWishListName.setText(localDataSet.get(position).getNameProduct());
        holder.tvWishListPrice.setText("$" + localDataSet.get(position).getPrecio_prod());
        holder.tvWishListDescription.setText("Descripción: " + localDataSet.get(position).getDescription_prod());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_product = localDataSet.get(position).getProduct_id();
                Call<Void> call = ProductVetApiAdapter.getApiService().getWishListDelete(id_product);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(context, "Se ha borrado playera", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

}
