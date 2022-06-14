package com.example.applicationlomography;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.applicationlomography.services.IListenerAPIConnexion;
import com.example.applicationlomography.services.ServerApi;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connexion extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, IListenerAPIConnexion {
    private Button btSeConnecter, btVoirCmd, btRetourLoginToMenu;
    private EditText txtMail, txtMdp;
    private boolean login=false;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        this.txtMail=(EditText) findViewById(R.id.idMail);
        this.txtMdp=(EditText) findViewById(R.id.idMdp);
        this.btSeConnecter=(Button) findViewById(R.id.idSeConnecter);
        //this.btVoirCmd=(Button) findViewById(R.id.idLoginToCmd);
        //this.btRetourLoginToMenu=(Button) findViewById(R.id.idRetourLoginToMenu);

        this.btSeConnecter.setOnClickListener(this);
        //this.btRetourLoginToMenu.setOnClickListener(this);
        //this.btVoirCmd.setOnClickListener(this);

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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent intentHome = new Intent(Connexion.this, MainActivity.class);
                startActivity(intentHome);
                break;
            case R.id.nav_commandes:
                Intent intentCommande = new Intent(Connexion.this, CommandeActivity.class);
                startActivity(intentCommande);
                break;
            case R.id.nav_login:
                Intent intentLogin = new Intent(Connexion.this, Connexion.class);
                startActivity(intentLogin);
                break;
            case R.id.nav_profil:
                Intent intentProfil = new Intent(Connexion.this, Profil.class);
                startActivity(intentProfil);
                break;
            case R.id.nav_logout:
                Intent intentLogout = new Intent(Connexion.this, Logout.class);
                startActivity(intentLogout);
                break;
            case R.id.nav_mail:
                Intent intentMail = new Intent(Connexion.this, Email.class);
                startActivity(intentMail);
                break;
            case R.id.nav_chat:
                Intent intentChat = new Intent(Connexion.this, Chat.class);
                startActivity(intentChat);
                break;
            case R.id.nav_avis:
                Intent intentAvis = new Intent(Connexion.this, Avis.class);
                startActivity(intentAvis);
                break;
            case R.id.nav_politique:
                Intent intentPolitique = new Intent(Connexion.this, APropos.class);
                startActivity(intentPolitique);
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
        if(view.getId() == R.id.idSeConnecter){
            String mailValue = this.txtMail.getText().toString();
            String mdpValue = this.txtMdp.getText().toString();
            ServerApi.connexion(this, mailValue, mdpValue, this);
        } /*else {
            if(view.getId() == R.id.idLoginToCmd){
                Intent unIntent = new Intent(this, CommandeActivity.class);
                this.startActivity(unIntent);
            } else {
                if (view.getId() == R.id.idRetourLoginToMenu) {
                    Intent unIntent = new Intent(this, MainActivity.class);
                    this.startActivity(unIntent);
                }
            }
        }*/
    }

    @Override
    public void isConnect(boolean connect, int userid) {
        if (connect) {
            Intent unIntent = new Intent(Connexion.this, MainActivity.class);
            unIntent.putExtra("isconnected", true);
            unIntent.putExtra("userid", userid);
            this.startActivity(unIntent);
            Toast.makeText(this, "Connexion réussie : ", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Connexion impossible, vérifiez vos identifiants : ", Toast.LENGTH_LONG).show();
        }
    }
}