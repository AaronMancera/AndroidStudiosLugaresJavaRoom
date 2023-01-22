package com.example.basededatosrom;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;

import com.example.basededatosrom.modelo.db.AppDb;
import com.example.basededatosrom.modelo.db.entidades.Avion;
import com.example.basededatosrom.modelo.db.entidades.Lugar;
import com.example.basededatosrom.modelo.db.entidades.Ruta;
import com.example.basededatosrom.modelo.db.entidades.RutaAvion;
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
        /*
        for(String ciudad:ciudades){
            conexion.getDaoLugar().insertLugar(new Lugar(ciudad));
        }
         */
        Lugar l1=new Lugar(ciudades[0]);
        conexion.getDaoLugar().insertLugar(l1);
        Lugar l2=new Lugar(ciudades[1]);
        conexion.getDaoLugar().insertLugar(l2);

        //Para insertar la fk tenemos que recoger el lugar de la base de datos mediante una query
        l1=conexion.getDaoLugar().verLugarByName(l1.getNombre());
        l2=conexion.getDaoLugar().verLugarByName(l2.getNombre());

        //Insertamos rutas en la base de datos
        Ruta r=new Ruta(l1.getId_lugar()
                ,l2.getId_lugar());
        conexion.getDaoRuta().insertRuta(r);


        //Guardamos los lugares de la bd en una lista
        List<Lugar> lugares=conexion.getDaoLugar().verLugar();
        //Guardamos las rutas de la bd en una lista
        List<Ruta> rutas=conexion.getDaoRuta().verRuta();
        //Mostrar los lugares recogiedos de la base de datos
        for(Lugar lugar:lugares){
            Log.d("room","Guardado -> Lugar: "+lugar.getNombre());
        }
        //Mostrar las rutas recogiedos de la base de datos
        for(Ruta ruta:rutas){
            Log.d("room","Guardado -> Origen: "+ruta.getOrigen() +" Destino: "+ruta.getDestino());
        }



        //Modificar un lugar
        Lugar l=conexion.getDaoLugar().verLugarByName("Sevilla");
        l.setDescripcion("Tiene un color especial");
        conexion.getDaoLugar().updateLugar(l);
        //Modificar una ruta
        r=conexion.getDaoRuta().verRutaByDestino(1);
        Lugar nuevoLugar=new Lugar("¿Donde caemos gente?");
        conexion.getDaoLugar().insertLugar(nuevoLugar);
        nuevoLugar=conexion.getDaoLugar().verLugarByName(nuevoLugar.getNombre());
        r.setDestino(nuevoLugar.getId_lugar());
        conexion.getDaoRuta().updateRuta(r);

        //Guardamos los lugares de la bd en una lista
        lugares=conexion.getDaoLugar().verLugar();
        //Guradamos las rutas de la bd en una lista
        rutas=conexion.getDaoRuta().verRuta();


        //Mostrar los lugares
        for(Lugar lugar:lugares){
            Log.d("room","Guardado -> Lugar: "+lugar.getNombre());
        }
        //Mostrar las rutas
        for(Ruta ruta:rutas){
            Log.d("room","Guardado -> Origen: "+ruta.getOrigen() +" Destino: "+ruta.getDestino());
        }

        
        //Creamos un avion
        Avion avion=new Avion("Avion 1");
        avion.setDescripcion("Un piloto sin licencia");
        conexion.getDaoAvion().insertAvion(avion);
        //Recogemos los aviones
        List<Avion> aviones=conexion.getDaoAvion().verAvion();
        //Visualizamos lo insertado
        for(Avion a:aviones){
            Log.d("room","Guardado avion ->"+a.getNombre());
        }
        Avion a=conexion.getDaoAvion().verAvionByName("Avion 1");
        Log.d("room","Guardado avion buscado ->"+a.getNombre());
        Ruta ru=conexion.getDaoRuta().verRutaByOrigen(1);
        Log.d("room","Prueba ->"+ru.getId_ruta());
        Log.d("room","Prueba ->"+a.getId_avion());
        List<RutaAvion> eliminacionDeRutasAvion=conexion.getDaoRutaAvion().verAvionRuta();
        for(RutaAvion ra:eliminacionDeRutasAvion){
            conexion.getDaoRutaAvion().deleteRutaAvion(ra);
        }
        RutaAvion rutaAvion=new RutaAvion(ru.getId_ruta(),a.getId_avion());

        conexion.getDaoRutaAvion().insertRutaAvion(rutaAvion);
        List<RutaAvion> rutaAvionList=conexion.getDaoRutaAvion().verAvionRuta();
        for(RutaAvion rutaAvion1:rutaAvionList){
            Log.d("room","Guardado rutaAvion -> Avion: "+rutaAvion1.getId_avion()+" Ruta: "+rutaAvion1.getId_ruta());
        }
        //Eliminamos una ruta - Para eliminar una ruta recuerda que es sql y debes eliminar el resto primero, las relaciones donde este estos valores
        //conexion.getDaoRuta().deleteRuta(r);
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