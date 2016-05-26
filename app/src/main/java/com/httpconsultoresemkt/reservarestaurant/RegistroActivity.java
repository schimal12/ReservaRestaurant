package com.httpconsultoresemkt.reservarestaurant;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.httpconsultoresemkt.reservarestaurant.Services.RegisterSQLHelper;
import com.httpconsultoresemkt.reservarestaurant.Services.SendMail;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    private EditText name, telephone, email, people, arrival, schedule, motivo;
    private Calendar myCalendar, myCalendarT;
    private String restaurante;

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );


    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener(){

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendarT.set(Calendar.HOUR, hourOfDay);
            myCalendarT.set(Calendar.MINUTE, minute);
            String myFormat = "HH:mm";
            SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat,Locale.US);
            schedule.setText(sdf1.format(myCalendarT.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            arrival.setText(sdf.format(myCalendar.getTime()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Bundle extras = getIntent().getExtras();
        restaurante = extras.getString("NombreR");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        myCalendar = Calendar.getInstance();
        myCalendarT = Calendar.getInstance();

        name = (EditText) findViewById(R.id.name);
        telephone = (EditText)findViewById(R.id.telefono);
        email = (EditText)findViewById(R.id.email);
        people = (EditText)findViewById(R.id.num_personas);

        arrival = (EditText)findViewById(R.id.fecha_llegada);
        arrival.setInputType(InputType.TYPE_NULL);

        schedule = (EditText)findViewById(R.id.horario_llegada);
        schedule.setInputType(InputType.TYPE_NULL);
        motivo = (EditText)findViewById(R.id.motivo);

        //Listeners

        arrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DatePickerDialog arrivalDatePicker =  new DatePickerDialog(RegistroActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                arrivalDatePicker.show();
            }
        });

        schedule.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               TimePickerDialog timePickerDialog =  new TimePickerDialog(RegistroActivity.this,time,myCalendarT.get(Calendar.HOUR),myCalendarT.get(Calendar.MINUTE),true);timePickerDialog.show();
            }
        });
    }

    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }


    public void onReservar(View view){
        String nameT, telephoneT, emailT, peopleT, arrivalT, scheduleT, motivoT;
        nameT = name.getText().toString();
        telephoneT = telephone.getText().toString();
        emailT = email.getText().toString();
        peopleT = people.getText().toString();
        arrivalT = arrival.getText().toString();
        scheduleT = schedule.getText().toString();
        motivoT = motivo.getText().toString();


        if(!TextUtils.isEmpty(nameT) && !TextUtils.isEmpty(telephoneT) && !TextUtils.isEmpty(emailT) && !TextUtils.isEmpty(peopleT) && !TextUtils.isEmpty(arrivalT) && !TextUtils.isEmpty(scheduleT) && !TextUtils.isEmpty(motivoT)){
            if(checkEmail(emailT)) {
                SaveinSQL(nameT, telephoneT, emailT, peopleT, arrivalT, scheduleT, motivoT);
                SendMail(emailT, restaurante, arrivalT, scheduleT, nameT, motivoT,peopleT);
            }else{
                Toast.makeText(RegistroActivity.this,"Ingresa un email válido",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(RegistroActivity.this, "Ingresa todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    private void SendMail(String emailT, String restaurante, String arrivalT, String scheduleT, String nameT, String motivoT, String peopleT) {
        Log.d("I","Send Mail");
        SendMail mail = new SendMail(RegistroActivity.this,getString(R.string.mailDirectionSend),getString(R.string.mailDirectionPass));

        String[] toArr = {emailT};
        mail.setTo(toArr);
        mail.setFrom("schimal12@gmail.com");
        mail.setSubject("Confirmación de Reservación");
        mail.setBody("Se envía el correo para confirmar su reservación para "+peopleT+" personas"+".\nEs el día "+arrivalT+" a las "+scheduleT+" en el restaurante "+restaurante+" con motivo de su "+motivoT+"\n"+"Gracias por su preferencia "+nameT);
        try{
            if(mail.send()){

            }else{
                Toast.makeText(RegistroActivity.this,"No enviado", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(RegistroActivity.this,"Error",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void SaveinSQL(String nameT, String telephoneT, String emailT, String peopleT,String arrivalT, String scheduleT, String motivoT) {

        RegisterSQLHelper registerSQLHelper = new RegisterSQLHelper(this,"DBReservacion",null,1);
        SQLiteDatabase db = registerSQLHelper.getWritableDatabase();

        if(db != null){
            db.execSQL("INSERT INTO Reservacion (name, telephone, email, people, arrival, schedule, motivo, restaurante)"+
                    "VALUES ("+"'"+nameT+"'"+",'"+telephoneT+"'"+",'"+emailT+"'"+",'"+peopleT+"'"+",'"+arrivalT+"'"+",'"+scheduleT+"'"+",'"+motivoT+"'"+",'"+restaurante.toString()+"'"+")");
            db.close();
            Toast.makeText(RegistroActivity.this,"Reservación Hecha",Toast.LENGTH_SHORT).show();
        }
        Intent principal = new Intent(this,MainActivity.class);
        startActivity(principal);
    }
}