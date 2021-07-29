package com.example.loginfirebasemail77;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

public class inf_marcador_adapter implements GoogleMap.InfoWindowAdapter {

    Context context;
    public inf_marcador_adapter(Context context) {
        this.context=context;
    }

    @Override
    public View getInfoWindow( Marker marker) {
        View infoView= LayoutInflater.from(context).inflate(R.layout.activity_inf_marcador_adapter,null);
        TextView textView=infoView.findViewById(R.id.facultad);
        TextView textView1=infoView.findViewById(R.id.ubicacion);
        TextView textView2=infoView.findViewById(R.id.decano);
        TextView textView3=infoView.findViewById(R.id.textView6);
        textView.setText(marker.getTitle());
        String[] a =marker.getSnippet().split("&");
        textView1.setText(a[0]);
        if(a[1].replace(" ","").equals("Rector"))
        {
            textView3.setText(a[1].replace(" ",""));
            textView2.setText("Dr. Eduardo Díaz Ocampo, PhD.");
        }else
            {
                textView3.setText("Decano");
                textView2.setText(a[1].toString());
        }

        ImageView imageView;
        imageView=infoView.findViewById(R.id.imageView2);
        String url= a[2].toString().replaceAll(" ","");

        Picasso.get().load("https://res.cloudinary.com/durxpegdm/image/upload/v1625982278/logos/"+url+"").resize(200,200).centerCrop().into(imageView);

        return infoView;
    }

    @Override
    public View getInfoContents( Marker marker) {
        return null;
    }
}
