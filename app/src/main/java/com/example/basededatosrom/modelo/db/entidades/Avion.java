package com.example.basededatosrom.modelo.db.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "avion")
public class Avion {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "id_avion")
    private int id_avion;
    @ColumnInfo(name= "nombre")
    private String nombre;
    @ColumnInfo(name= "descripcion")
    private String descripcion;

    public Avion(String nombre) {
        this.nombre = nombre;
    }

    public int getId_avion() {
        return id_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
