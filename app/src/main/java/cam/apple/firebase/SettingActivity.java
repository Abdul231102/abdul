package cam.apple.firebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.firebase.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingActivity extends AppCompatActivity {
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

    ConstraintLayout constraintLayout;


    TextView name;
    TextView email;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Button outbtn =findViewById(R.id.candy);
        name =findViewById(R.id.name);
        email =findViewById(R.id.email);


        final String getplayerOneName =getIntent().getStringExtra("inputusername");
        final String getplayerTwoName =getIntent().getStringExtra("inputEmail");

        name.setText(getplayerOneName);
        email.setText(getplayerTwoName);

        BottomNavigationView bottomNavigationView =findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.gallery);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){


                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.gallery:
                        return true;


                }
                return false;
            }
        });

        googleSignInOptions =new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);


        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
          if(account!= null){
              String  personName = account.getDisplayName();
              String personEmail=account.getEmail();
              name.setText(personName);
              email.setText(personEmail);
          }

        final SharedPreferences share =getSharedPreferences("Data", Context.MODE_PRIVATE);
        outbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editt =share.edit();
                editt.clear();
                editt.commit();

                signout();


            }
        });
    }




    private void signout() {
        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();

               startActivity(new Intent(getApplicationContext(),MainActivity.class));
                Toast.makeText(SettingActivity.this,"signout successful",Toast.LENGTH_SHORT).show();
            }
        });
    }
}