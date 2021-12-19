package com.EmirMuhamadZaidJmartAK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.EmirMuhamadZaidJmartAK.jmart_android.request.PaymentRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    private static Payment paid = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentproduct);

        TextView name = findViewById(R.id.NamePayment);
        TextView shipmentPlans = findViewById(R.id.ShipmentPayment);
        TextView price = findViewById(R.id.PricePayment);
        EditText productCount = findViewById(R.id.payment_product_count);
        EditText address = findViewById(R.id.address_payment);
        Button order = findViewById(R.id.OrderButton);
        name.setText(ProductFragment.productClicked.name);

        /**
         * Switch case shipmentPlans
         * @description
         * Untuk mengubah shipmentPlans (byte) menuju shipmentPlans (String)
         */

        switch (ProductFragment.productClicked.shipmentPlans) {
            case 0:
                shipmentPlans.setText("INSTANT");
                break;
            case 1:
                shipmentPlans.setText("SAME DAY");
                break;
            case 2:
                shipmentPlans.setText("NEXT DAY");
                break;
            case 3:
                shipmentPlans.setText("REGULER");
                break;
            default:
                shipmentPlans.setText("KARGO");
                break;
        }



        /**
         * The order.setOnClickListener
         * @description
         * Disini button order di berfungsi untuk membuat payment request
         */

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if(object != null){
                                paid = gson.fromJson(object.toString(),Payment.class);
                                System.out.println(paid);
                                Intent intent = new Intent(PaymentActivity.this,MainActivity.class);
                                LoginActivity.getLoggedAccount().balance -= (ProductFragment.productClicked.price - (ProductFragment.productClicked.price*(ProductFragment.productClicked.discount/100)))*Double.parseDouble(productCount.getText().toString());
                                startActivity(intent);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
                PaymentRequest paymentRequest = new PaymentRequest(productCount.getText().toString(),address.getText().toString(),listener,null);
                RequestQueue requestQueue = Volley.newRequestQueue(PaymentActivity.this);
                requestQueue.add(paymentRequest);
            }
        });
    }

}


