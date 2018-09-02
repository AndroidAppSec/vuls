package ddns.android.vuls.activities.net;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ddns.android.vuls.R;

public class NetActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        findViewById(R.id.btn_HttpsURLConnection).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_HttpsURLConnection:
                startActivity(new Intent(this, HttpsURLConnectionActivity.class));
                break;
            default:
                break;

        }
    }
}
