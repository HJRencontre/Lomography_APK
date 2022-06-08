package com.example.applicationlomography;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

        ArrayList<Livraison> livraisons = new ArrayList<Livraison>();
        adapter = new LivraisonAdapter(this);
        listView = (ListView) findViewById(R.id.idLivraison);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), LivraisonActivity.class);
                intent.putExtra("livraison", adapter.getLivraisons().get(position));
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        ServerApi.getLivraisons(this, this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    @Override
    public void receiveLivraison(ArrayList<Livraison> livraisons) {

    }

    @Override
    public void onClick(View view) {

    }
}
