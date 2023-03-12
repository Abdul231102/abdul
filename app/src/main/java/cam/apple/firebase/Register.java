package cam.apple.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.NotNull;

public class Register extends AppCompatActivity {

    TextView ihaveanaccount;
    EditText inputusername,inputEmail,inputPassword,inputConfirmPassword;
    Button Registerbtn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressdialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ihaveanaccount =findViewById(R.id.ihaveanaccount);

        inputusername =findViewById(R.id.inputusername);
        inputEmail=findViewById(R.id.inputEmail);
        inputPassword =findViewById(R.id.inputPassword);
        inputConfirmPassword =findViewById(R.id.inputConfirmPassword);
        Registerbtn =findViewById(R.id.Registerbtn);
        progressdialog=new ProgressDialog(this);
        mAuth =FirebaseAuth.getInstance();
        mUser =mAuth.getCurrentUser();


        ihaveanaccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Register.this,MainActivity.class));
            }
        });

        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();



            }
        });
    }
    private void PerforAuth(){
      String username =inputusername.getText().toString();
      String email =inputEmail.getText().toString();
      String password =inputPassword.getText().toString();
      String confirmpassword =inputConfirmPassword.getText().toString();

        final String getPlayerOneName =inputusername.getText().toString();
        final String getPlayerTwoName =inputEmail.getText().toString();

      if(username.isEmpty()){
          inputusername.setError("enter the username");
      }else if(!email.matches(emailPattern)){
          inputEmail.setError("Enter cotext Email");
      }else if(password.isEmpty()||password.length()<6){
          inputPassword.setError("Enter right password");
      }else if(!password.equals(confirmpassword)){
          inputConfirmPassword.setError("the passsword is not Match");
      }else {
          progressdialog.setMessage("Please wait while Registeion....");
          progressdialog.setTitle("Registion..");
          progressdialog.setCanceledOnTouchOutside(false);
          progressdialog.show();

          mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NotNull Task<AuthResult> task) {
                  if (task.isSuccessful()) {
                      progressdialog.dismiss();
                      sendUserToNextActivity();
                      Toast.makeText(Register.this, "registertion successful", Toast.LENGTH_SHORT).show();
                  } else {
                      progressdialog.dismiss();
                      Toast.makeText(Register.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                  }
              }
          });
      }}
    public void sendUserToNextActivity(){
        Intent intent=new Intent(Register.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
      }

