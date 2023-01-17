package com.example.basededatosrom.modelo.db.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.basededatosrom.modelo.db.entidades.Lugar;

import java.util.List;

@Dao
public interface DaoLugar {
    //Inserta un lugar
    @Insert
    void insertLugar(Lugar lugar);

    //Actualiza un lugar
    @Update
    void updateLugar(Lugar lugar);

    //Borra un lugar
    @Delete
    void deleteLugar(Lugar lugar);

    //Borra todos los lugares
    @Query("delete from lugar")
    void borrarLugares();

    //Busca por nombre
    @Query("select * from lugar where nombre like :nombre")
    Lugar verLugarByName(String nombre);

    //Busca todos los lugares
    @Query("select * from lugar")
    List<Lugar> verLugar();

    //Busca por id
    @Query("select * from lugar where id_lugar like :id")
    Lugar verLugarById(int id);

}
