package com.stos.italo.projetofilme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

class CustomAdapter extends BaseAdapter{

    private Context applicationContext;
    private int exemplo;
    private List<Filme> filmes;

    //adapter (a.k.a o carinha que monta a tela)
    CustomAdapter(Context applicationContext, int sample, List<Filme> filmes) {

        this.applicationContext =applicationContext;
        this.exemplo = exemplo;
        this.filmes =filmes;

    }


    @Override    public int getCount() {
        return filmes.size();
    }

    @Override    public Object getItem(int i) {
        return filmes.get(i);
    }

    @Override    public long getItemId(int i) {
        return i;
    }

    @Override    public View getView(int i, View view, ViewGroup viewGroup) {



        if(view == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view =  layoutInflater.inflate(R.layout.bloco,viewGroup,false);

        }

        //variaveis
        TextView title;

        //busca de ids do layout
        title= view.findViewById(R.id.titulo);
        //muda o id do layout
        title.setText(filmes.get(i).getTitulo());

        return view;
    }
}

