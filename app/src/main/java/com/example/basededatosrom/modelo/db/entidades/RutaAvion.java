package com.example.basededatosrom.modelo.db.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "rutaAvion",
        primaryKeys = {"id_ruta","id_avion"},
        foreignKeys = {
        @ForeignKey(entity = Ruta.class,
                //El nombre del atributo en ruta
                parentColumns = "id_ruta",
                //El nombre del atributo en rutaAvion
                childColumns ="id_ruta"
                ),
        @ForeignKey(entity = Avion.class,
                //El nombre del atributo en avion
                parentColumns = "id_avion",
                //El nombre del atributo en rutaAvion
                childColumns = "id_avion"
                )
        }
)
public class RutaAvion {
        @ColumnInfo(name = "id_ruta")
        private int id_ruta;
        @ColumnInfo(name = "id_avion")
        private int id_avion;

        public int getId_ruta() {
                return id_ruta;
        }

        public void setId_ruta(int id_ruta) {
                this.id_ruta = id_ruta;
        }

        public int getId_avion() {
                return id_avion;
        }

        public void setId_avion(int id_avion) {
                this.id_avion = id_avion;
        }
}
