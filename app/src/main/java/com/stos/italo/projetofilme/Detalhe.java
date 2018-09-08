package com.stos.italo.projetofilme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Detalhe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        getSupportActionBar().setTitle("Detalhe do Filme");
    }
}
