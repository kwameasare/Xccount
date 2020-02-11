package genius.xccount;

import android.app.Activity;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Auth extends Activity implements FingerprintHelper.FingerprintHelperListener {


private FingerprintHelper fingerprintHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);





        fingerprintHelper = new FingerprintHelper(this);
        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        fingerprintHelper.startAuth(fingerprintManager, null);



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


        final Intent intent = new Intent();
        setResult(981, intent);
        finish();



       // Toast.makeText(this, "You Fucking Authenticated", Toast.LENGTH_LONG).show();

    }


}


