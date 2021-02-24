package com.appalber.ciudadesespaamapswiki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Activity_webview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        WebView webv = findViewById(R.id.wv_web);
        String ciudad = getIntent().getStringExtra("Ciudad");
        webv.setWebViewClient(new WebViewClient());
        String url = "https://en.wikipedia.org/wiki/"+ciudad;
        webv.loadUrl(url);
    }
}