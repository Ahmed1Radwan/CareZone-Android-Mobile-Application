package com.ahmedhamdy.healthcare.Web;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ahmedhamdy.healthcare.Medical.MedicinesController;
import com.ahmedhamdy.healthcare.R;

public class WebsiteView extends AppCompatActivity {

    String message = "Loading...";
    String virusName = "";
    private WebView webView = null;
    private MedicinesController medicinesController = MedicinesController.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_view);
        virusName = getIntent().getStringExtra("virusName");
        webView = findViewById(R.id.webView);
        setUpWebView();
    }
    private void setUpWebView(){
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webSettings.setGeolocationEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // enable media playback for version greater than jelly bean
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            webSettings.setMediaPlaybackRequiresUserGesture(true);
        }

        webSettings.setLoadsImagesAutomatically(true);

        webSettings.setUseWideViewPort(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);

        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        try {
            if(MedicinesController.index < medicinesController.medicinesNames.size())
                webView.loadUrl(getResources().getString(medicinesController.getWebUrl()));
            else
                webView.loadUrl(medicinesController.clouldWebsites.get(virusName).get(MedicinesController.index1));
            System.out.println("ssssss-----------------------");
        }catch (Exception e){
            webView.setWebChromeClient(new WebChromeClient(){
                @Override
                public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                    callback.invoke(origin,true,false);
                }
            });
            message = "Scanning Location...";
            webView.loadUrl("https://www.google.com/");
        }


    }

    class MyWebViewClient extends WebViewClient{
        ProgressDialog progressDialog;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressDialog = new ProgressDialog(WebsiteView.this);
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage(message);
            progressDialog.show();
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressDialog.dismiss();
            super.onPageFinished(view, url);
        }
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }
}