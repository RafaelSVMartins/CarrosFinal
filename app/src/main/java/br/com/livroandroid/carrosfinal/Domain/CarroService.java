package br.com.livroandroid.carrosfinal.Domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rrafael on 14/10/2016.
 */

public class CarroService  {
    public static List<Carro> getCarros(Context context, int tipo) {
        String tipoString = context.getString(tipo);
        List<Carro> carros = new ArrayList<Carro>();
        for (int i = 0; i <20; i++) {
            Carro c = new Carro();
            c.nome = "Carro " + tipoString + i;
            c.desc = "Desc " + i;
            c.urlFoto="http://www.livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png";
            carros.add(c);
        }
        return carros;
    }
}
