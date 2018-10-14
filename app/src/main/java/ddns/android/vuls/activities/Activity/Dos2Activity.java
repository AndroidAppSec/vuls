package ddns.android.vuls.activities.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ddns.android.vuls.R;

public class Dos2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos2);

        Intent intent = getIntent();
        boolean[] items = intent.getBooleanArrayExtra("data");
        for (boolean item : items){
            System.out.println(item);
        }
    }
}
