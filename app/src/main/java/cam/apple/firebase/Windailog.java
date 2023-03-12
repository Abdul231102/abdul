package cam.apple.firebase;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.firebase.R;

public class Windailog extends Dialog {

    private final String message;
    private final MainActivity mainActivity;
    public Windailog(@NonNull Context context,String message) {
        super(context);
        this.message= message;
        this.mainActivity=((MainActivity)context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_dailog_online);

        final TextView messageTV =findViewById(R.id.messageTV);
        final Button startBtn =findViewById(R.id.startNewBtn);
        messageTV.setText(message);


    }
}
