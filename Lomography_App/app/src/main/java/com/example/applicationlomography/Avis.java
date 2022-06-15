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
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Avis extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private RadioGroup rgAvis, rgAvis2;
    private Button btValider;
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
        setContentView(R.layout.activity_avis);

        this.btValider=(Button) findViewById(R.id.idAvisValider);
        this.rgAvis=(RadioGroup) findViewById(R.id.idAvisApp);
        this.rgAvis2=(RadioGroup) findViewById(R.id.idAvisApp4);

        this.btValider.setOnClickListener(this);

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
                Intent intentHome = new Intent(Avis.this, MainActivity.class);
                startActivity(intentHome);
                break;
            case R.id.nav_commandes:
                Intent intentCommande = new Intent(Avis.this, LivraisonActivity.class);
                startActivity(intentCommande);
                break;
            case R.id.nav_login:
                Intent intentLogin = new Intent(Avis.this, Connexion.class);
                startActivity(intentLogin);
                break;
            case R.id.nav_profil:
                Intent intentProfil = new Intent(Avis.this, Profil.class);
                startActivity(intentProfil);
                break;
            case R.id.nav_logout:
                Intent intentLogout = new Intent(Avis.this, Logout.class);
                startActivity(intentLogout);
                break;
            case R.id.nav_mail:
                Intent intentMail = new Intent(Avis.this, Email.class);
                startActivity(intentMail);
                break;
            case R.id.nav_chat:
                Intent intentChat = new Intent(Avis.this, Chat.class);
                startActivity(intentChat);
                break;
            case R.id.nav_avis:
                Intent intentAvis = new Intent(Avis.this, Avis.class);
                startActivity(intentAvis);
                break;
            case R.id.nav_politique:
                Intent intentPolitique = new Intent(Avis.this, APropos.class);
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
        if(view.getId() == R.id.idAvisValider){

            int score = 0;
            if(this.rgAvis.getCheckedRadioButtonId()==R.id.idAvisApp1)
            {
                score = score+1;
            }
            if(this.rgAvis.getCheckedRadioButtonId()==R.id.idAvisApp2)
            {
                score = score+0;
            }
            if(this.rgAvis.getCheckedRadioButtonId()==R.id.idAvisApp3)
            {
                score = score-1;
            }
            if(this.rgAvis2.getCheckedRadioButtonId()==R.id.idAvisApp5)
            {
                score = score+1;
            }
            if(this.rgAvis2.getCheckedRadioButtonId()==R.id.idAvisApp6)
            {
                score = score+0;
            }
            if(this.rgAvis2.getCheckedRadioButtonId()==R.id.idAvisApp7)
            {
                score = score-1;
            }

            if (score>0) {
                Toast.makeText(this, "Merci pour avis positif !  Score :" + score, Toast.LENGTH_SHORT).show();
                Intent unIntent = new Intent(this, MainActivity.class);
                this.startActivity(unIntent);
            }
            if (score==0) {
                Toast.makeText(this, "Merci pour avis neutre ! Score : " + score, Toast.LENGTH_SHORT).show();
                Intent unIntent = new Intent(this, MainActivity.class);
                this.startActivity(unIntent);
            }
            if (score<0) {
                Toast.makeText(this, "Merci pour avis bien que celui soit négatif ! Score :" + score, Toast.LENGTH_SHORT).show();
                Intent unIntent = new Intent(this, MainActivity.class);
                this.startActivity(unIntent);
            }
        }
    }
}