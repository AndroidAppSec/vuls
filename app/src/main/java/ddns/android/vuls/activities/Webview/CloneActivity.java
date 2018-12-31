package ddns.android.vuls.activities.Webview;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ddns.android.vuls.R;

public class CloneActivity extends AppCompatActivity {

    private WebView webView;

    public CloneActivity() {
        Log.e("FragmentVuls", "CloneActivity constructor");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWebView();

        if (ActivityCompat.checkSelfPermission(CloneActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        Uri uri = getIntent().getData();
        String url = uri.getQueryParameter("url");
        Log.e("clone", url);
        webView.loadUrl(url);

        setContentView(webView);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initWebView(){
        webView = new WebView(this);
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
}
