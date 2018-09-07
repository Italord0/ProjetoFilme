package com.stos.italo.projetofilme;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListaFilmes extends AppCompatActivity {

    private ListView l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);

        l1= (ListView) findViewById(R.id.l1);
        JsonTask jsonTask = new JsonTask();
        jsonTask.execute();
    }

    public class JsonTask extends AsyncTask<String,String,List<Filme>>
    {
        @Override
        protected List<Filme> doInBackground(String... strings)
        {
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;
            String JsonFile;
            try {
                URL url = new URL("http://processo.stos.mobi/app/filme/listar");

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String line="";
                while ((line = bufferedReader.readLine())!= null)
                {
                    stringBuffer.append(line);
                }

                JsonFile = stringBuffer.toString();

                JSONObject mainObject = new JSONObject(JsonFile);
                JSONArray filmeArray = mainObject.getJSONArray("JSON");
                List<Filme> filmes = new ArrayList<>();

                //loopzinho pra gerar varios objetos
                for(int i=0; i<filmeArray.length();i++)
                {
                    JSONObject ArrayObjects = filmeArray.getJSONObject(i);
                    Filme filme = new Filme();
                    filme.setId(ArrayObjects.getString("id"));
                    filme.setTitulo(ArrayObjects.getString("descricao"));
                    filme.setCapa(ArrayObjects.getString("capa"));
                    filme.setUrl_imagem(ArrayObjects.getString("Url_imagem"));
                }



               //return  stringBuffer.toString() ;
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }catch (JSONException e)
            {
                e.printStackTrace();
            }



            return null;
        }

        @Override
        protected void onPostExecute(List<Filme> s) {
            super.onPostExecute(s);

        }
    }
}
