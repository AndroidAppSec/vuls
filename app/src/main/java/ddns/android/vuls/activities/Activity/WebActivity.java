package ddns.android.vuls.activities.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import ddns.android.vuls.R;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
    }

    public void visitWeb(View view){
        EditText editText = findViewById(R.id.et_weburl);
        String url = editText.getText().toString();
        Intent intent = new Intent(this, WebviewActivity.class);
        if (null != url){
            intent.putExtra("url", url);
            startActivity(intent);
        }
    }
}
