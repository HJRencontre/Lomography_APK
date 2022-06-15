package com.example.applicationlomography;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.applicationlomography.model.Livraison;
import com.example.applicationlomography.model.LivraisonAdapter;
import com.example.applicationlomography.services.IListenerAPI;
import com.example.applicationlomography.services.ServerApi;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class LivraisonActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, IListenerAPI {
    private ListView listView;
    private LivraisonAdapter adapter;
    private TextView textName, textDescription;
    private Button btRetourCmdToMenu, btCmdToSAV;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

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
        //Rendre les boutons écoutables
        this.btRetourCmdToMenu.setOnClickListener(this);

        ArrayList<Livraison> livraisons = new ArrayList<Livraison>();
        adapter = new LivraisonAdapter(this);
        listView = (ListView) findViewById(R.id.idLivraison);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Livraison selectedLivraison= adapter.getLivraisons().get(position);
                Intent intentLivraisonDetaillee = new Intent(getApplicationContext(), LivraisonDetailActivity.class);
                intentLivraisonDetaillee.putExtra("idlivraison", selectedLivraison.getIdlivraison());
                startActivity(intentLivraisonDetaillee);
            }
        });
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
    protected void onResume(){
        super.onResume();
        Intent intent= getIntent();
        int userid= intent.getIntExtra("userid", -1);
        ServerApi.getLivraisons(this, userid, this);
    }

    @Override
    public void receiveLivraison(ArrayList<Livraison> livraisons) {
        adapter.setLivraisons(livraisons);
        listView.setAdapter(adapter);
        listView.invalidate();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.idRetourCmdToMenu){
            Intent unIntent = new Intent(getApplicationContext(), MainActivity.class);
            this.startActivity(unIntent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent intentHome = new Intent(LivraisonActivity.this, MainActivity.class);
                startActivity(intentHome);
                break;
            case R.id.nav_commandes:
                Intent intentCommande = new Intent(LivraisonActivity.this, LivraisonActivity.class);
                startActivity(intentCommande);
                break;
            case R.id.nav_login:
                Intent intentLogin = new Intent(LivraisonActivity.this, Connexion.class);
                startActivity(intentLogin);
                break;
            case R.id.nav_profil:
                Intent intentProfil = new Intent(LivraisonActivity.this, Profil.class);
                startActivity(intentProfil);
                break;
            case R.id.nav_logout:
                Intent intentLogout = new Intent(LivraisonActivity.this, Logout.class);
                startActivity(intentLogout);
                break;
            case R.id.nav_mail:
                Intent intentMail = new Intent(LivraisonActivity.this, Email.class);
                startActivity(intentMail);
                break;
            case R.id.nav_chat:
                Intent intentChat = new Intent(LivraisonActivity.this, Chat.class);
                startActivity(intentChat);
                break;
            case R.id.nav_avis:
                Intent intentAvis = new Intent(LivraisonActivity.this, Avis.class);
                startActivity(intentAvis);
                break;
            case R.id.nav_politique:
                Intent intentPolitique = new Intent(LivraisonActivity.this, APropos.class);
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
