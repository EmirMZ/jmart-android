package com.EmirMuhamadZaidJmartAK.jmart_android.request;

import androidx.annotation.Nullable;

import com.EmirMuhamadZaidJmartAK.jmart_android.LoginActivity;
import com.EmirMuhamadZaidJmartAK.jmart_android.ProductFragment;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Payment Request class
 *
 * create a payment to purchase request based on URL and the paramater provided
 */
public class PaymentRequest extends StringRequest {
    public static final String URL = "http://10.0.2.2:6969/payment/create";
    public final Map<String,String> params;

    /**
     * PaymentRequest constructor to assemble the request
     * @param productCount
     * @param shipmentAddress
     * @param listener
     * @param errorListener
     */
    public PaymentRequest(String productCount, String shipmentAddress, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("buyerId",String.valueOf(LoginActivity.getLoggedAccount().id));
        params.put("productId",String.valueOf(ProductFragment.productClicked.id));
        params.put("productCount",productCount);
        params.put("shipmentAddress",shipmentAddress);
        params.put("shipmentPlan",String.valueOf(ProductFragment.productClicked.shipmentPlans));
    }

    /**
     * return the paramater used to create the request
     * @return
     */
    public Map<String,String> getParams(){
        return params;
    }
}