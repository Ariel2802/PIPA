package com.example.loginfirebasemail77;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginfirebasemail77.modelos.paciente;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

import id.zelory.compressor.Compressor;

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

    Uri imageUri;
    //variables de imagenes
    ImageView foto;
    Button subir,seleccionar;
    StorageReference storageReference;
    ProgressDialog cargando;
    Bitmap thump_bitmap=null;
    Button botonCargar;
    ImageView imagen;
    String path;
    Image image;
    TextView tv;
    private static int RESULT_LOAD_IMAGE = 1;
    //fin de variables para imagenes
    private static final String TAG = "MiTag";
    private static  final int STORAGE_PERMISSION_CODE=113;

    private GestureDetector gesto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarpaciente);
        nameTutor=findViewById(R.id.txtNameTutor);
        firstname=findViewById(R.id.txtfirtname);
        lastname=findViewById(R.id.txtLastname);
        subir=findViewById(R.id.bntGuardar);
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

        imagen= (ImageView) findViewById(R.id.imagemId);
        botonCargar= (Button) findViewById(R.id.btnCargarImg);

        botonCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();

            }
        });
        gesto=new GestureDetector(this, new EscuchaGestos());
    }
    //---------------------------------------Fin del onCreate-------------------------------------//
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        gesto.onTouchEvent(event);
        return  super.onTouchEvent(event);
    }
    class EscuchaGestos extends GestureDetector.SimpleOnGestureListener
    {
       /* @Override
        public void onLongPress(MotionEvent event)
        {
            Toast.makeText(registrarpaciente.this,"Presión larga ",Toast.LENGTH_SHORT );

        }
        @Override
        public boolean onDoubleTap(MotionEvent event)
        {
            Toast.makeText(registrarpaciente.this,"Doble tag ",Toast.LENGTH_SHORT );
            return true;
        }*/
        @Override
        public void onShowPress(MotionEvent event)
        {
            Toast.makeText(registrarpaciente.this,"Presión corta",Toast.LENGTH_SHORT );
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //De izquierda a derecha
            if(e2.getX()>e1.getY())
            {
                Toast.makeText(registrarpaciente.this,"Desplazamiento a derecha",Toast.LENGTH_SHORT );
            }else
            {
                Intent i = new Intent(registrarpaciente.this,listapacientes.class);
                i.putExtra("idUsuario",idUsuario);
                startActivity(i);
                Toast.makeText(registrarpaciente.this,"Desplazamiento a la izquierda",Toast.LENGTH_SHORT );
            }
            return true;
        }
    }
    //-----------------------------Programación para los gestos------------------------------------//


    public void OpenGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
                ImageView imageView = (ImageView) findViewById(R.id.imagemId);
                imageView.setImageURI(data.getData());
                //FIN DE PONER LAS IMAGEN EN EL VIEW
                imageUri = CropImage.getPickImageResultUri(this, data);

            }

    }

   public String nombreAleatodio()
   {

       int p=(int) (Math.random()*25+1); int s=(int) (Math.random()*25+1);
       int t=(int) (Math.random()*25+1);int c=(int) (Math.random()*25+1);
       int numero1=(int) (Math.random()*1012+2111);
       int numero2=(int) (Math.random()*1012+2111);

       String[] elementos={"a","b","c","d","e","f","g","h","i","j","k","l",
               "m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
       final String aleatorio=elementos[p]+elementos[s]+numero1+elementos[t]+elementos[c]+numero2+"comprimido.jpg";
       return  aleatorio;
   }
    public void addFirebasePaciente(View view)
    {

        StorageReference reference=storageReference.child(nombreAleatodio());
        UploadTask uploadTask= reference.putFile(imageUri);

        Task<Uri> uriTask= uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then( Task<UploadTask.TaskSnapshot> task) throws Exception {
                if(!task.isSuccessful()){
                    throw Objects.requireNonNull(task.getException());
                }
                return reference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> task) {
                Uri dUri=task.getResult();
                guardarTodo(dUri.toString());
                Toast.makeText(registrarpaciente.this,"Todo bien ",Toast.LENGTH_SHORT );
            }
        });

    }
    public  void  guardarTodo(String url)
    {
        paciente p=new paciente();
        p.setIdpatient(UUID.randomUUID().toString());
        p.setNameTutor(nameTutor.getText().toString());
        p.setFirstname(firstname.getText().toString());
        p.setLastname(lastname.getText().toString());
        p.setBirthname(birthname.getText().toString());
        p.setImagBase64(url);

        if(rbtFemenino.isChecked())
        {
            genero="Femenino";

        }else if(rbtMasculino.isChecked())
        {
            genero="Maculino";
        }
        p.setGender(genero);
        p.setDecivename(decivename.getText().toString());
        p.setMacadress(macadress.getText().toString());
        p.setIdUsuario(idUsuario);
        p.setState("True");
        databaseReference.child("Paciente").child(p.getIdpatient()).setValue(p);
        Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
    }
    //--------------------------Iniciar Firebase-----------------------------------------------//
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        storageReference= FirebaseStorage.getInstance().getReference().child("Fotos");
    }
    //--------------------------Fin de Firebase-----------------------------------------------//
    //--------------------------------------------Parte de los permisos---------------------------------------------------//
    @Override
    protected void onResume()
    {
        super.onResume();
        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
    }
    public void checkPermission(String permission, int requestCode)
    {
        if(ContextCompat.checkSelfPermission(registrarpaciente.this, permission)== PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(registrarpaciente.this,new String[]{permission},requestCode);
        }
    }
    @Override
    public  void onRequestPermissionsResult(int requestCode, @NonNull  String[] permission, @NonNull int[] grantResults )
    {
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
        if(requestCode==STORAGE_PERMISSION_CODE)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {Toast.makeText(registrarpaciente.this,"Acepto los permisos", Toast.LENGTH_SHORT).show();}
        }else
        {Toast.makeText(registrarpaciente.this,"Permisos denegados", Toast.LENGTH_SHORT).show();}
    }
    //--------------------------------------------Parte de los permisos---------------------------------------------------//

}