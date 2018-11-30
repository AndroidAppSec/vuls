package ddns.android.vuls.activities.Webview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import ddns.android.vuls.R;

public class WvLoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private WebView webView;
    private String TOKEN = null;
    private String WEBSITE = "http://192.168.0.7/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wv_login);
        etUsername = findViewById(R.id.et_number);
        etPassword = findViewById(R.id.et_password);
        initWebView();
    }

    public void wvLogin(View view){

        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(WvLoginActivity.this, "账号和密码不能为空", Toast.LENGTH_SHORT).show();
        }else if ("12345".equals(password)){
            String sessionCookie = "session=" + username + "; HTTPOnly";
            TOKEN = sessionCookie;
            CookieManager.getInstance().setCookie(WEBSITE, sessionCookie);
            webView.loadUrl("javascript:javaCallJs(" + "'" + username + "'" + ")");
            setContentView(webView);
        }else {
            Toast.makeText(WvLoginActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
        }

    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        webView = new WebView(this);
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                String scheme = uri.getScheme();
                String authority = uri.getAuthority();
                String activity = uri.getQueryParameter("activity");
                if ("ddns".equals(scheme) &&
                        "startActivity".equals(authority) &&
                        !TextUtils.isEmpty(activity)){
                    Class<?> activityClass = null;
                    try {
                        activityClass = Class.forName(activity);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (activityClass != null){
                        startActivity(new Intent(WvLoginActivity.this, activityClass));
                    }
                    return true;
                }

                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });

        //设置支持 js 调用 java
        webView.addJavascriptInterface(new JSInterface(),"Android");

        //加载网络资源
        webView.loadUrl(WEBSITE + "welcome");
    }

    private class JSInterface {
        @JavascriptInterface
        public String getSession(){
            return TOKEN;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
}
