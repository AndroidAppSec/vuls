package ddns.android.vuls.activities.Activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;


public class FragmentActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        Log.e("FragmentVuls", "fragmentName: " + fragmentName);
        return true;
    }
}
