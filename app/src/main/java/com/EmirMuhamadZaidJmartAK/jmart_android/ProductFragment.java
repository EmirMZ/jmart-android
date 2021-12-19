package com.EmirMuhamadZaidJmartAK.jmart_android;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.EmirMuhamadZaidJmartAK.jmart_android.model.Product;
import com.EmirMuhamadZaidJmartAK.jmart_android.request.RequestFactory;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * product fragment class with viewpage to manage the list
 */
public class ProductFragment extends Fragment {
    private static final Gson gson = new Gson();
    public static ArrayList<Product> productsList = new ArrayList<>();
    final int pageSize = 20;
    static int page = 0;
    public static Product productClicked=null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View productView = inflater.inflate(R.layout.fragment_product, container, false);
        EditText pageNumber = (EditText) productView.findViewById(R.id.InputPageProduct);
        Button next = (Button) productView.findViewById(R.id.ButtonNext);
        Button prev = (Button) productView.findViewById(R.id.ButtonPrev);
        Button go = (Button) productView.findViewById(R.id.ButtonGo);

        pageNumber.setText(String.valueOf(page + 1), TextView.BufferType.EDITABLE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Next Page", Toast.LENGTH_SHORT);
                page += 1;
                getActivity().finish();
                getActivity().startActivity(getActivity().getIntent());
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Prev Page", Toast.LENGTH_SHORT);
                page -= 1;
                if(page < 0){
                    page = 0;
                }
                getActivity().startActivity(getActivity().getIntent());
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Go!", Toast.LENGTH_SHORT);
                page = Integer.parseInt(pageNumber.getText().toString()) - 1;

                getActivity().startActivity(getActivity().getIntent());
            }
        });

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray object = new JSONArray(response);
                    if (object != null) {
                        productsList = gson.fromJson(object.toString(), new TypeToken<ArrayList<Product>>() {
                        }.getType());
                        System.out.println(productsList);
                        ArrayAdapter<Product> listViewAdapter = new ArrayAdapter<Product>(
                                getActivity(),
                                android.R.layout.simple_list_item_1,
                                productsList
                        );
                            ListView lv = (ListView) productView.findViewById(R.id.ProductListView);

                        lv.setAdapter(listViewAdapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                productClicked = (Product) lv.getItemAtPosition(i);
                                Toast.makeText(getActivity(),"", Toast.LENGTH_SHORT);
                                Intent intent = new Intent(getActivity(), ProductViewActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(RequestFactory.getPage("product", page, pageSize, listener, null));

        return productView;
    }
}