package com.example.loginfirebasemail77;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.loginfirebasemail77.modelos.adaptadorLista;
import com.example.loginfirebasemail77.modelos.paciente;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class listapacientes extends AppCompatActivity {

    List<paciente>  list=new ArrayList<>();
    ArrayAdapter<paciente> arrayAdapterPaciente;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listaView;
    paciente pacienteSelect;
    TextView textView;
    String idUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listapacientes);
        //listaView=findViewById(R.id.listaComponentes);
        idUsuario=getIntent().getExtras().getString("idUsuario");
        inicializarFirebase();
        listapaciente();
        //textView=findViewById(R.id.nombresss);

       /* listaView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            pacienteSelect=(paciente) parent.getItemAtPosition(position);
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

            Intent i = new Intent(listapacientes.this,editarpaciente.class);
           i.putExtra("idpatient",idpatient);
            i.putExtra("nameTutor",nameTutor);
            i.putExtra("firstname",firstname);
            i.putExtra("lastname",lastname);
            i.putExtra("birthname",birthname);
            i.putExtra("gender",gender);
            i.putExtra("imagBase64",imagBase64);
            i.putExtra("decivename",decivename);
            i.putExtra("macadress",macadress);
            i.putExtra("state",state);

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

    private void listapaciente() {
        databaseReference.child("Paciente").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot objShaptshot : snapshot.getChildren())
                {
                    paciente p= objShaptshot.getValue(paciente.class);
                    list.add(p);
                    System.out.println(list);
                    //arrayAdapterPaciente= new ArrayAdapter<paciente>(listapacientes.this, android.R.layout.simple_list_item_1, list);
                    //listaView.setAdapter(arrayAdapterPaciente);
                }
                for (int i=0; i<list.size();i++)
                {
                   System.out.println("valor:" +list.get(i).getNameTutor());
                }
                ejecutar();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void ejecutar()
    {

        adaptadorLista lista= new adaptadorLista(list, this);
        RecyclerView recyclerView=findViewById(R.id.listaRevista);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(lista);

    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }
}