package com.example.applicationlomography;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.applicationlomography.model.Livraison;
import com.example.applicationlomography.model.LivraisonAdapter;
import com.example.applicationlomography.model.Personnage;
import com.example.applicationlomography.services.IListenerAPI;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class LivraisonActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, IListenerAPI {

    private TextView textName, textDescrption;
    private Button btRetourCmdToMenu, btCmdToSAV;
    private LivraisonAdapter Adapter;
    private ListView lvListeCmd;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandes);

        textName =
    }

    @Override
    protected void onResume(){
        super.onResume();
        Intent intent = getIntent();
        final Livraison livraison = (Livraison) intent.getSerializableExtra("livraison");
        textName.setText(livraison.getDateExpedition());
        textDescrption.setText(livraison.getAdresse());

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void receivePersonnages(ArrayList<Personnage> personnages) {

    }

    @Override
    public void receiveLivraison(ArrayList<Livraison> livraisons) {

    }
}
