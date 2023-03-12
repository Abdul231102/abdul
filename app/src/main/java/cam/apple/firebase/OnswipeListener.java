package cam.apple.firebase;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

public class OnswipeListener implements View.OnTouchListener {

    public GestureDetector gestureDetector;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);

    }
    public OnswipeListener(Context context){
        gestureDetector =new GestureDetector(context,new GestureListener() );
    }
    private final class GestureListener extends GestureDetector.SimpleOnGestureListener{
        public static final int SWIPE_THRESOLD =100;
        public static final int SWIPE_VELOCITY_THRESOLD=100;

        @Override
        public boolean onDown(@NonNull MotionEvent e) {
            return true;
        }
        @Override
        public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            boolean result =false;
            float yDiff =e2.getY() - e1.getY();
            float xDiff =e2.getX() - e1.getX();
            if(Math.abs(xDiff)> Math.abs(yDiff)) {
                if (Math.abs(xDiff) > SWIPE_THRESOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESOLD) {
                    if (xDiff > 0) {
                        onSwipeRight();

                    } else {
                        onSwipeLeft();
                    }
                    result = true;


                }
            }

                    else if(Math.abs(yDiff)>SWIPE_THRESOLD && Math.abs(velocityY)>SWIPE_VELOCITY_THRESOLD){
                        if(yDiff > 0){
                            onSwipeBottom();

                        }else{
                            onSwipeTop();
                        }

                    result =true;
            }
            return result;
        }
    }
    void onSwipeLeft(){}
    void onSwipeRight(){}
    void onSwipeTop(){}
    void onSwipeBottom(){}
}
