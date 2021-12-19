package com.EmirMuhamadZaidJmartAK.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * RequestFactory class
 *
 * create a page request based on URL and the paramater provided
 */
public class RequestFactory
{
    private static final String URL_FORMAT_ID = "http://10.0.2.2:6969/%s/%d";
    private static final String URL_FORMAT_PAGE = "http://10.0.2.2:6969/%s/page?page=%s&pageSize=%s";
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

    /**
     * the request to get the page
     * @param parentURI
     * @param page
     * @param pageSize
     * @param listener
     * @param errorListener
     * @return
     */
    public static StringRequest getPage
            (
                    String parentURI,
                    int page,
                    int pageSize,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_PAGE, parentURI, page, pageSize);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }
}

