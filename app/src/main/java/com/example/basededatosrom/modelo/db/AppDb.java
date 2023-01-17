package com.example.basededatosrom.modelo.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries().build();
        }
        return  INSTANCE;
    }

    //
    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE avion (id_avion INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,nombre VARCHAR(255),descripcion VARCHAR(255))");
            database.execSQL("CREATE TABLE rutaAvion(id_ruta INTEGER NOT NULL, id_avion INTEGER NOT NULL, PRIMARY KEY(id_ruta,id_avion), FOREIGN KEY(id_ruta) REFERENCES ruta(id_ruta),FOREIGN KEY(id_avion) REFERENCES avion(id_avion))");
        }
    };



    public static void destroyInstance(){INSTANCE=null;}
}
