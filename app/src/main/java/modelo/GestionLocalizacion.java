package modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Jorge on 06/04/2017.
 */

public class GestionLocalizacion {

    String nombredb;
    Ayudante helper;
    SQLiteDatabase db;
    public GestionLocalizacion(Context ctx, String nombreBd){
        this.nombredb=nombreBd;
        helper=new Ayudante(ctx,nombredb);
        db=helper.getWritableDatabase();
    }

    public void guardarPosicion(Posicion p){
        ContentValues valores=new ContentValues();
        valores.put("latitud",p.getLatitud());
        valores.put("longitud",p.getLongitud());
        db.insert(nombredb,null,valores);
    }

    public Cursor mostrarDatos(){
        ArrayList<Posicion> pos=new ArrayList<>();
        Cursor c=db.query(nombredb,null,null,null,null,null,null);
        /*while(c.moveToNext()){
            Posicion p=new Posicion(Double.parseDouble(c.getString(1)),Double.parseDouble(c.getString(2)));
            pos.add(p);
        }
        return pos;*/
        return c;
    }

}
