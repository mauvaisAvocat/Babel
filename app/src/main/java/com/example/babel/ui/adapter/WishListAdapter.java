package com.example.babel.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babel.R;
import com.example.babel.WishProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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

        public ViewHolder(View v) {
            super(v);
            tvWishListID = (TextView) v.findViewById(R.id.tv_wl_idproduct);
            tvWishListName = (TextView) v.findViewById(R.id.tv_wl_nameproduct);
            tvWishListPrice = (TextView) v.findViewById(R.id.tv_wl_priceproduct);
            tvWishListDescription = (TextView) v.findViewById(R.id.tv_wl_descproduct);
            imgWishList = (ImageView) v.findViewById(R.id.img_card_view);
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
                .inflate(R.layout.item_cardview_wishlist, parent, false);
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
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
