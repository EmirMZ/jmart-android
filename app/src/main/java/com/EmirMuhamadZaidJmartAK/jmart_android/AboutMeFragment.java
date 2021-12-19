package com.EmirMuhamadZaidJmartAK.jmart_android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.EmirMuhamadZaidJmartAK.jmart_android.request.TopUpRequest;
import com.EmirMuhamadZaidJmartAK.jmart_android.request.balanceRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;


public class AboutMeFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View accountView = inflater.inflate(R.layout.fragment_about_me, container, false);
        super.onCreate(savedInstanceState);

        TextView name = (TextView) accountView.findViewById(R.id.nameOutputAccount);
        name.setText("" + LoginActivity.getLoggedAccount().name);

        TextView email = (TextView) accountView.findViewById(R.id.emailOutputAccount);
        email.setText("" + LoginActivity.getLoggedAccount().email);

        TextView balance = (TextView) accountView.findViewById(R.id.balanceOutputAccount);
        balance.setText("" + LoginActivity.getLoggedAccount().balance);

        EditText topUpInput =  accountView.findViewById(R.id.topupInputAccount);
        Button buttonTopUp = accountView.findViewById(R.id.topupBottonAccount);

        buttonTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener1 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if( Boolean.valueOf(response)){
                            Toast.makeText(getActivity(), "Topup berhasil!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(), "Topup error!", Toast.LENGTH_SHORT).show();
                        }
                        //LoginActivity.getLoggedAccount().balance += Double.parseDouble(topUpInput.getText().toString());
                    }
                };
                Response.Listener<String> listener2 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if( Boolean.valueOf(Double.valueOf(response) > 0.0)){
                            Toast.makeText(getActivity(), "Topup berhasil!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(), "Topup error!", Toast.LENGTH_SHORT).show();
                        }
                        LoginActivity.getLoggedAccount().balance = Double.valueOf(response);
                        balance.setText("" + String.valueOf(response));
                    }
                };
                TopUpRequest topUpRequest = new TopUpRequest(LoginActivity.getLoggedAccount().id, Double.parseDouble(topUpInput.getText().toString()), listener1, null);
                balanceRequest balanceRequest = new balanceRequest(LoginActivity.getLoggedAccount().id, listener2, null);
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(topUpRequest);
                requestQueue.add(balanceRequest);




            }


        });
        return accountView;
    }
}