package ddns.android.vuls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TokenReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean success = intent.getBooleanExtra("success", false);
        if (success){
            String token = intent.getStringExtra("token");
            Log.e("TokenReceiver", "token: " + token);
        }else {
            Log.e("TokenReceiver", "no token");
        }

    }
}
