package com.httpconsultoresemkt.reservarestaurant;

/**
 * Created by mnitsch on 04/05/2016.
 */
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.httpconsultoresemkt.reservarestaurant.Models.Restaurant;

public class CustomAdapterRestaurants extends BaseAdapter {

    Context context;
    ArrayList<Restaurant> modelList;
    Context mContext;
    RestaurantActivity r;

    public CustomAdapterRestaurants(Context context, ArrayList<Restaurant> modelList, Context mContext, RestaurantActivity r) {
        this.context = context;
        this.modelList = modelList;
        this.mContext = mContext;
        this.r = r;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = null;

        if (convertView == null) {

            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.restaurant_listview, null);

            ImageView logo = (ImageView) convertView.findViewById(R.id.logo_restaurant);
            Button rm_btn = (Button) convertView.findViewById(R.id.rm_btn);
            TextView nombre = (TextView)convertView.findViewById(R.id.nombre);
            TextView direccion = (TextView)convertView.findViewById(R.id.direccion);


            Restaurant m = modelList.get(position);
            logo.setImageResource(m.getImage());
            nombre.setText(m.getNombre());
            direccion.setText(m.getLugar());

            // click listiner for remove button
            rm_btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //modelList.remove(position);
                    //notifyDataSetChanged();
                    Restaurant restaurant = modelList.get(position);
                    r.reservar(restaurant);
                }
            });
        }
        return convertView;
    }
}
