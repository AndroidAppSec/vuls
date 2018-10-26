package ddns.android.vuls;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ddns.android.vuls.activities.Activity.ActivityActivity;
import ddns.android.vuls.activities.Services.ServiceActivity;
import ddns.android.vuls.activities.net.NetActivity;
import ddns.android.vuls.activities.storage.StorageActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_storage).setOnClickListener(this);
        findViewById(R.id.btn_net).setOnClickListener(this);
        findViewById(R.id.btn_activity).setOnClickListener(this);
        findViewById(R.id.btn_service).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_storage:
                startActivity(new Intent(this, StorageActivity.class));
                break;
            case R.id.btn_net:
                startActivity(new Intent(this, NetActivity.class));
                break;
            case R.id.btn_activity:
                startActivity(new Intent(this, ActivityActivity.class));
                break;
            case R.id.btn_service:
                startActivity(new Intent(this, ServiceActivity.class));
                break;

            default:
                break;
        }


    }

}
