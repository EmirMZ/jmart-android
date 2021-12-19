package com.EmirMuhamadZaidJmartAK.jmart_android.request;

import com.EmirMuhamadZaidJmartAK.jmart_android.LoginActivity;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * balanceRequest
 *
 * create a request based on URL and the paramater provided
 */
public class balanceRequest extends StringRequest {
    private static final String URL =  "http://10.0.2.2:6969/account/"  + LoginActivity.getLoggedAccount().id + "/checkbalance";
    private final Map<String , String> params;

    /**
     * balanceRequest Constructor
     * @param id
     * @param listener
     * @param errorListener
     */
    public balanceRequest
            (
                    int id,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        super(Method.POST, URL, listener, errorListener);


        params = new HashMap<>();
        params.put("id", Integer.toString(id));
    }

    /**
     * get parameters
     * @return
     */
    public Map<String , String> getParams() {
        return params;
    }
}