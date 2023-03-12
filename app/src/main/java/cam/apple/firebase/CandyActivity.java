package cam.apple.firebase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CandyActivity extends AppCompatActivity {

    int[] candies={
            R.drawable.bluecandy,
            R.drawable.yellowcandy,
            R.drawable.greencandy,
            R.drawable.redcandy,
            R.drawable.orangecandy,
            R.drawable.purplecandy
    };
    int widthOfBlocks ,noOfBlocks =8,   widthOfScreen;
    ArrayList<ImageView>candy =new ArrayList<>();
    int candyToBeDragged, candyToReplaced;
    int notcandy=R.drawable.ic_launcher_background;
    Handler Mhandler ;
    int interval =100;
    TextView scoreResult;
    int score =0;
    Button back;

    @SuppressLint({"ClickableViewAccessibility", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candy);
        scoreResult =findViewById(R.id.score);
        back =findViewById(R.id.Exit);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(CandyActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        DisplayMetrics displayMetrics =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthOfScreen =displayMetrics.widthPixels;
        int heightOfScreen =displayMetrics.heightPixels;
        widthOfBlocks =widthOfScreen / noOfBlocks;
        createBoard();

        for(final ImageView imageView : candy)
        {
            imageView.setOnTouchListener(new OnswipeListener(this)
            {
                @Override
                void onSwipeBottom() {
                    super.onSwipeBottom();
                    candyToBeDragged =imageView.getId();
                    candyToReplaced=candyToBeDragged +noOfBlocks;
                    candyInterchanged();
                }

                @Override
                void onSwipeTop() {
                    super.onSwipeTop();
                    candyToBeDragged =imageView.getId();
                    candyToReplaced=candyToBeDragged - noOfBlocks;
                    candyInterchanged();
                }

                @Override
                void onSwipeLeft() {
                    super.onSwipeLeft();
                    candyToBeDragged =imageView.getId();
                    candyToReplaced=candyToBeDragged-1;
                    candyInterchanged();


                }

                @Override
                void onSwipeRight() {
                    super.onSwipeRight();
                    candyToBeDragged =imageView.getId();
                    candyToReplaced=candyToBeDragged +1;
                    candyInterchanged();
                }
            });
        }
         Mhandler =new Handler();
        startRepeat();
    }
    private void checkRowForThree(){
        for(int i =0; i<62;i++){
            int chosedCandy =(int)candy.get(i).getTag();
            boolean isBlank =(int)candy.get(i).getTag() == notcandy;
            Integer[] notVaild={6,7,14,15,22,23,30,31,38,39,46,47,54,55};
            List<Integer> list= Arrays.asList(notVaild);
            if(!list.contains(i)){
                int x=i;
                if((int) candy.get(x++).getTag() == chosedCandy && !isBlank &&
                        (int) candy.get(x++).getTag() == chosedCandy &&
                        (int) candy.get(x).getTag() ==chosedCandy)
                {
                    score =score +3 ;
                    scoreResult.setText(String.valueOf(score));
                    candy.get(x).setImageResource(notcandy);
                    candy.get(x).setTag(notcandy);
                    x--;
                    candy.get(x).setImageResource(notcandy);
                    candy.get(x).setTag(notcandy);
                    x--;
                    candy.get(x).setImageResource(notcandy);
                    candy.get(x).setTag(notcandy);

                }
            }
        }
        moveDownCandies();
    }
    private void checkColumnForThree(){
        for(int i =0; i<47;i++){
            int chosedCandy =(int)candy.get(i).getTag();
            boolean isBlank =(int)candy.get(i).getTag() == notcandy;
                int x=i;
                if((int) candy.get(x).getTag() == chosedCandy && !isBlank &&
                        (int) candy.get(x+noOfBlocks).getTag() == chosedCandy &&
                        (int) candy.get(x+2*noOfBlocks).getTag() ==chosedCandy)
                {
                    score =score +3 ;
                    scoreResult.setText(String.valueOf(score));
                    candy.get(x).setImageResource(notcandy);
                    candy.get(x).setTag(notcandy);
                    x = x+noOfBlocks;
                    candy.get(x).setImageResource(notcandy);
                    candy.get(x).setTag(notcandy);
                    x = x+noOfBlocks;
                    candy.get(x).setImageResource(notcandy);
                    candy.get(x).setTag(notcandy);

                }
            }
        moveDownCandies();
        }
        private void moveDownCandies(){
        Integer[]firstRow ={0,1,2,3,4,5,6,7};
        List<Integer>list =Arrays.asList(firstRow);
        for(int i=55; i>=0; i--){
            if((int) candy.get(i + noOfBlocks).getTag() ==notcandy){
                candy.get(i + noOfBlocks).setImageResource((int) candy.get(i).getTag());
                candy.get(i +noOfBlocks).setTag(candy.get(i).getTag());
                candy.get(i).setImageResource(notcandy);
                candy.get(i).setTag(notcandy);

                if(list.contains(i)&&(int)candy.get(i).getTag() == notcandy){
                        int randomColor = (int) Math.floor(Math.random() * candies.length);
                        candy.get(i).setImageResource(candies[randomColor]);
                        candy.get(i).setTag(candies[randomColor]);
                }
            }
        }
        for(int i =0 ;i <8;i++){
            if((int) candy.get(i).getTag() ==notcandy)
            {
                int randomColor = (int) Math.floor(Math.random() * candies.length);
                candy.get(i).setImageResource(candies[randomColor]);
                candy.get(i).setTag(candies[randomColor]);
            }
        }

    }
    Runnable repeatChecker =new Runnable() {
        @Override
        public void run() {
        try{
            checkRowForThree();
            checkColumnForThree();
            moveDownCandies();
        }
        finally {
          Mhandler.postDelayed(repeatChecker ,interval);
        }
        }
    };
    void startRepeat(){
        repeatChecker.run();
    }
    private void candyInterchanged(){
        int Background= (int) candy.get(candyToReplaced).getTag();
        int Background1= (int) candy.get(candyToBeDragged).getTag();
        candy.get(candyToBeDragged).setImageResource(Background);
        candy.get(candyToReplaced).setImageResource(Background1);
        candy.get(candyToBeDragged).setTag(Background);
        candy.get(candyToReplaced).setTag(Background1);
    }

    private void createBoard() {
        GridLayout gridLayout =findViewById(R.id.board);
        gridLayout.setRowCount(noOfBlocks);
        gridLayout.setColumnCount(noOfBlocks);

        gridLayout.getLayoutParams().width=widthOfScreen;
        gridLayout.getLayoutParams().height=widthOfScreen;

        for(int i =0; i< noOfBlocks * noOfBlocks ; i++){
            ImageView imageView =new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new
                    android.view.ViewGroup.LayoutParams(widthOfBlocks,widthOfBlocks));
            imageView.setMaxHeight(widthOfBlocks);
            imageView.setMaxWidth(widthOfBlocks);
            int randomCandy = (int) Math.floor(Math.random()*candies.length);
            imageView.setImageResource(candies[randomCandy]);
            imageView.setTag(candies[randomCandy]);
            candy.add(imageView);
            gridLayout.addView(imageView);
        }
    }
}