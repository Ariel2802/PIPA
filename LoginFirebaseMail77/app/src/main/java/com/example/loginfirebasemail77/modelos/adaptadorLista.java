package com.example.loginfirebasemail77.modelos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.loginfirebasemail77.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adaptadorLista extends RecyclerView.Adapter<adaptadorLista.ViewHolder> {
    private List<paciente> Data;
    private LayoutInflater myinflater;
    private Context context;

    public adaptadorLista(List<paciente> itemList, Context context)
    {
        this.myinflater=LayoutInflater.from(context);
        this.context=context;
        this.Data=itemList;
    }
    @Override
    public  int getItemCount()
    { return Data.size();
    }
    @Override
    public adaptadorLista.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=myinflater.inflate(R.layout.elementos, null);
        return new adaptadorLista.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final  adaptadorLista.ViewHolder holder, final int position)
    {
        holder.bindData(Data.get(position));

    }
    public  void  setItems(List<paciente> items){Data=items;}
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titulo;
        ViewHolder(View itemView)
        {
            super(itemView);
            titulo=itemView.findViewById(R.id.txtTitulo);

        }
        void bindData(final  paciente item)
        {
           // Picasso.get().load(item.getCover()).resize(100,100).centerCrop().into(imageRevista);
            titulo.setText(item.getFirstname());

        }
    }
}
