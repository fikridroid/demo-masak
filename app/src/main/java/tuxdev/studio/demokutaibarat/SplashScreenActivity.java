package tuxdev.studio.demokutaibarat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ukietux on 09/01/16.
 */
public class SplashScreenActivity extends AppCompatActivity {
    //waktu splash screen
    private static int splashInterval = 3000;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

                                      @Override
                                      public void run() {
                                          Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                                          startActivity(i);
                                          SplashScreenActivity.this.finish();
                                      }
                                  },
                splashInterval);
    }
}