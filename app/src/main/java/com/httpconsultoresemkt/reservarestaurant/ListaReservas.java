package com.httpconsultoresemkt.reservarestaurant;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.httpconsultoresemkt.reservarestaurant.Models.Reservacion;
import com.httpconsultoresemkt.reservarestaurant.Services.RegisterSQLHelper;

import java.util.ArrayList;

public class ListaReservas extends AppCompatActivity {

    ListView lv;
    ArrayList<Reservacion> modelList;
    CustomAdapterReservas adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reservas);

        //crear el listview de restaurantes
        lv = (ListView) findViewById(R.id.listView);
        modelList = new ArrayList<Reservacion>();
        adapter = new CustomAdapterReservas(getApplicationContext(), modelList, getApplicationContext(), this);
        lv.setAdapter(adapter);
        setDataListView();
    }

    private void setDataListView() {

        Reservacion reservacion = null;

        String name = "";
        String telephone = "";
        String email = "";
        String people = "";
        String arrival = "";
        String schedule = "";
        String motivo = "";
        String restaurante = "";

        RegisterSQLHelper registerSQLHelper = new RegisterSQLHelper(this,"DBReservacion",null,1);
        SQLiteDatabase db = registerSQLHelper.getWritableDatabase();
        Cursor c = db.rawQuery(" SELECT name, telephone, email, people, arrival, schedule, motivo, restaurante FROM Reservacion", null);
        if(c.moveToFirst()){
            do {
                name = c.getString(0);
                telephone = c.getString(1);
                email = c.getString(2);
                people = c.getString(3);
                arrival = c.getString(4);
                schedule = c.getString(5);
                motivo = c.getString(6);
                restaurante = c.getString(7);
                Log.d("I", "Nombre: " + name);
                reservacion = new Reservacion(restaurante, name, telephone, email, people, arrival, schedule, motivo);
                modelList.add(reservacion);
            }while (c.moveToNext());
        }
        adapter.notifyDataSetChanged();
    }
}
