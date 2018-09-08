package com.stos.italo.projetofilme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detalhe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Filme model = (Filme) getIntent().getSerializableExtra("filme");
        String titulo = model.getTitulo();
        String descricao = model.getDescricao();
        String imagem = model.getUrl_imagem();

        getSupportActionBar().setTitle(titulo);

        TextView textView = findViewById(R.id.descricao);
        ImageView imageView = findViewById(R.id.iv);
        textView.setText(descricao);
        Picasso.with(this).load(imagem).into(imageView);
    }
}
