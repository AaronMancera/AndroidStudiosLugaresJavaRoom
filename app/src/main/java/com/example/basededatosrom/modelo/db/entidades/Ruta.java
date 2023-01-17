package com.example.basededatosrom.modelo.db.entidades;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "ruta",
        foreignKeys = {
                @ForeignKey(entity=Lugar.class,
                parentColumns = "id_lugar",
                childColumns = "origen",
                onDelete =  CASCADE),
                @ForeignKey(entity=Lugar.class,
                parentColumns = "id_lugar",
                childColumns = "destino",
                //Por si el import no funciona bien
                //onDelete = ForeignKey.CASCADE)
                onDelete =  CASCADE)
        }
)
public class Ruta {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "id_ruta")
    private int id_ruta;
    @ColumnInfo(name= "origen")
    private int origen;
    @ColumnInfo(name= "destino")
    private int destino;

    public Ruta(int origen, int destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public int getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(int id_ruta) {
        this.id_ruta = id_ruta;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }
}
