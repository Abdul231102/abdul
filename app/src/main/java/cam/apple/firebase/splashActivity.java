package cam.apple.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.firebase.R;

public class splashActivity extends AppCompatActivity {
private static int SPLASH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            SharedPreferences sharedPreferences =getSharedPreferences(MainActivity.PREFS_NAME,0);
            boolean hasLoggedIn=sharedPreferences.getBoolean("hasLoggedIn",false);
            if(hasLoggedIn){
                Intent intent =new Intent(splashActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent =new Intent(splashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    },SPLASH_TIME_OUT);

    }
}