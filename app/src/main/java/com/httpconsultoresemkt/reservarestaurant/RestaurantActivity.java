package com.httpconsultoresemkt.reservarestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.httpconsultoresemkt.reservarestaurant.Models.Restaurant;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity {

    String[] SPINNERLISTHORARIO = {"13:00 - 15:00", "19:00 - 00:00"};
    String[] SPINNERLISTFECHA = {"Peruana", "Tradicional", "Italiana"};
    String[] SPINNERLISTLUGAR = {"Concepción", "San Pedro"};

    ListView lv;
    ArrayList<Restaurant> modelList;
    CustomAdapterRestaurants adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        // spinner fecha
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, SPINNERLISTFECHA);
        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.tipo_compra);
        materialDesignSpinner.setAdapter(arrayAdapter);

        //spinner horario
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, SPINNERLISTHORARIO);
        MaterialBetterSpinner materialDesignSpinner1 = (MaterialBetterSpinner)
                findViewById(R.id.horario);
        materialDesignSpinner1.setAdapter(arrayAdapter1);

        //spinner lugar
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, SPINNERLISTLUGAR);
        MaterialBetterSpinner materialDesignSpinner2 = (MaterialBetterSpinner)
                findViewById(R.id.lugar);
        materialDesignSpinner2.setAdapter(arrayAdapter2);

        //crear el listview de restaurantes
        lv = (ListView) findViewById(R.id.listView);
        modelList = new ArrayList<Restaurant>();
        adapter = new CustomAdapterRestaurants(getApplicationContext(), modelList, getApplicationContext(), this);
        lv.setAdapter(adapter);
        setDataListView();
    }

    public void setDataListView(){
        Restaurant r1 = new Restaurant("Tradicional","13:00 - 15:00","Tucapel #752, Concepcion",R.drawable.aztlan, "Aztlan Restaurant");
        Restaurant r2 = new Restaurant("Peruana","19:00 - 00:00","Los Maitenes #225, San Pedro",R.drawable.laolla, "La Olla Peruana Restaurant.");
        Restaurant r3 = new Restaurant("Italiana","13:00 - 15:00","Las Heras #544 San Pedro",R.drawable.italiano, "Tratoria Restaurant");
        Restaurant r4 = new Restaurant("Tradicional","19:00 - 00:00","San Martin #1453. Concepcion",R.drawable.gondola, "Gandola Restaurant");
        Restaurant r5 = new Restaurant("Peruana","19:00 - 00:00","Chacabuco #1266 San Pedro",R.drawable.sabor, "Sabor Peru Restaurant");
        Restaurant r6 = new Restaurant("Italiana","13:00 - 15:00","Prat #25 Concepcion",R.drawable.italianor, "Italiano Restaurant");

        modelList.add(r1);
        modelList.add(r2);
        modelList.add(r3);
        modelList.add(r4);
        modelList.add(r5);
        modelList.add(r6);
        adapter.notifyDataSetChanged();
    }
    
    public void reservar(Restaurant r){
        String nombreR = r.getNombre().toString();
        Log.d("NombreR",nombreR);
        Intent i = new Intent(this, RegistroActivity.class);
        i.putExtra("NombreR",nombreR);
        startActivity(i);
    }
}
