package com.example.loginfirebasemail77;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.loginfirebasemail77.modelos.Paciente;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.UUID;

public class RegistrarPaciente extends AppCompatActivity {

    EditText nameTutor, firstname, lastname, birthname, decivename, macadress;
    FirebaseDatabase firebaseDatabase;

    DatePickerDialog.OnDateSetListener setListener;
    Button btnFecha, btnSelectImg;

    DatabaseReference databaseReference;
    String idUsuario;
    EditText etPlannedDate;
    String base64imagen; /// en esta trabajas
    RadioButton rbtMasculino, rbtFemenino;
    String genero;
    Bitmap bitmap;
    String base64encoded = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarpaciente);
        nameTutor = findViewById(R.id.txtEditNameTutor);
        firstname = findViewById(R.id.Editarfirtname);
        lastname = findViewById(R.id.Editlastname);


        decivename = findViewById(R.id.EditarDecivename);
        macadress = findViewById(R.id.EditarMac);
        etPlannedDate = findViewById(R.id.EditaDate);


        birthname = findViewById(R.id.EditaDate);
        btnFecha = findViewById(R.id.btnFechaNacimiento);

        rbtMasculino = findViewById(R.id.radioButton);
        rbtFemenino = findViewById(R.id.radioButton2);


        Calendar cal = Calendar.getInstance();
        int YEAR = cal.get(Calendar.YEAR);
        int MES = cal.get(Calendar.MONTH);
        int DIA = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegistrarPaciente.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , setListener, YEAR, MES, DIA);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                birthname.setText(date);
            }
        };
        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegistrarPaciente.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        birthname.setText(date);
                    }
                }, YEAR, MES, DIA);
                datePickerDialog.show();
            }
        });

        btnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        idUsuario = getIntent().getExtras().getString("idUsuario");
        inicializarFirebase();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
                ImageView imageView = (ImageView) findViewById(R.id.imgPaciente);
                imageView.setImageURI(data.getData());

                // Convert bitmap to base64 encoded string
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] imageBytes = byteArrayOutputStream.toByteArray();
                base64encoded = Base64.encodeToString(imageBytes, Base64.NO_WRAP);
                //Image image = new Image();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addFirebasePaciente(View view) {
        Paciente p = new Paciente();
        p.setIdpatient(UUID.randomUUID().toString());
        p.setNameTutor(nameTutor.getText().toString());
        p.setFirstname(firstname.getText().toString());
        p.setLastname(lastname.getText().toString());
        p.setBirthname(birthname.getText().toString());

        if (rbtFemenino.isChecked()) {
            genero = "Femenino";

        } else if (rbtMasculino.isChecked()) {
            genero = "Maculino";
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
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }


}