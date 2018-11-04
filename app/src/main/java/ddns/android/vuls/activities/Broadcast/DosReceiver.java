package ddns.android.vuls.activities.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DosReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String dos = intent.getStringExtra("dos");
        Log.e("DosReceiver", dos);
    }
}
