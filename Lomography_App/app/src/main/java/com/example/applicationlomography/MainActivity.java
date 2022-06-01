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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private static HashMap<String, Commandes> lesCommandes = new HashMap<>();
    private RelativeLayout boxCommandes, boxProfil, boxEmail, boxChat, boxAvis, boxAPropos;

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
        setContentView(R.layout.activity_main);

        //Boutons
        this.boxCommandes=(RelativeLayout) findViewById(R.id.idBoxCommandes);
        this.boxProfil=(RelativeLayout) findViewById(R.id.idBoxConnexion);
        this.boxEmail=(RelativeLayout) findViewById(R.id.idBoxEmail);
        this.boxChat=(RelativeLayout) findViewById(R.id.idBoxChat);
        this.boxAvis=(RelativeLayout) findViewById(R.id.idBoxAvis);
        this.boxAPropos=(RelativeLayout) findViewById(R.id.idBoxAPropos);
        //Rendre les boutons cliquables
        this.boxCommandes.setOnClickListener(this);
        this.boxProfil.setOnClickListener(this);
        this.boxEmail.setOnClickListener(this);
        this.boxChat.setOnClickListener(this);
        this.boxAvis.setOnClickListener(this);
        this.boxAPropos.setOnClickListener(this);

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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch(menuItem.getItemId())
        {
            case R.id.nav_home:
                break;
            case R.id.nav_commandes:
                Intent intentCommande = new Intent(MainActivity.this,Commandes.class);
                startActivity(intentCommande);
                break;
            case R.id.nav_login:
                Intent intentLogin = new Intent(MainActivity.this,Connexion.class);
                startActivity(intentLogin);
                break;
            case R.id.nav_profil:
                Intent intentProfil = new Intent(MainActivity.this,Profil.class);
                startActivity(intentProfil);
                break;
            case R.id.nav_logout:
                Intent intentLogout = new Intent(MainActivity.this,Logout.class);
                startActivity(intentLogout);
                break;
            case R.id.nav_mail:
                Intent intentMail = new Intent(MainActivity.this,Email.class);
                startActivity(intentMail);
                break;
            case R.id.nav_chat:
                Intent intentChat = new Intent(MainActivity.this,Chat.class);
                startActivity(intentChat);
                break;
            case R.id.nav_avis:
                Intent intentAvis = new Intent(MainActivity.this,Avis.class);
                startActivity(intentAvis);
                break;
            case R.id.nav_politique:
                Intent intentAPropos = new Intent(MainActivity.this,APropos.class);
                startActivity(intentAPropos);
                break;
            case R.id.nav_partager:
                Toast.makeText(this, "Partagez notre application", Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.idBoxCommandes)
        {
            Intent unIntent = new Intent(this, Commandes.class);
            this.startActivity(unIntent);
        }
        else if (view.getId()==R.id.idBoxConnexion)
        {
            Intent unIntent = new Intent(this, Connexion.class);
            this.startActivity(unIntent);
        }
        else if (view.getId()==R.id.idBoxEmail)
        {
            Intent unIntent = new Intent(this, Email.class);
            this.startActivity(unIntent);
        }
        else if (view.getId()==R.id.idBoxChat)
        {
            Intent unIntent = new Intent(this, Chat.class);
            this.startActivity(unIntent);
        }
        else if (view.getId()==R.id.idBoxAvis)
        {
            Intent unIntent = new Intent(this, Avis.class);
            this.startActivity(unIntent);
        }
        else if (view.getId()==R.id.idBoxAPropos)
        {
            Intent unIntent = new Intent(this, APropos.class);
            this.startActivity(unIntent);
        }
    }

    public static Commandes getCommandes(String cmd){
        return lesCommandes.get(cmd);
    }
}