package com.example.babel.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babel.DetailProduct;
import com.example.babel.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    private ArrayList<DetailProduct> localDataSet;
    private Context context;
    //private LayoutInflater inflater;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDetailsID;
        private TextView tvDetailsName;
        private TextView tvDetailsPrice;
        private TextView tvDetailsSizeS;
        private TextView tvDetailsSizeM;
        private TextView tvDetailsSizeL;
        private TextView tvDetailsCosto;
        private TextView tvDetailsDescuento;
        private TextView tvDetailsDescription;
        private TextView tvDetailsStatus;
        private TextView tvDetailsMaterial;
        private TextView tvDetailsCategoria;
        private TextView tvDetailsProveedor;
        private ImageView imgDetails;

        public ViewHolder(View v) {
            super(v);
            tvDetailsID = (TextView) v.findViewById(R.id.tv_details_id);
            tvDetailsName = (TextView) v.findViewById(R.id.tv_details_name);
            tvDetailsPrice = (TextView) v.findViewById(R.id.tv_details_price);
            tvDetailsSizeS = (TextView) v.findViewById(R.id.tv_details_size_s);
            tvDetailsSizeM = (TextView) v.findViewById(R.id.tv_details_size_m);
            tvDetailsSizeL = (TextView) v.findViewById(R.id.tv_details_size_l);
            tvDetailsCosto = (TextView) v.findViewById(R.id.tv_details_costo);
            tvDetailsDescuento = (TextView) v.findViewById(R.id.tv_details_descuento);
            tvDetailsDescription = (TextView) v.findViewById(R.id.tv_details_desc);
            tvDetailsStatus = (TextView) v.findViewById(R.id.tv_details_status);
            tvDetailsMaterial = (TextView) v.findViewById(R.id.tv_details_mat);
            tvDetailsCategoria = (TextView) v.findViewById(R.id.tv_details_cat);
            tvDetailsProveedor = (TextView) v.findViewById(R.id.tv_details_prov);
            imgDetails = (ImageView) v.findViewById(R.id.img_details);
        }
    }

    public DetailsAdapter(Context context) {
        this.context = context;
        localDataSet = new ArrayList<>();
        //inflater = LayoutInflater.from(context);
    }

    public void setDataSet(ArrayList<DetailProduct> dataSet) {
        localDataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_details_product, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsAdapter.ViewHolder holder, int position) {
        holder.tvDetailsID.setText("ID: " + localDataSet.get(position).getId());
        holder.tvDetailsName.setText(localDataSet.get(position).getNameProduct());
        holder.tvDetailsDescription.setText(localDataSet.get(position).getDescription_prod());
        holder.tvDetailsCosto.setText("$" + localDataSet.get(position).getCosto_prod());
        holder.tvDetailsPrice.setText("$" + localDataSet.get(position).getPrecio_prod());
        holder.tvDetailsDescuento.setText("" + localDataSet.get(position).getDescuento());
        holder.tvDetailsMaterial.setText("Material: " + localDataSet.get(position).getMaterial_prod());
        Picasso.get()
                .load(localDataSet.get(position).getImage())
                .placeholder(R.drawable.relojarena)
                .error(R.drawable.nube)
                .into(holder.imgDetails);
        holder.tvDetailsSizeS.setText("Talla CH Disponibles: " + localDataSet.get(position).getExistence_s());
        holder.tvDetailsSizeM.setText("Talla M Disponibles: " + localDataSet.get(position).getExistence_m());
        holder.tvDetailsSizeL.setText("Talla G Disponibles: " + localDataSet.get(position).getExistence_l());
        holder.tvDetailsStatus.setText(localDataSet.get(position).getStatus());
        holder.tvDetailsCategoria.setText("Categoría: " + localDataSet.get(position).getCategory());
        holder.tvDetailsProveedor.setText("Proveedor: " + localDataSet.get(position).getProvider());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
