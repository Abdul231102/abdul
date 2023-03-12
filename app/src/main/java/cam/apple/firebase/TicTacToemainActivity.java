package cam.apple.firebase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.R;

import java.util.ArrayList;
import java.util.List;



public class TicTacToemainActivity extends AppCompatActivity {

public final List<int[]> combinationList =new ArrayList<>();

public int[] boxpositions ={ 0, 0, 0, 0, 0, 0, 0, 0, 0};

public int playerTurn =1;

public int totalSelectedBoxes =1;

public LinearLayout playerOneLayout,playerTwoLayout;

public TextView playerOneName,playerTwoName;

public ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9;

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_tic_tac_toemain);

     playerOneName =findViewById(R.id.playerOneName);
     playerTwoName =findViewById(R.id.playerTwoName);

      playerOneLayout =findViewById(R.id.playerOneLayout);
      playerTwoLayout =findViewById(R.id.playerTwoLayout);

      image1=findViewById(R.id.image1);
     image2 =findViewById(R.id.image2);
     image3 =findViewById(R.id.image3);
     image4 =findViewById(R.id.image4);
     image5=findViewById(R.id.image5);
     image6=findViewById(R.id.image6);
     image7 =findViewById(R.id.image7);
     image8 =findViewById(R.id.image8);
     image9= findViewById(R.id.image9);

        combinationList.add(new int[]{0, 1, 2});
        combinationList.add(new int[]{3, 4, 5});
        combinationList.add(new int[]{6, 7, 8});
        combinationList.add(new int[]{0, 3, 6});
        combinationList.add(new int[]{0, 1, 2});
        combinationList.add(new int[]{1, 4, 7});
        combinationList.add(new int[]{2, 5, 8});
        combinationList.add(new int[]{2, 4, 6});
        combinationList.add(new int[]{0, 4, 8});

        final String getplayerOneName =getIntent().getStringExtra("playerOne");
        final String getplayerTwoName =getIntent().getStringExtra("playerTwo");

        playerOneName.setText(getplayerOneName);
        playerTwoName.setText(getplayerTwoName);




     image1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(isBoxSelectable(0)){
                 performAction((ImageView)v,0);
             }
         }
     });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(isBoxSelectable(1)){
                  performAction((ImageView)v,1);
              }
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if(isBoxSelectable(2)){
                      performAction((ImageView)v,2);
                  }
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(isBoxSelectable(3)){
                 performAction((ImageView)v,3);
             }
            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(isBoxSelectable(4)){
                   performAction((ImageView)v,4);
               }
            }
        });
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelectable(5)){
                    performAction((ImageView)v,5);
                }
            }
        });
        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(isBoxSelectable(6)){
                  performAction((ImageView)v,6);
              }
            }
        });
        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(isBoxSelectable(7)){
                   performAction((ImageView)v,7);
               }
            }
        });
        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(isBoxSelectable(8)){
                 performAction((ImageView)v,8);
                 }
            }
        });
    }

    public void performAction(ImageView imageview,int selectedBoxPosition){
        boxpositions[selectedBoxPosition]=playerTurn;

        if(playerTurn==1){
            imageview.setImageResource(R.drawable.logo_x_9);


          if(checkPlayerWin()){
           WinsDailog winsDailog = new WinsDailog( TicTacToemainActivity.this,playerOneName.getText().toString()+" has won the match",TicTacToemainActivity.this);
           winsDailog.setCancelable(false);
           winsDailog.show();
          }
            else if(totalSelectedBoxes == 9){

                WinsDailog winsDailog =new WinsDailog(TicTacToemainActivity.this,"IT is a Draw one",TicTacToemainActivity.this);
              winsDailog.setCancelable(false);
          winsDailog.show();
          }
            else {
                changePlayerTurn( 2);

                totalSelectedBoxes++;

          }
        }
        else{
            imageview.setImageResource(R.drawable.logo_o_9);
            if(checkPlayerWin()){
                WinsDailog winsDailog = new WinsDailog( TicTacToemainActivity.this,playerTwoName.getText().toString()+" has won the match",TicTacToemainActivity.this);
                winsDailog.setCancelable(false);
                winsDailog.show();
            }
            else if(selectedBoxPosition == 9){
                WinsDailog winsDailog =new WinsDailog(TicTacToemainActivity.this,"IT is a Draw one",TicTacToemainActivity.this);
                winsDailog.setCancelable(false);
                winsDailog.show();
            }
            else{

                changePlayerTurn(1);

                totalSelectedBoxes++;
            }
        }
    }
  public void changePlayerTurn(int currentPlayerTurn){
        playerTurn =currentPlayerTurn;

        if(playerTurn == 1){
            playerOneLayout.setBackgroundResource(R.drawable.bg_black_border);
            playerTwoLayout.setBackgroundResource(R.drawable.game_cube_dark_blue);
        }
        else{
            playerTwoLayout.setBackgroundResource(R.drawable.bg_black_border);
            playerOneLayout.setBackgroundResource(R.drawable.game_cube_dark_blue);
        }

  }
    public boolean checkPlayerWin(){
        boolean response =false;
        for(int i=0;i<combinationList.size();i++){
            final int[] combination=combinationList.get(i);

            if(boxpositions[combination[0]]==playerTurn && boxpositions [combination[1]]==playerTurn && boxpositions[combination[2]]==playerTurn){
                response = true;
            }
        }
        return response;
    }
    public boolean isBoxSelectable(int boxposition){
        boolean response =false;
        if(boxpositions[boxposition]==0){
            response= true;
        }
        return response;
    }
  public void restartMatch(){
        boxpositions =new int[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0};

        playerTurn =1;

        totalSelectedBoxes =1;

        image1 .setImageResource(R.drawable.tci_emptry_box);
      image2 .setImageResource(R.drawable.tci_emptry_box);
      image3 .setImageResource(R.drawable.tci_emptry_box);
      image4 .setImageResource(R.drawable.tci_emptry_box);
      image5 .setImageResource(R.drawable.tci_emptry_box);
      image6 .setImageResource(R.drawable.tci_emptry_box);
      image7 .setImageResource(R.drawable.tci_emptry_box);
      image8 .setImageResource(R.drawable.tci_emptry_box);
      image9 .setImageResource(R.drawable.tci_emptry_box);
  }
  public void Exitbtn(){
      Intent intent =new Intent(TicTacToemainActivity.this,HomeActivity.class);
      startActivity(intent);
  }
}