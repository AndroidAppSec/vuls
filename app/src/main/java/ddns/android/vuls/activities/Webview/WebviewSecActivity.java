package ddns.android.vuls.activities.Webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ddns.android.vuls.R;

public class WebviewSecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_sec);
    }

    public void jsSec(View view){
        startActivity(new Intent(this, WvLoginActivity.class));

    }

    public void netSec(View view){
        startActivity(new Intent(this, WvNetActivity.class));
    }
}
