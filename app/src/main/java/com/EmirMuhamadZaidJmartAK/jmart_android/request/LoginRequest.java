package com.EmirMuhamadZaidJmartAK.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:5554/account/login" ;
    private final Map<String,String> params;

    public LoginRequest(String email, String password, Response.Listener<String> listener,
                        Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
    }
    public Map<String,String> getParams(){
        return params;
    }
}
