package com.EmirMuhamadZaidJmartAK.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RequestFactory {
    private static final String URL_FORMAT_ID = "http://10.0.2.2:6969/%s/%d" ;
    private static final String URL_FORMAT_PAGE = "http://10.0.2.2:6969/%s/page" ;

    public static StringRequest getById
            (
                    String parentURI,
                    int id,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_ID, parentURI, id);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }
    public static StringRequest getPage
            (
                    String parentURI,
                    int page,
                    int pageSize,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_PAGE, parentURI);
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("pageSize", String.valueOf(pageSize));
        return new StringRequest(Request.Method.GET, url, listener, errorListener) {
            public Map<String, String> getParams() { return params; }
        };
    }


}
