package com.example.applicationlomography;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Commandes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    //Bonjour bg
    private Button btRetourCmdToMenu, btCmdToSAV;
    private ListView lvListeCmd;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    //--------------------Communication BDD---------------------//
    Gson gson = new GsonBuilder().setLenient().create();

    Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("http://172.16.1.29/Java_Api/")
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build();
    //PHPApi phpApi = retrofit.create(PHPApi.class);
    //-------------------- Fin ---------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandes);

        /*----------------------Hooks--------------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        /*----------------------Toolbar--------------------*/
        setSupportActionBar(toolbar);

        /*----------------------Navigation Drawer Menu--------------------*/
        //Hide or show items
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_profil).setVisible(false);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        this.btRetourCmdToMenu = (Button) findViewById(R.id.idRetourCmdToMenu);
        this.btCmdToSAV = (Button) findViewById(R.id.idCmdToSAV);
        this.lvListeCmd = (ListView) findViewById(R.id.idListeCmd);
        //Rendre les boutons écoutables
        this.btRetourCmdToMenu.setOnClickListener(this);
        this.btCmdToSAV.setOnClickListener(this);

        //remplir liste de commandes
        ArrayList<String> lesCommandes = new ArrayList<>();
        lesCommandes.add("N° 123456"+ " | " + "Expédition prévue le : 08/03/22" + "\n" + "En cours");
        lesCommandes.add("N° 654321"+ " | " + "N/A" + "\n" + "Annulée");
        lesCommandes.add("N° 098765"+ " | " + "Réception prevue le : 10/03/2022" + "\n" + "Envoyée");
        lesCommandes.add("N° 567890" + " | " + "Reçue le : 02/03/2022" + "\n" + "Reçue");
        for(String uneCommande : lesCommandes){

        }
        ArrayAdapter unAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lesCommandes);
        this.lvListeCmd.setAdapter(unAdapter);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.idRetourCmdToMenu){
            Intent unIntent = new Intent(this, MainActivity.class);
            this.startActivity(unIntent);
        } else {
            if(view.getId() == R.id.idCmdToSAV){
                Intent unIntent = new Intent(this, Email.class);
                this.startActivity(unIntent);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent intentHome = new Intent(Commandes.this, MainActivity.class);
                startActivity(intentHome);
                break;
            case R.id.nav_commandes:
                Intent intentCommande = new Intent(Commandes.this, Commandes.class);
                startActivity(intentCommande);
                break;
            case R.id.nav_login:
                Intent intentLogin = new Intent(Commandes.this, Connexion.class);
                startActivity(intentLogin);
                break;
            case R.id.nav_profil:
                Intent intentProfil = new Intent(Commandes.this, Profil.class);
                startActivity(intentProfil);
                break;
            case R.id.nav_logout:
                Intent intentLogout = new Intent(Commandes.this, Logout.class);
                startActivity(intentLogout);
                break;
            case R.id.nav_mail:
                Intent intentMail = new Intent(Commandes.this, Email.class);
                startActivity(intentMail);
                break;
            case R.id.nav_chat:
                Intent intentChat = new Intent(Commandes.this, Chat.class);
                startActivity(intentChat);
                break;
            case R.id.nav_avis:
                Intent intentAvis = new Intent(Commandes.this, Avis.class);
                startActivity(intentAvis);
                break;
            case R.id.nav_politique:
                Intent intentPolitique = new Intent(Commandes.this, APropos.class);
                startActivity(intentPolitique);
                break;
            case R.id.nav_partager:
                Toast.makeText(this, "Partagez notre application", Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}