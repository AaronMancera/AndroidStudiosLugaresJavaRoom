package com.example.basededatosrom.modelo.db.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lugar")
public class Lugar {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_lugar")
    private int id_lugar;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "descripcion")
    private String descripcion;

    public Lugar(String nombre) {
        this.nombre = nombre;
    }

    public int getId_lugar() {
        return id_lugar;
    }

    public void setId_lugar(int id_lugar) {
        this.id_lugar = id_lugar;
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
