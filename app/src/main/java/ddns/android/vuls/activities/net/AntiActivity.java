package ddns.android.vuls.activities.net;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ddns.android.vuls.Common.AntiTools;
import ddns.android.vuls.R;

public class AntiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti);
    }

    public void checkSignature(View view){
        boolean notRepacked = AntiTools.checkSignature(AntiActivity.this,
                "c2429d87b38bf8ba347cfda67657576c80b18644");
        if (notRepacked){
            Toast.makeText(AntiActivity.this, "未重打包", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(AntiActivity.this, "重打包了！", Toast.LENGTH_LONG).show();
        }
    }

    public void checkProxy(View view){
        boolean proxy = AntiTools.checkProxy();
        if (proxy){
            Toast.makeText(AntiActivity.this, "有代理或VPN", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(AntiActivity.this, "网络环境正常", Toast.LENGTH_LONG).show();
        }
    }

    public void checkHook(View view){
        boolean hooked = AntiTools.checkHook();
        if (hooked){
            Toast.makeText(AntiActivity.this, "存在 Hook 框架", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(AntiActivity.this, "环境正常", Toast.LENGTH_LONG).show();
        }
    }
}
