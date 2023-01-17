package com.example.basededatosrom.modelo.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.basededatosrom.modelo.db.dao.DaoLugar;
import com.example.basededatosrom.modelo.db.dao.DaoRuta;
import com.example.basededatosrom.modelo.db.entidades.Lugar;
import com.example.basededatosrom.modelo.db.entidades.Ruta;

@Database(entities={Lugar.class , Ruta.class},version=1)
public abstract class AppDb extends RoomDatabase {
    private static AppDb INSTANCE;

    public abstract DaoLugar getDaoLugar();

    public abstract DaoRuta getDaoRuta();

    public static AppDb getAppOb(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                    AppDb.class,
                    "rutasbd")
                    .allowMainThreadQueries().build();
        }
        return  INSTANCE;
    }

    public static void destroyInstance(){INSTANCE=null;}
}
