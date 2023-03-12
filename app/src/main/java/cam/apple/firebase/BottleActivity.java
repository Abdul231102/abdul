package cam.apple.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.R;

import java.util.Random;

public class BottleActivity extends AppCompatActivity {
TextView back;
    private ImageView bot;
    private Random rand = new Random();
    private int lstDr;
    private boolean spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottle);
        back=findViewById(R.id.backbtn);
        bot = findViewById(R.id.bottle);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(BottleActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    public void spinBottle(View v) {
        // check if the bottled has stopped spinning
        if (!spn) {

            // generate a random number from 1-1800
            int num = rand.nextInt(2800);

            // set the pivot to the centre of the image
            float pX = bot.getWidth() / 2;
            float pY = bot.getHeight() / 2;

            // pass parameters in RoatateAnimation function
            Animation rot = new RotateAnimation(lstDr, num, pX, pY);

            // set rotate duration 2500 millisecs
            rot.setDuration(5000);

            // rotation will persist after finishing
            rot.setFillAfter(true);
            rot.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spn = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spn = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });

            // change the last direction
            lstDr = num;

            // start the animation
            bot.startAnimation(rot);
        }
    }
}