package br.com.livroandroid.carrosfinal.Domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rrafael on 31/10/2016.
 */

public class CarroDB extends SQLiteOpenHelper {
    private static final String TAG="sql";
    public static final String NOME_BANCO="livro_android.sqlite";
    private static final int VERSAO_BANCO=1;

    public CarroDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando tabela carro...");
        db.execSQL("create table if not exists carro (_id integer primary key autoincrement,nome text,desc text,url_foto text,url_info text,url_video text, latitude text, longitude text, tipo text);");
        Log.d(TAG,"Tabela criada com sucesso.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Caso mude a versão do banco de dados, podemos executar um SQL aqui
    }

    //Insere um novo carro, ou atualiza se já existe

    public long save(Carro carro) {
        long id= carro.id;
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("nome",carro.nome);
            values.put("desc",carro.desc);
            values.put("url_foto",carro.urlFoto);
            values.put("url_info",carro.urlInfo);
            values.put("url_video",carro.urlVideo);
            values.put("latitude",carro.lagitude);
            values.put("longitude",carro.longitude);
            values.put("tipo",carro.tipo);
            if (id != 0) {
                String _id = String.valueOf(carro.id);
                String[] whereArgs = new String[]{_id};
                //update carro set values = ... where _id=?
                int count = db.update("carro", values, "_id=?", whereArgs);
                return count;
            } else {
                // insert into carro values (...)
                id = db.insert("carro","",values);
                return id;
            }
        } finally {
            db.close();
        }
    }

    //Deleta o Carro
    public int delete(Carro carro) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            // delete from carro  where _id=?
            int count = db.delete("carro", "_id=?", new String[]{String.valueOf(carro.id)});
            Log.i(TAG,"Deletou [" + count + "] registro.");
            return count;
        } finally {
            db.close();
        }
    }

    //Deleta os carros pelo tipofornecido
    public  int deletaCarrosByTipo(String tipo) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            // delete from carro where _id=?
            int count = db.delete("carro", "tipo=?", new String[]{tipo});
            Log.i(TAG,"Deletou [" + count + "] registros.");
            return count;
        } finally {
            db.close();
        }
    }

    //Consulta a lista com todos os carros
    public List<Carro> findAll() {
        SQLiteDatabase db = getWritableDatabase();
        try {
            //select * from carro
            Cursor c = db.query("carro",null,null,null,null,null,null,null);
            return toList(c);
        } finally {
            db.close();
        }
    }

    public List<Carro> FindAllByTipo(String tipo) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            //select * from carro where tipo = ?
            Cursor c = db.query("carro",null,"tipo = '" + tipo + "'",null,null,null,null);
            return toList(c);
        } finally {
            db.close();
        }
    }

    //Lê o cursor e cria a lista de carros
    private List<Carro> toList(Cursor c) {
        List<Carro> carros = new ArrayList<>();
        try {
            if (c.moveToFirst() && c != null) {
                do {
                    Carro carro = new Carro();
                    carro.id = c.getLong(c.getColumnIndex("_id"));
                    carro.nome = c.getString(c.getColumnIndex("nome"));
                    carro.desc = c.getString(c.getColumnIndex("desc"));
                    carro.urlInfo = c.getString(c.getColumnIndex("url_info"));
                    carro.urlFoto = c.getString(c.getColumnIndex("url_foto"));
                    carro.urlVideo = c.getString(c.getColumnIndex("url_video"));
                    carro.lagitude = c.getString(c.getColumnIndex("latitude"));
                    carro.longitude = c.getString(c.getColumnIndex("longitude"));
                    carro.tipo = c.getString(c.getColumnIndex("tipo"));
                    carros.add(carro);
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
        return carros;
    }
    // Executa um sql
        public void execSQL(String sql) {
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.execSQL(sql);
            } finally {
                db.close();
            }
        }
    // Executa um sql
        public void execSQL(String sql, Object[] args) {
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.execSQL(sql, args);
            } finally {
                db.close();
            }
        }
}
