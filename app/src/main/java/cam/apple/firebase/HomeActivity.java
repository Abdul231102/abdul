package cam.apple.firebase;



import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.firebase.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;

    Button tictactoebtn;
    Button snake;
    Button outbtn;
    Button Bottle;
    Button Dice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        constraintLayout =findViewById(R.id.homeAct);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tictactoebtn =findViewById(R.id.tictactoebtn);
        snake =findViewById(R.id.snakebtn);
        outbtn =findViewById(R.id.candy);
        Bottle =findViewById(R.id.Bottlebtn);
        Dice =findViewById(R.id.Dice_btn);

        BottomNavigationView bottomNavigationView =findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:
                        return true;
                    case R.id.gallery:
                        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });

        outbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this,CandyActivity.class);
                startActivity(intent);
            }
        });

        tictactoebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToNextActivity1();
            }
        });
        snake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent =new Intent(HomeActivity.this,BulletActivity.class);
            startActivity(intent);
            }
        });


        Bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,BottleActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this,DiceActivity2.class);
                startActivity(intent);
            }
        });

    }
    public void sendUserToNextActivity1(){
        Intent intent=new Intent(HomeActivity.this,TicTacToeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



}