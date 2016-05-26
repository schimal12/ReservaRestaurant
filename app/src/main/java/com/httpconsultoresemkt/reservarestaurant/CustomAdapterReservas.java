package com.httpconsultoresemkt.reservarestaurant;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.httpconsultoresemkt.reservarestaurant.Models.Reservacion;
import com.httpconsultoresemkt.reservarestaurant.Models.Restaurant;

import java.util.ArrayList;

/**
 * Created by sebastianchimal on 26/05/16.
 */
public class CustomAdapterReservas extends BaseAdapter {
    Context context;
    ArrayList<Reservacion> modelList;
    Context mContext;
    ListaReservas r;

    public CustomAdapterReservas(Context applicationContext, ArrayList<Reservacion> modelList, Context applicationContext1, ListaReservas listaReservas) {

        this.context = applicationContext;
        this.modelList = modelList;
        this.mContext = applicationContext1;
        this.r = listaReservas;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = null;

        if (convertView == null) {

            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.reserva_listview, null);

            TextView nombre = (TextView) convertView.findViewById(R.id.anombrede);
            TextView horario = (TextView) convertView.findViewById(R.id.horario1);
            TextView restaurant = (TextView) convertView.findViewById(R.id.restaurant1);

            Reservacion m = modelList.get(position);
            nombre.setText(m.getNombre());
            horario.setText(m.getHoraLlegada());
            restaurant.setText(m.getRestaurant());
        }
        return convertView;
    }
}
