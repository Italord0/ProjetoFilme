package com.stos.italo.projetofilme;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListaFilmes extends AppCompatActivity {

        ListView listView;


    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);
        getSupportActionBar().setTitle("Filmes");

        listView = (ListView) findViewById(R.id.l1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i = new Intent(ListaFilmes.this, Detalhe.class);
            // passei 10 horas nessa linha abaixo meu deus do ceu kkkkk
            i.putExtra("filme" , (Serializable) listView.getItemAtPosition(position));
            startActivity(i);
        }
    });

        JsonWork jsonWork = new JsonWork();
        jsonWork.execute();
    }


    public class JsonWork extends AsyncTask<String,String,List<Filme>>{

        //requisicao http
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        String  FullJsonData;
        @Override        protected List<Filme> doInBackground(String... strings) {

            try {
                //link do json ----------------------------------------------------------------
                URL url = new URL("https://api.myjson.com/bins/11b090");
                //-----------------------------------------------------------------------------
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer= new StringBuffer();
                String line="";

                while ((line= bufferedReader.readLine())!= null){

                    stringBuffer.append(line);

                }
                FullJsonData =  stringBuffer.toString();
                List<Filme> listaFilme = new ArrayList<>();

                JSONObject jsonStartingObject = new JSONObject(FullJsonData);
                JSONArray  jsonFilmeArray = jsonStartingObject.getJSONArray("filmes");

                //loopzinho pra popular lista com os json`s
                for(int i=0; i<jsonFilmeArray.length(); i++) {

                    JSONObject jsonUnderArrayObject = jsonFilmeArray.getJSONObject(i);

                    Filme filme = new Filme();
                    filme.setId(jsonUnderArrayObject.getString("id"));
                    filme.setTitulo(jsonUnderArrayObject.getString("titulo"));
                    filme.setDescricao(jsonUnderArrayObject.getString("descricao"));
                    filme.setCapa(jsonUnderArrayObject.getString("capa"));
                    filme.setUrl_imagem(jsonUnderArrayObject.getString("url_imagem"));
                    listaFilme.add(filme);
                }

                return listaFilme;


            } catch (JSONException | IOException e) {
                e.printStackTrace();
            } finally{

                httpURLConnection.disconnect();
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        //montando os blocos com o adapter
        @Override   protected void onPostExecute(List<Filme> filmes) {
            super.onPostExecute(filmes);
            CustomAdapter adapter = new CustomAdapter(getApplicationContext(),R.layout.bloco,filmes);
            listView.setAdapter(adapter);

        }
    }

}
