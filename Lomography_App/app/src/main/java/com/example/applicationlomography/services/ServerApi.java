package com.example.applicationlomography.services;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.applicationlomography.model.Livraison;
import com.example.applicationlomography.model.LivraisonDetail;
import com.google.gson.Gson;

import com.example.applicationlomography.MainActivity;
import com.example.applicationlomography.R;
import com.example.applicationlomography.model.Livraison;
import com.example.applicationlomography.services.IListenerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ServerApi {
    private static String TAG= "API REQUEST";
    private static String URL_API= "http://172.16.8.59/Promotion_241/Projets/Lomography_PPE/server/";

    public static void getLivraisons(Context context, int userId, final IListenerAPI listenerAPI) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_API + "livraison",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Gson gson = new Gson();
                        Livraison[] tempArray = gson.fromJson(response, Livraison[].class);
                        ArrayList<Livraison> livraisons = new ArrayList<Livraison>(Arrays.asList(tempArray));
                        for(int i=0; i < livraisons.size(); i++){
                            if(livraisons.get(i).getIduser()!=userId){
                                livraisons.remove(i);
                            }
                        }
                        listenerAPI.receiveLivraison(livraisons);
                        Log.d(TAG, "Ok je suis la");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public static void getLivraisonDetails(Context context, int livraisonId, final IListenerAPIDetail listener) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_API + "livraisondetails/"+livraisonId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Gson gson = new Gson();
                        LivraisonDetail[] tempArray = gson.fromJson(response, LivraisonDetail[].class);
                        ArrayList<LivraisonDetail> livraisons = new ArrayList<LivraisonDetail>(Arrays.asList(tempArray));
                        listener.receiveLivraison(livraisons);
                        Log.d(TAG, "Ok je suis la");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public static void connexion(final Context context, final String email, String mdp, IListenerAPIConnexion listener){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_API + "connexion", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status= jsonObject.getInt("status");
                    int userid= jsonObject.getInt("userid");
                    listener.isConnect(status==200, userid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,error.getMessage() );
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);
                params.put("mdp", mdp);
                return params;
            }
        };
        queue.add(postRequest);
    }

    public static void loadImage(Context context, String url, final ImageView imageView){
        RequestQueue queue = Volley.newRequestQueue(context);
        ImageRequest request = new ImageRequest(url,
            new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }
            }, 0, 0, null,
            new Response.ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    imageView.setImageResource(R.drawable.ic_launcher_foreground);
                }
            });
        queue.add(request);
    }

    /*public static void createPersonnage(final Context context, final Personnage personnage){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_API + "personnages", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Enregistré", Toast.LENGTH_LONG);
                context.startActivity(new Intent(context, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,error.getMessage() );
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", personnage.getName());
                params.put("image", personnage.getImage());
                params.put("description", personnage.getDescription());
                return params;
            }
        };
        queue.add(postRequest);
    }
     */

    /*public static void updatePersonnage(final Context context, final Personnage personnage){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest putRequest = new StringRequest(Request.Method.PUT, URL_API + "personnages/"+personnage.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Enregistré", Toast.LENGTH_LONG);
                context.startActivity(new Intent(context, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,error.getMessage() );
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", personnage.getName());
                params.put("image", personnage.getImage());
                params.put("description", personnage.getDescription());
                return params;
            }
        };
        queue.add(putRequest);
    }*/

    /*public  static void deletePersonnage(final Context context, int idPersonnage){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, URL_API + "personnages/"+idPersonnage, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "supprimé!", Toast.LENGTH_LONG);
                context.startActivity(new Intent(context, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,error.getMessage() );
            }
        });
        queue.add(deleteRequest);
    }*/
}
