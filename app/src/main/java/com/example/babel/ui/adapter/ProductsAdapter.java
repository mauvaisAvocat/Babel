package com.example.babel.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babel.Details;
import com.example.babel.ProductList;
import com.example.babel.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private ArrayList<ProductList> localDataSet;
    private Context context;
    //private LayoutInflater inflater;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameProduct;
        private TextView tvPriceProduct;
        private ImageView imgProduct;
        private Button btnMore;

        public ViewHolder(View v) {
            super(v);
            tvNameProduct = (TextView) v.findViewById(R.id.tv_name_product);
            tvPriceProduct = (TextView) v.findViewById(R.id.tv_price_product);
            imgProduct = (ImageView) v.findViewById(R.id.img_product);
            btnMore = (Button) v.findViewById(R.id.btn_more_information);
        }
    }

    public ProductsAdapter(Context context) {
        this.context = context;
        localDataSet = new ArrayList<>();
        //inflater = LayoutInflater.from(context);
    }

    public void setDataSet(ArrayList<ProductList> dataSet) {
        localDataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_productos, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNameProduct.setText(localDataSet.get(position).getNameProduct());
        holder.tvPriceProduct.setText("$" + localDataSet.get(position).getPrecio_prod());
        Picasso.get()
                .load(localDataSet.get(position).getImage())
                .placeholder(R.drawable.relojarena)
                .error(R.drawable.nube)
                .fit()
                .into(holder.imgProduct);
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Details.class);
                i.putExtra("product_id", localDataSet.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }


}
