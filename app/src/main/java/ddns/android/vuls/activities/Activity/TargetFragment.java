package ddns.android.vuls.activities.Activity;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import ddns.android.vuls.R;

public class TargetFragment extends Fragment {

    public TargetFragment() {
        Log.e("DDNS: ", "TargetFragment's constructor");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_target,null);
        WebView webview = (WebView) view.findViewById(R.id.webview_fragment);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(getActivity().getIntent().getDataString());
        return view;
    }

}
