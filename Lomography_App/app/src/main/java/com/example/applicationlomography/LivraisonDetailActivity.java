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
import com.example.applicationlomography.model.LivraisonDetail;
import com.example.applicationlomography.model.LivraisonDetailAdapter;
import com.example.applicationlomography.services.IListenerAPI;
import com.example.applicationlomography.services.IListenerAPIDetail;
import com.example.applicationlomography.services.ServerApi;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class LivraisonDetailActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, IListenerAPIDetail {
    private ListView listView;
    private LivraisonDetailAdapter adapter;
    private TextView textName, textDescription;
    private Button btPrblm;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livraison_detail);
        listView = (ListView) findViewById(R.id.idLivraisonDetaillee);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent intentHome = new Intent(LivraisonDetailActivity.this, MainActivity.class);
                startActivity(intentHome);
                break;
            case R.id.nav_commandes:
                Intent intentCommande = new Intent(LivraisonDetailActivity.this, LivraisonActivity.class);
                startActivity(intentCommande);
                break;
            case R.id.nav_login:
                Intent intentLogin = new Intent(LivraisonDetailActivity.this, Connexion.class);
                startActivity(intentLogin);
                break;
            case R.id.nav_profil:
                Intent intentProfil = new Intent(LivraisonDetailActivity.this, Profil.class);
                startActivity(intentProfil);
                break;
            case R.id.nav_logout:
                Intent intentLogout = new Intent(LivraisonDetailActivity.this, Logout.class);
                startActivity(intentLogout);
                break;
            case R.id.nav_mail:
                Intent intentMail = new Intent(LivraisonDetailActivity.this, Email.class);
                startActivity(intentMail);
                break;
            case R.id.nav_chat:
                Intent intentChat = new Intent(LivraisonDetailActivity.this, Chat.class);
                startActivity(intentChat);
                break;
            case R.id.nav_avis:
                Intent intentAvis = new Intent(LivraisonDetailActivity.this, Avis.class);
                startActivity(intentAvis);
                break;
            case R.id.nav_politique:
                Intent intentPolitique = new Intent(LivraisonDetailActivity.this, APropos.class);
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

    }

    @Override
    public void receiveLivraison(ArrayList<LivraisonDetail> livraisons) {
        adapter = new LivraisonDetailAdapter(this);
        adapter.setLivraisons(livraisons);
        listView.setAdapter(adapter);
        listView.invalidate();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Intent intent= getIntent();
        int idlivraison= intent.getIntExtra("idlivraison", -1);
        ServerApi.getLivraisonDetails(this, idlivraison, this);
    }
}
