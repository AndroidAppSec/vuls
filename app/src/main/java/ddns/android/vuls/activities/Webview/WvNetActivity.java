package ddns.android.vuls.activities.Webview;

import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import ddns.android.vuls.R;

public class WvNetActivity extends AppCompatActivity {

    private WebView webView;
    private EditText etUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wv_net);
        etUrl = findViewById(R.id.et_wv_url);
        initWebview();
    }

    private void initWebview(){
        webView = new WebView(this);
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

        });

        webView.setWebChromeClient(new WebChromeClient());
    }

    public void view(View view){

        String url = etUrl.getText().toString().trim();
        if (TextUtils.isEmpty((url))){
            Toast.makeText(WvNetActivity.this, "URL 不能为空！", Toast.LENGTH_LONG).show();
        }else {
            webView.loadUrl(url);
            setContentView(webView);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
}
