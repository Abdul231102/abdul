package cam.apple.firebase;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.firebase.R;


public class Background {
    int x = 0, y = 0;
    Bitmap background;

    Background (int screenX, int screenY, Resources res) {

        background = BitmapFactory.decodeResource(res, R.drawable.back1);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);

    }

}
