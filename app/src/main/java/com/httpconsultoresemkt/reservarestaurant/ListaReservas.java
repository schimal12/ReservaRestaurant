package com.httpconsultoresemkt.reservarestaurant;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.httpconsultoresemkt.reservarestaurant.Models.Reservacion;
import com.httpconsultoresemkt.reservarestaurant.Services.RegisterSQLHelper;

import java.util.ArrayList;

public class ListaReservas extends ListActivity {

    ArrayList<Reservacion> modelList;
    CustomAdapterReservas adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reservas);
        //crear el listview de restaurantes
        modelList = new ArrayList<Reservacion>();
        adapter = new CustomAdapterReservas(getApplicationContext(), modelList, getApplicationContext(), this);
        setListAdapter(adapter);
        setContentView(R.layout.activity_lista_reservas);
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

    public void onListItemClick(ListView l, View v, final int position, long id) {
        new AlertDialog.Builder(this)
                .setTitle("Borrar reserva")
                .setMessage("¿Estás seguro que quieres borrar esta reserva?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        RegisterSQLHelper registerSQLHelper = new RegisterSQLHelper(ListaReservas.this,"DBReservacion",null,1);
                        SQLiteDatabase db = registerSQLHelper.getWritableDatabase();

                        String nombre = modelList.get(position).getNombre().toString();

                        if(db != null){
                            db.execSQL("DELETE FROM Reservacion WHERE name="+"'"+nombre+"'");
                            db.close();
                            Toast.makeText(ListaReservas.this,"Reservación Borrada",Toast.LENGTH_SHORT).show();
                            modelList.clear();
                            setDataListView();
                        }

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
