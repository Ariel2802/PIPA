package com.example.loginfirebasemail77;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.loginfirebasemail77.modelos.lista;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class mapa extends AppCompatActivity implements GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {
    private GoogleMap mapG;
    String campus="Campus Central";
    String imgCampus="ingenieria_vtgzuq_xy2pny.jpg";
    double latiG=-1.0128684338088096,logitiG=-79.46930575553893;
    int vista=1;
    List<lista> facultades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        vista=2;

        facultades=new ArrayList<>();
        facultades.add(new lista("FACULTAD DE CIENCIAS AGROPECUARIAS",
                "-1.0809533698071374, -79.50269192673238",
                "Ing. Leonardo Gonzalo Matute, M.Sc.",
                "agraria_fsdshj.jpg"));
        facultades.add(new lista("FACULTAD DE CIENCIAS DE LA INGENIERÍA",
                "-1.0127542911580514, -79.47083072315361",
                "Ing. Washington Alberto Chiriboga Casanova, M.Sc.",
                "logo_fci_a0npdm.jpg"));
        facultades.add(new lista("FACULTAD DE CIENCIAS EMPRESARIALES",
                "-1.0121821020306447, -79.47017069530311",
                "Ing. Mariela Susana Andrade Arias, PhD",
                "Empresariales_duhayc.jpg"));
        facultades.add(new lista("FACULTAD DE CIENCIAS DE LA INDUSTRIA Y LA PRODUCCIÓN",
                "-1.0812661596830198, -79.5029589900245",
                "Ing. Sonnia Esther Barzola Miranda, M.Sc.",
                "industriales_rxsgww.jpg"));
        facultades.add(new lista("DECANO UNIDAD DE POSGRADO",
                "-1.0130542111468632, -79.46855806906344",
                "Ing. Roque Vivas, M.Sc",
                "posgrado_mcss9g.jpg"));
        facultades.add(new lista("LIC. Enfermeria",
                "-1.0129917510549566, -79.46948730818116",
                "Ing. Roque Vivas, M.Sc",
                "LicEnfermeria_lmoxj9"));
    }
    public void configurar(View v)
    {
        RadioButton radioButton;
        radioButton=findViewById(R.id.radioButton);
        if(radioButton.isChecked())
        {
            latiG=-1.0128684338088096;
            logitiG=-79.46930575553893;
            campus="Campus Central";
            imgCampus="ingenieria_vtgzuq_xy2pny.jpg";
        }else
        {
            latiG=-1.0799763882093478;
            logitiG= -79.50105832172973;
            campus="Campus La Maria";
            imgCampus="uteq_inaugura_nuevo_edificio_en_la_maria__20151026085715-1200x800_bqxygi.jpg";
        }

        LatLng coorInicial = new LatLng(latiG, logitiG);
        mapG.setMapType(vista);
        vista = vista<4?vista+1:1;

        CameraPosition camaraP = new CameraPosition.Builder()
                .target(coorInicial)
                .zoom(18)
                .bearing(0)
                .tilt(0)
                .build();
        CameraUpdate cameraUpdate =
                CameraUpdateFactory.newCameraPosition(camaraP);

        mapG.animateCamera(cameraUpdate);
        ponerMarcadores();

    }
    public void ponerMarcadores()
    {
        /*for(int i=0;i<facultades.size();i++)
        {
          String[] ubicacion=facultades.get(i).getUbicación().split(",");
            double latitude,longitude;
            latitude=Double.parseDouble(ubicacion[0]);
            longitude=Double.parseDouble(ubicacion[1]);
            final LatLng ads = new LatLng(latitude, longitude);
            mapG.addMarker(
                    new MarkerOptions()
                            .position(ads)
                            .title(facultades.get(i).getFaculta())
                            .snippet(""+facultades.get(i).getUbicación()+" & "+facultades.get(i).getDocente()+" & "+facultades.get(i).getNombre()+""));
        }
        ponerFiguras();

        LatLng uteqCampus = new LatLng(latiG+0.0002, logitiG);
        Marker campusUteq = mapG.addMarker(
                new MarkerOptions()
                        .position(uteqCampus)
                        .title(campus)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        .snippet(" "+latiG+", "+logitiG+" & Rector & "+imgCampus+""));
        campusUteq.showInfoWindow();*/

    }
    public  void ponerFiguras()
    {
        //Figura del campus de la ciudad
        /*PolylineOptions figuraPrincipal = new PolylineOptions()
                .add(new LatLng(-1.0123640378002916 , -79.46724191146544 ))
                .add(new LatLng(-1.0131793019445348 , -79.46723118262943 ))
                .add(new LatLng(-1.0129325772909452 , -79.47180166676536 ))
                .add(new LatLng(-1.0119187283120508 , -79.47185213725422 ))
                .add(new LatLng(-1.0123640378002916 , -79.46724191146544 ));
        figuraPrincipal.width(8);
        figuraPrincipal.color(R.color.colorPrimary);
        mapG.addPolyline(figuraPrincipal);

        //Figura del campus de la María
        PolylineOptions figuraSecundaria = new PolylineOptions()
                .add(new LatLng(-1.0786183199175454 ,-79.50135991117891 ))
                .add(new LatLng(-1.083709672071642  ,-79.4967956123442  ))
                .add(new LatLng(-1.0893288802614813 ,-79.50028696033628 ))
                .add(new LatLng(-1.082773136361302  ,-79.50556655973678 ))
                .add(new LatLng(-1.0802359745162262 ,-79.5041189276441  ))
                .add(new LatLng(-1.0788226552740785 ,-79.50244989299698 ))
                .add(new LatLng(-1.0786183199175454 ,-79.50135991117891 ));
        figuraSecundaria.width(8);
        figuraSecundaria.color(R.color.design_default_color_primary);
        mapG.addPolyline(figuraSecundaria);

        mapG.setInfoWindowAdapter(new inf_marcador_adapter(mapa.this));
        mapG.setMapType(vista);*/
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapG = googleMap;
        mapG.getUiSettings().setZoomControlsEnabled(true);
        mapG.setOnMarkerClickListener(this);
        LatLng uteq = new LatLng(latiG, logitiG);
        CameraPosition camaraP = new CameraPosition.Builder()
                .target(uteq)
                .zoom(18)
                .bearing(0)
                .tilt(0)
                .build();
        CameraUpdate cameraUpdate =
                CameraUpdateFactory.newCameraPosition(camaraP);
        mapG.animateCamera(cameraUpdate);
        ponerMarcadores();
    }
    @Override
    public boolean onMarkerClick( Marker marker) {
        Integer clickCount = (Integer) marker.getTag();
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}