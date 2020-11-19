package com.example.babel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private List<ProductList> data;
    private LayoutInflater inflater;
    private ImageView imgProduct;
    private TextView tvName;
    private TextView tvPrice;

    public ProductAdapter(Context context, List<ProductList> data){
        this.context = context;
        this.data    = data;
        // Preparamos el diseño
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // Retornamos el tamaño de la lista
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView =inflater.inflate(R.layout.item_lista_productos, null);
            tvName = convertView.findViewById(R.id.tv_name);
            tvPrice = convertView.findViewById(R.id.tv_price);
            imgProduct =convertView.findViewById(R.id.img_product);

            tvName.setText(data.get(position).getNombre());
            tvPrice.setText("$" + data.get(position).getPrecio());

            Picasso.with(context)
                    .load(data.get(position).getUrlImagen())
                    .placeholder(R.drawable.relojarena)
                    .error(R.drawable.nube)
                    .into(imgProduct);
        }
        return convertView;
    }
}
