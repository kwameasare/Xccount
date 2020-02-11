package genius.xccount;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView misc=findViewById(R.id.misc);

        misc.setOnClickListener(v -> {
            Intent fsAct= new Intent(MainActivity.this,SetupActivity.class);
            startActivity(fsAct);
        });


        CardView pro=findViewById(R.id.pro);

        pro.setOnClickListener(v -> {
            Intent fsAct= new Intent(MainActivity.this,Auth.class);
            startActivity(fsAct);
        });


    }
}
