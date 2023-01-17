package com.example.basededatosrom.modelo.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.basededatosrom.modelo.db.entidades.Avion;

import java.util.List;

@Dao
public interface DaoAvion {
    //Inserta un avion
    @Insert
    void insertAvion(Avion avion);
    //Actualiza un avion
    @Update
    void updateAvion(Avion avion);

    //Borra un avion
    @Delete
    void deleteAvion(Avion avion);

    //Borra todos los avion
    @Query("delete from avion")
    void borrarAviones();

    //Busca por nombre
    @Query("select * from avion where nombre like :nombre")
    Avion verAvionByName(String nombre);

    //Busca todos los aviones
    @Query("select * from avion")
    List<Avion> verAvion();

    //Busca por id
    @Query("select * from avion where id_avion like :id")
    Avion verAvionById(int id);
}
