package com.example.app_stoy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_stoy.R;

public class HomeAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private String[] des;
    private  int[] price;
    private  int[] sold;
    private  int[] image;

    public HomeAdapter(Context c, int[] im, String[] d, int[] pr, int[] so) {
        context = c;
        this.des = d;
        this.price = pr;
        this.sold = so;
        this.image = im;
    }

    @Override
    public int getCount() {
        return des.length;
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

        imageView.setImageResource(image[position]);
        dess.setText(des[position]);
        pricee.setText(String.valueOf(price[position]) + "đ");
        soldd.setText("Đã bán: " + String.valueOf(sold[position]));
        return convertView;
    }
}
