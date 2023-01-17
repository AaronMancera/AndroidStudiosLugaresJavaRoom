package com.example.basededatosrom.modelo.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.basededatosrom.modelo.db.entidades.Lugar;
import com.example.basededatosrom.modelo.db.entidades.Ruta;

import java.util.List;

@Dao
public interface DaoRuta {
    //Insertar una ruta
    @Insert
    void insertRuta(Ruta ruta);
    //Actualiza una ruta
    @Update
    void updateRuta(Ruta ruta);
    //Borrar una ruta
    @Delete
    void deleteRuta(Ruta ruta);
    //Buscar por origen
    @Query("select * from ruta where origen like :origen")
    Ruta verRutaByOrigen(int origen);
    //Buscar por destino
    @Query("select * from ruta where origen like :destino")
    Ruta verRutaByDestino(int destino);

    //Busca todas las rutas
    @Query("select * from ruta")
    List<Ruta> verRuta();
    //Buscar por id
    @Query("select * from ruta where id_ruta like :id")
    List<Ruta> verRutaById(int id);

}
