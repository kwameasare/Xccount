package genius.xccount;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Booted extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent();
        i.setClassName("genius.xccount", "genius.xccount.SetupActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }

}