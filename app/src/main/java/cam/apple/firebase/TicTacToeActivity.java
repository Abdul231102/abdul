package cam.apple.firebase;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.R;


public class TicTacToeActivity extends AppCompatActivity {


Button backbtn;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
    backbtn =findViewById(R.id.backbtn);

    final EditText playerOne =findViewById(R.id.playerOneName);
    final EditText playerTwo =findViewById(R.id.playerTwoName);
    final Button startGameBtn =findViewById(R.id.startGameBtn);

    startGameBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String getPlayerOneName =playerOne.getText().toString();
            final String getPlayerTwoName =playerTwo.getText().toString();

            if(getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()){
                Toast.makeText(TicTacToeActivity.this,"Enter the players name",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent =new Intent(TicTacToeActivity.this,TicTacToemainActivity.class);
                intent.putExtra("playerOne",getPlayerOneName);
                intent.putExtra("playerTwo",getPlayerTwoName);
                startActivity(intent);
            }
        }
    });


    backbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            byback();
        }
    });
    }
    public void byback(){
    Intent intent =new Intent(TicTacToeActivity.this,HomeActivity.class);
    startActivity(intent);
}
}