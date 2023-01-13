package com.example.basededatosrom.modelo.db.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ruta")

public class Ruta {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "id_ruta")
    private int id_ruta;
    @ColumnInfo(name= "origen")
    private String origen;
    @ColumnInfo(name= "destino")
    private String destino;

    public Ruta(String origen, String destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public int getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(int id_ruta) {
        this.id_ruta = id_ruta;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
