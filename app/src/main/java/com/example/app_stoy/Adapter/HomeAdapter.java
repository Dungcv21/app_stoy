package com.example.app_stoy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_stoy.Model.Product;
import com.example.app_stoy.R;

import java.util.List;

public class HomeAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    List<Product> products;

    public HomeAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
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
        if(layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_product, null);
        }
        ImageView imageView = convertView.findViewById(R.id.image_product);
        TextView dess = convertView.findViewById(R.id.descibe);
        TextView pricee = convertView.findViewById(R.id.price);
        TextView soldd = convertView.findViewById(R.id.sold);
        Product product = products.get(position);
            dess.setText(product.getDescription());
            pricee.setText(String.valueOf(product.getPrice()) + "đ");
            soldd.setText("Đã bán: " + String.valueOf(product.getSole()));
            imageView.setImageResource(R.drawable.image);
        return convertView;
    }
}
