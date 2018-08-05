package ddns.android.vuls.activities.storage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ddns.android.vuls.R;

public class StorageActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        findViewById(R.id.btn_sp).setOnClickListener(this);
        findViewById(R.id.btn_internal_file).setOnClickListener(this);
        findViewById(R.id.btn_external_file).setOnClickListener(this);
        findViewById(R.id.btn_database).setOnClickListener(this);
        findViewById(R.id.btn_logcat).setOnClickListener(this);
        findViewById(R.id.btn_screen_shot).setOnClickListener(this);
        findViewById(R.id.btn_clipboard).setOnClickListener(this);
        findViewById(R.id.btn_keyboard_cache).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sp:
                startActivity(new Intent(this, SpActivity.class));
                break;
            case R.id.btn_internal_file:
                startActivity(new Intent(this, InternalFileActivity.class));
                break;
            case R.id.btn_external_file:
                startActivity(new Intent(this, ExternalFileActivity.class));
                break;
            case R.id.btn_database:
                startActivity(new Intent(this, SQLiteActivity.class));
                break;
            case R.id.btn_logcat:
                startActivity(new Intent(this, LogcatActivity.class));
                break;
            case R.id.btn_screen_shot:
                startActivity(new Intent(this, ScreenShotActivity.class));
                break;
            case R.id.btn_clipboard:
                startActivity(new Intent(this, ClipboardActivity.class));
                break;
            case R.id.btn_keyboard_cache:
                startActivity(new Intent(this, KeyboardCacheActivity.class));
                break;
            default:
                break;
        }
    }

    public void onClickGetUID(View v){
        startActivity(new Intent(this, UidActivity.class));
    }
}
