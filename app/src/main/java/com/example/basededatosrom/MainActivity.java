package com.example.basededatosrom;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;

import com.example.basededatosrom.modelo.db.AppDb;
import com.example.basededatosrom.modelo.db.entidades.Lugar;
import com.example.basededatosrom.modelo.db.entidades.Ruta;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basededatosrom.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        configView();
        //Lo de arriba se crea todo automatico y es del navegatio drawer

        //Creacion de listas de ciudades, conexion y metodos.

        String [] ciudades={"Sevilla","Huelva"};
        //Creacion de la conexion de base de datos
        AppDb conexion=AppDb.getAppOb(getApplicationContext());

        //Metemos la lista de la base de datos
        for(String ciudad:ciudades){
            conexion.getDaoLugar().insertLugar(new Lugar(ciudad));
        }

        //Insertamos rutas en la base de datos
        conexion.getDaoRuta().insertRuta(new Ruta(ciudades[0],ciudades[1]));
        conexion.getDaoRuta().insertRuta(new Ruta(ciudades[1],ciudades[0]));


        //Guardamos los lugares de la bd en una lista
        List<Lugar> lugares=conexion.getDaoLugar().verLugar();
        //Guardamos las rutas de la bd en una lista
        List<Ruta> rutas=conexion.getDaoRuta().verRuta();
        //Mostrar los lugares
        for(Lugar lugar:lugares){
            Log.d("room","Guardado -> Lugar: "+lugar.getNombre());
        }
        //Mostrar las rutas
        for(Ruta ruta:rutas){
            Log.d("room","Guardado -> Origen: "+ruta.getOrigen() +" Destino: "+ruta.getDestino());
        }



        //Modificar un lugar
        Lugar l=conexion.getDaoLugar().verLugarByName("Sevilla");
        l.setDescripcion("Tiene un color especial");
        conexion.getDaoLugar().updateLugar(l);
        //Modificar una ruta
        Ruta r=conexion.getDaoRuta().verRutaByDestino("Huelva");
        r.setDestino("¿Donde caemos gente?");
        conexion.getDaoRuta().updateRuta(r);

        //Guardamos los lugares de la bd en una lista
        lugares=conexion.getDaoLugar().verLugar();
        //Guradamos las rutas de la bd en una lista
        rutas=conexion.getDaoRuta().verRuta();
        //Eliminamos una ruta
        conexion.getDaoRuta().deleteRuta(new Ruta(ciudades[0],ciudades[1]));

        //Mostrar los lugares
        for(Lugar lugar:lugares){
            Log.d("room","Guardado -> Lugar: "+lugar.getNombre());
        }
        //Mostrar las rutas
        for(Ruta ruta:rutas){
            Log.d("room","Guardado -> Origen: "+ruta.getOrigen() +" Destino: "+ruta.getDestino());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void configView(){
        //Desde aqui
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Hasta aqui podemos crear un metodo llamado configView(); y lo llamamos en el onCreate
    }
}