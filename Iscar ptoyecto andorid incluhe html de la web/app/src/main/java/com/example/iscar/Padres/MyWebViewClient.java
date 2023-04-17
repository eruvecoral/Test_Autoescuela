package com.example.iscar.Padres;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {

    private static final String TAG = "Error";

    public MyWebViewClient() {
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url == null || url.startsWith("http://") || url.startsWith("https://")) {
            return false;
        }

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            view.getContext().startActivity(intent);
            return true;
        } catch (Exception e) {
            Log.i(TAG, "shouldOverrideUrlLoading Exception:" + e);
            return true;
        }
    }
}
