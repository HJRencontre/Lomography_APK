/*<<<<<<< HEAD
package com.example.applicationlomography.model;
=======
 */
/*
package net.merryservices.androidtestapi.model;
>>>>>>> d36ad920886dd47885bdfbfcc1c6d958e99c79c5

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

<<<<<<< HEAD
import com.example.applicationlomography.R;
import com.example.applicationlomography.services.ServerApi;
=======
import com.example.applicationlomography.model.Personnage;
import com.example.applicationlomography.services.ServerApi;

import net.merryservices.androidtestapi.R;
import net.merryservices.androidtestapi.services.ServerApi;
>>>>>>> d36ad920886dd47885bdfbfcc1c6d958e99c79c5

import java.util.ArrayList;

public class PersonnageAdapter extends BaseAdapter {

    private ArrayList<Personnage> personnages;
    private Context context;

    public ArrayList<Personnage> getPersonnages() {
        return personnages;
    }

    public void setPersonnages(ArrayList<Personnage> personnages) {
        this.personnages = personnages;
    }

    public PersonnageAdapter(Context context){
        this.context = context;
        this.personnages = new ArrayList<Personnage>();
    }

    @Override
    public int getCount() {
        return personnages.size();
    }

    @Override
    public Object getItem(int position) {
        return personnages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return personnages.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView= inflater.inflate(R.layout.item_personnage, null);
        TextView textView= (TextView) rowView.findViewById(R.id.textView);
        textView.setText(personnages.get(position).getName());
        ImageView imageView= (ImageView) rowView.findViewById(R.id.imageView);
        ServerApi.loadImage(context, personnages.get(position).getImage(), imageView);
        return rowView;
    }
}
*/