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

public class LivraisonDetailAdapter extends BaseAdapter {

    private ArrayList<LivraisonDetail> livraisons;
    private Context context;

    public ArrayList<LivraisonDetail> getLivraisons(){
        return livraisons;
    }

    public void setLivraisons(ArrayList<LivraisonDetail> livraisons) {
        this.livraisons = livraisons;
    }

    public LivraisonDetailAdapter(Context context){
        this.context = context;
        this.livraisons = new ArrayList<LivraisonDetail>();
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
        return livraisons.get(position).getIdproduit();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.produit_detail, null);
        TextView textViewNomProduit = (TextView) rowView.findViewById(R.id.textViewNomProduit);
        TextView textViewQteProduit = (TextView) rowView.findViewById(R.id.textViewQteProduit);
        TextView textViewPrixProduit = (TextView) rowView.findViewById(R.id.textViewPrixProduit);
        ImageView imageViewProduit= (ImageView) rowView.findViewById(R.id.imageViewProduit);
        ServerApi.loadImage(context, "http://172.16.8.59/Promotion_241/Projets/Lomography_PPE/"+livraisons.get(position).getImg(), imageViewProduit);
        textViewNomProduit.setText("Nom :"+livraisons.get(position).getNom());
        textViewQteProduit.setText("Quantité :"+livraisons.get(position).getQte());
        textViewPrixProduit.setText("Prix :"+livraisons.get(position).getPrix());
        return rowView;
    }
}
