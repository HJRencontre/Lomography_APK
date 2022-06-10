package com.example.applicationlomography.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applicationlomography.R;
import com.example.applicationlomography.model.Livraison;
import com.example.applicationlomography.services.ServerApi;

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
        return livraisons.size();
    }

    @Override
    public Object getItem(int position) {
        return livraisons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return livraisons.get(position).getIdlivraison();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_livraison, null);
        TextView textView = (TextView) rowView.findViewById(R.id.textViewItemLivraisonName);
        textView.setText("Commande N°"+livraisons.get(position).getIdlivraison());
        return rowView;
    }
}
