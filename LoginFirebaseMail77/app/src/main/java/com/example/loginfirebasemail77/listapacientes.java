package com.example.loginfirebasemail77;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.loginfirebasemail77.modelos.Paciente;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaPacientes extends AppCompatActivity {

<<<<<<< HEAD
    List<Paciente>  list=new ArrayList<Paciente>();
    ArrayAdapter<Paciente> arrayAdapterPaciente;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listaView;
    Paciente pacienteSelect;
    TextView textView;
    String idUsuario;
=======
    List<paciente>  list=new ArrayList<>();
    ArrayAdapter<paciente> arrayAdapterPaciente;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listaView;
    paciente pacienteSelect;
    TextView textView;
    String idUsuario;

>>>>>>> parent of f8958c6 (ruta)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listapacientes);
        listaView=findViewById(R.id.listaComponentes);
        idUsuario=getIntent().getExtras().getString("idUsuario");
        inicializarFirebase();
        listapaciente();
<<<<<<< HEAD
        textView=findViewById(R.id.nombresss);

    listaView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            pacienteSelect=(Paciente) parent.getItemAtPosition(position);
=======
        //textView=findViewById(R.id.nombresss);

       /* listaView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            pacienteSelect=(paciente) parent.getItemAtPosition(position);
>>>>>>> parent of f8958c6 (ruta)
            String idpatient, nameTutor,firstname,lastname, birthname, gender, imagBase64, decivename,macadress,state;
            idpatient=pacienteSelect.getIdpatient();
            nameTutor=pacienteSelect.getNameTutor();
            firstname=pacienteSelect.getFirstname();
            lastname=pacienteSelect.getLastname();
            birthname=pacienteSelect.getBirthname();
            gender=pacienteSelect.getGender();
            imagBase64=pacienteSelect.getImagBase64();
            decivename=pacienteSelect.getDecivename();
            macadress=pacienteSelect.getMacadress();
            state=pacienteSelect.getState();

<<<<<<< HEAD
            Intent i = new Intent(ListaPacientes.this,EditarPaciente.class);
            i.putExtra("idpatient",idpatient);
=======
            Intent i = new Intent(listapacientes.this,editarpaciente.class);
           i.putExtra("idpatient",idpatient);
>>>>>>> parent of f8958c6 (ruta)
            i.putExtra("nameTutor",nameTutor);
            i.putExtra("firstname",firstname);
            i.putExtra("lastname",lastname);
            i.putExtra("birthname",birthname);
            i.putExtra("gender",gender);
            i.putExtra("imagBase64",imagBase64);
            i.putExtra("decivename",decivename);
            i.putExtra("macadress",macadress);
            i.putExtra("state",state);
<<<<<<< HEAD

            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            return false;
        }
    });
        listaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pacienteSelect=(Paciente) parent.getItemAtPosition(position);
                textView.setText(pacienteSelect.getIdpatient()+"\n "
                                +pacienteSelect.getBirthname()+"\n "
                                +pacienteSelect.getGender()+"\n "
                                +pacienteSelect.getMacadress());
            }
        });


=======

            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            return false;
        }
    });
        listaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pacienteSelect=(paciente) parent.getItemAtPosition(position);
                textView.setText(pacienteSelect.getIdpatient()+"\n "
                                +pacienteSelect.getBirthname()+"\n "
                                +pacienteSelect.getGender()+"\n "
                                +pacienteSelect.getMacadress());
            }
        });*/
    }
    public void goaddPaciente(View view)
    {
        Intent i = new Intent(this,registrarpaciente.class);
        i.putExtra("idUsuario",idUsuario);
        startActivity(i);
    }
    public void goMapa(View view)
    {
        Intent i = new Intent(this,mapa.class);
        i.putExtra("idUsuario",idUsuario);
        startActivity(i);
    }
    public void goHome(View view)
    {
        Intent i = new Intent(this,HomeActivity.class);
        i.putExtra("idUsuario",idUsuario);
        startActivity(i);
>>>>>>> parent of f8958c6 (ruta)
    }

    private void listapaciente() {
        databaseReference.child("Paciente").orderByChild("idTutor").equalTo(idUsuario).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot objShaptshot : snapshot.getChildren())
                {
                    Paciente p= objShaptshot.getValue(Paciente.class);
                    list.add(p);
                    arrayAdapterPaciente= new ArrayAdapter<Paciente>(ListaPacientes.this, android.R.layout.simple_list_item_1, list);
                    listaView.setAdapter(arrayAdapterPaciente);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference=firebaseDatabase.getReference();
    }
}