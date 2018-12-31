package ddns.android.vuls.activities.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ddns.android.vuls.R;

public class ActivityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
    }

    public void bypassLogin(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void openWebsite(View view){
        startActivity(new Intent(this, WebActivity.class));
    }

    public void openWebsiteWithBrowser(View view){

        Uri uri = Uri.parse("https://www.baidu.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void startFragementActivity(View view){

        startActivity(new Intent(this, FragmentActivity.class));
    }





}
