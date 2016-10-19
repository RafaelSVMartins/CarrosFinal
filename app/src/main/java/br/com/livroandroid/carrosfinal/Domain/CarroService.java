package br.com.livroandroid.carrosfinal.Domain;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.carrosfinal.R;
import livroandroid.lib.utils.FileUtils;
import livroandroid.lib.utils.HttpHelper;
import livroandroid.lib.utils.XMLUtils;

/**
 * Created by Rrafael on 14/10/2016.
 */

public class CarroService  {
   private static boolean LOG_ON=false;
    private static final  String TAG= "CarroService";
    private static final
    String URL="http://www.livroandroid.com.br/livro/carros/carros_{tipo}.json";

    public static List<Carro> getCarros(Context context, int tipo) throws IOException {
        String tipoString = getTipo(tipo);
        String url = URL.replace("{tipo}",tipoString);
        HttpHelper http = new HttpHelper();

        List<Carro> carros = null;
        try {
            //String xml = readFile(context,tipo);
            String json = http.doGet(url);
            carros = parserJSON(context,json);
                
        } catch (Exception e) {
            Log.e(TAG, "Erro em exibir os carros: " + e.getMessage(),e);
        }
        return carros;
    }

    private static String getTipo(int tipo) {
        if(tipo == R.string.classicos) {
            return "classicos";
        } else if(tipo == R.string.esportivos) {
            return "esportivos";
        }

        return "luxo";
    }

    private static String readFile(Context context, int tipo) throws IOException {
        if (tipo == R.string.classicos) {
            return FileUtils.readRawFileString(context,R.raw.carros_classicos,"UTF-8");
        } else if (tipo == R.string.esportivos) {
            return FileUtils.readRawFileString(context,R.raw.carros_esportivos,"UTF-8");
        }
        return FileUtils.readRawFileString(context,R.raw.carros_luxo,"UTF-8");
    }

    private static List<Carro> parserJSON(Context context, String json) throws IOException {
        List<Carro> carros = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(json);
            JSONObject obj = root.getJSONObject("carros");
            JSONArray jsonCarros = obj.getJSONArray("carro");

            for (int i=0; i < jsonCarros.length(); i++) {
                JSONObject jsonCarro = jsonCarros.getJSONObject(i);
                Carro c = new Carro();
                c.nome = jsonCarro.getString("nome");
                c.desc = jsonCarro.getString("desc");
                c.urlFoto = jsonCarro.getString("url_foto");
                c.urlInfo = jsonCarro.getString("url_info");
                c.urlVideo = jsonCarro.getString("url_video");
                c.lagitude = jsonCarro.getString("latitude");
                c.longitude = jsonCarro.getString("longitude");

                if (LOG_ON) {
                    Log.d(TAG, "Carro: " + c.nome + c.urlFoto);
                }
                carros.add(c);
                if (LOG_ON) {
                    Log.d(TAG, carros.size() + "encontrados. ");
                }


            }
        } catch (JSONException e) {
            throw new IOException(e.getMessage(),e);
        }

        return carros;
    }

    private static List<Carro> parserXML (Context context, String xml) {
        List<Carro> carros = new ArrayList<>();
        Element root = XMLUtils.getRoot(xml,"UTF-8");
        List<Node> nodeCarros = XMLUtils.getChildren(root,"carro");
        for (Node node : nodeCarros) {
            Carro c = new Carro();

            c.nome = XMLUtils.getText(node,"nome");
            c.desc = XMLUtils.getText(node,"desc");
            c.urlFoto = XMLUtils.getText(node,"url_foto");
            c.urlInfo = XMLUtils.getText(node,"url_info");
            c.urlVideo = XMLUtils.getText(node,"url_video");
            c.lagitude = XMLUtils.getText(node,"latitude");
            c.longitude = XMLUtils.getText(node,"longitude");


            if (LOG_ON) {
                Log.d(TAG, "Carro: " + c.nome + " > " + c.urlFoto);
            }
            carros.add(c);

            if (LOG_ON) {
                Log.d(TAG, "Carro: " + carros.size() + "encontrados");
            }
        }

        return carros;
    }
}
