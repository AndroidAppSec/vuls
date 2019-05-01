package ddns.android.vuls.activities.net;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import ddns.android.vuls.R;

public class HttpsURLConnectionActivity extends AppCompatActivity {

    private String baidu = "https://www.baidu.com/aaa";
    private String self = "https://192.168.8.233";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_https_urlconnection);
    }

    public void doHttpsURLConnection(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(baidu);
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(10000);
                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    if (200 == responseCode) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HttpsURLConnectionActivity.this, "请求成功", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(HttpsURLConnectionActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }

    public void doCheckNothing(View view) {

        final TrustManager[] trustManagers = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType)
                            throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType)
                            throws CertificateException {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };

        final HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(null, trustManagers, new SecureRandom());

                    URL url = new URL(baidu);
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    connection.setSSLSocketFactory(sslContext.getSocketFactory());
                    connection.setHostnameVerifier(hostnameVerifier);
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(10000);
                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    if (200 == responseCode) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HttpsURLConnectionActivity.this, "请求成功", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(HttpsURLConnectionActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }

    public void doCheckHost(View view) {

        final TrustManager[] trustManagers = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };

        final HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                Log.e("NetAcitivty", hostname);
                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                Boolean result = hv.verify("www.google.com", session);
                return result;
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(null, trustManagers, new SecureRandom());

                    URL url = new URL(baidu);
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    connection.setSSLSocketFactory(sslContext.getSocketFactory());
                    connection.setHostnameVerifier(hostnameVerifier);
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(10000);
                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    if (200 == responseCode) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HttpsURLConnectionActivity.this, "请求成功", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(HttpsURLConnectionActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }

}
