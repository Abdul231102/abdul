package cam.apple.firebase;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.firebase.R;

public class WinsDailog extends Dialog {
    private final String message;
    private Button startAgainBtn;
    private Button exitBtn;


    private final TicTacToemainActivity ticTacToemainActivity;
    public WinsDailog(@NonNull Context context,String message ,TicTacToemainActivity ticTacToemainActivity) {
        super(context);

        this.message =message;
        this.ticTacToemainActivity=ticTacToemainActivity;
    }

   @SuppressLint("MissingInflatedId")
   @Override
   public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.win_dailoglayout);

        final TextView messageTxt =findViewById(R.id.messageTxt);
       startAgainBtn =findViewById(R.id.startAgainBtn);
       exitBtn =findViewById(R.id.Backbtn);

        messageTxt.setText(message);

        startAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ticTacToemainActivity.restartMatch();
                dismiss();
            }
        });
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticTacToemainActivity.Exitbtn();
                dismiss();
            }
        });

   }
}
