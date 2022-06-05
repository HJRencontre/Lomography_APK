package com.example.applicationlomography.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applicationlomography.model.Livraison;
import com.example.applicationlomography.services.ServerApi;

//import net.merryservices.androidtestapi.R;
//import net.merryservices.androidtestapi.services.ServerApi;

import java.util.ArrayList;

public class LivraisonAdapter extends BaseAdapter {

    private ArrayList<Livraison> livraisons;
    private Context context;

    public ArrayList<Livraison> getLivraisons(){
        return livraisons;
    }

    public void setLivraisons(ArrayList<Livraison> livraisons) {
        this.livraisons = livraisons;
    }

    public LivraisonAdapter(Context context){
        this.context = context;
        this.livraisons = new ArrayList<Livraison>();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
