package com.example.loginfirebasemail77;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.loginfirebasemail77.modelos.paciente;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.UUID;

public class registrarpaciente extends AppCompatActivity {

    EditText  nameTutor, firstname, lastname, birthname, decivename,macadress;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatePickerDialog.OnDateSetListener setListener;
    Button btnFecha;

    String idUsuario;
    EditText etPlannedDate;
    String base64imagen="asdasdasdasdas"; /// en esta trabajas
    RadioButton rbtMasculino, rbtFemenino;
    String genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarpaciente);
        nameTutor=findViewById(R.id.txtNameTutor);
        firstname=findViewById(R.id.txtfirtname);
        lastname=findViewById(R.id.txtLastname);



        decivename=findViewById(R.id.txtDecivename);
        macadress=findViewById(R.id.txtMac);
        etPlannedDate=findViewById(R.id.txtDate);


        birthname=findViewById(R.id.txtDate);
        btnFecha=findViewById(R.id.btnFechaNacimiento);

        rbtMasculino=findViewById(R.id.radioButton);
        rbtFemenino=findViewById(R.id.radioButton2);




        Calendar cal=Calendar.getInstance();
        int YEAR=cal.get(Calendar.YEAR);
        int MES=cal.get(Calendar.MONTH);
        int DIA=cal.get(Calendar.DAY_OF_MONTH);
        int style= AlertDialog.THEME_HOLO_LIGHT;

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        registrarpaciente.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,YEAR,MES,DIA);
                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    datePickerDialog.show();

            }
        });
        setListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
              month=month+1;
              String date=dayOfMonth+"/"+month+"/"+year;
                birthname.setText(date);
            }
        };
        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog= new DatePickerDialog(
                        registrarpaciente.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        String date=dayOfMonth+"/"+month+"/"+year;
                        birthname.setText(date);
                    }
                },YEAR,MES,DIA);
                datePickerDialog.show();
            }
        });

        idUsuario=getIntent().getExtras().getString("idUsuario");
        inicializarFirebase();

    }
    public void addFirebasePaciente(View view)
    {
        paciente p=new paciente();
        p.setIdpatient(UUID.randomUUID().toString());
        p.setNameTutor(nameTutor.getText().toString());
        p.setFirstname(firstname.getText().toString());
        p.setLastname(lastname.getText().toString());
        p.setBirthname(birthname.getText().toString());

        if(rbtFemenino.isChecked())
        {
            genero="Femenino";

        }else if(rbtMasculino.isChecked())
        {
            genero="Maculino";
        }
        p.setGender(genero);
        p.setImagBase64(base64imagen);
        p.setDecivename(decivename.getText().toString());
        p.setMacadress(macadress.getText().toString());
        p.setIdUsuario(idUsuario);
        p.setState("True");

        databaseReference.child("Paciente").child(p.getIdpatient()).setValue(p);
        Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }



}