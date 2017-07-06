package folio.jaagdeveloper.usman.a75criteria;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        AdView adView = (AdView) findViewById(R.id.ads);
        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.setAdListener(new ToastListener(this));
        adView.loadAd(adRequest);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                try {
                    ProgressBar progressBar = (ProgressBar) findViewById(R.id.loading_indicator);
                    progressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);
                    sleep(3000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        thread.start();
    }
}
