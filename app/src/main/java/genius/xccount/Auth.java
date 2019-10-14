package genius.xccount;

import android.app.Activity;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Auth extends Activity implements FingerprintHelper.FingerprintHelperListener {


private FingerprintHelper fingerprintHelper;
    int x,y,xh,yh;
    String serial_id,preview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);


        Bundle b = getIntent().getExtras();



        if (b != null) {
            String callpackage = b.getString("get me in");
            x = b.getInt("X");
            y = b.getInt("Y");
            xh = b.getInt("XH");
            yh = b.getInt("YH");
            serial_id=b.getString("ID");
            preview=b.getString("preview");
        }


        fingerprintHelper = new FingerprintHelper(this);
        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        fingerprintHelper.startAuth(fingerprintManager, null);



    }


    class RequestCode {
        static final int IMPORT = 100;
        static final int WRITE_PERMISSION = 101;
    }









    @Override
    protected void onPause() {
        super.onPause();
        if (fingerprintHelper != null)
            fingerprintHelper.cancel();
        this.finish();
    }

    @Override
    public void authenticationFailed(String error) {
        //Your fucking fingerprint ain't right
        Toast.makeText(this, "Invalid Fingerprint", Toast.LENGTH_LONG).show();
    }
    @Override
    public void authenticationSuccess(FingerprintManager.AuthenticationResult result) {


        Intent intent = new Intent();
        intent.setClassName("geniusinc.xplay", "geniusinc.xplay.OpenCard");
        Bundle bundle = new Bundle();
        bundle.putInt("result", 981);
        bundle.putInt("X", x);
        bundle.putInt("Y",y);
        bundle.putInt("XH",xh);
        bundle.putInt("YH",yh);
        bundle.putString("ID",serial_id);
        bundle.putString("preview",preview);
        intent.putExtras(bundle);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
       // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityIfNeeded(intent,1);



       // Toast.makeText(this, "You Fucking Authenticated", Toast.LENGTH_LONG).show();

    }


}


