package ddns.android.vuls.activities.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ddns.android.vuls.R;

public class Dos1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos);

        Intent intent = getIntent();

        String data = intent.getStringExtra("data");
        System.out.println(data);

    }
}
