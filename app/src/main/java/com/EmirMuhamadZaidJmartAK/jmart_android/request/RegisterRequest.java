package com.EmirMuhamadZaidJmartAK.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
/**
 * RegisterRequest
 *
 * create a request based on URL and the paramater provided
 */
public class RegisterRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:6969/account/register" ;
    private final Map<String,String> params ;

    /**
     * RegisterRequest constructor to assemble the request
     * @param name
     * @param email
     * @param password
     * @param listener
     * @param errorListener
     */
    public RegisterRequest(String name, String email, String password,
                           Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
    }
    /**
     * return the paramater used to create the request
     * @return
     */
    public Map<String,String> getParams(){
        return params;
    }
}
