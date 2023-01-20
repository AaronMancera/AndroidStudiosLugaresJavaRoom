package com.example.basededatosrom.modelo.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.basededatosrom.modelo.db.entidades.Avion;
import com.example.basededatosrom.modelo.db.entidades.Ruta;
import com.example.basededatosrom.modelo.db.entidades.RutaAvion;

import java.util.List;

@Dao
public interface DaoRutaAvion {
    //Inserta un rutaAvion
    @Insert
    void insertRutaAvion(RutaAvion rutaAvion);
    //Actualiza un avion
    @Update
    void updateRutaAvion(RutaAvion rutaAvion);

    //Borra un RutaAvion
    @Delete
    void deleteRutaAvion(RutaAvion rutaAvion);

    //Borra todos los RutaAvion
    @Query("delete from rutaAvion")
    void borrarRutaAvion();
    //Ver todos los rutaAvion
    @Query("SELECT * FROM rutaAvion")
    List<RutaAvion> verAvionRuta();
    //Ver a una ruta avion completa
    @Query("SELECT * FROM ruta INNER JOIN avion ON ruta.id_ruta=avion.id_avion WHERE ruta.id_ruta=:id_ruta AND avion.id_avion=:id_avion")
    List<Ruta> verAvionRutaByIds(int id_ruta, int id_avion);

}
