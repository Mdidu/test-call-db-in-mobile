package com.example.testcalldbinmobile;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

public class ClientHttp extends AsyncTask<String, Void, String> {

    // traitement des données saisie par l'utilisateur
    @Override
    protected String doInBackground(String... strings) {
        try {
            HttpClient client = new DefaultHttpClient();// Classe fille de HttpClient qui est abstraite
            HttpGet get = new HttpGet(strings[0]);
            HttpResponse result = client.execute(get);

            // Recupère la réponse et la transforme en String
            String dataJsonToString = EntityUtils.toString(result.getEntity());

            JSONArray jsonArray = new JSONArray(dataJsonToString);
            StringBuilder stringToDisplayAfterJSONArrayTreatment = new StringBuilder();

            for(int i = 0; i < jsonArray.length(); i++) {

                JSONObject objectJson = jsonArray.getJSONObject(i);
                Iterator key = objectJson.keys();

                while(key.hasNext()) {

                    String value = key.next().toString();

                    // Ajoute les valeurs à afficheur après le traitement du JSONArray
                    stringToDisplayAfterJSONArrayTreatment
                            .append(objectJson.getString(value))
                            .append("\n");
                }
            }
            MainActivity.setTxt(stringToDisplayAfterJSONArrayTreatment);
//            txt.setText(stringToDisplayAfterJSONArrayTreatment);
            return dataJsonToString;
        } catch (JSONException | IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Arrête la connexion
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}