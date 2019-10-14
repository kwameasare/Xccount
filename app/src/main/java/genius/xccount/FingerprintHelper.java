package genius.xccount;

import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;

public class FingerprintHelper extends FingerprintManager.AuthenticationCallback {

    private FingerprintHelperListener listener;

    public FingerprintHelper(FingerprintHelperListener listener) {
        this.listener = listener;
    }

    private CancellationSignal cancellationSignal;

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        cancellationSignal = new CancellationSignal();

        try {
            manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
        } catch (SecurityException ex) {
            listener.authenticationFailed("An error occurred: " + ex.getMessage());
        } catch (Exception ex) {
            listener.authenticationFailed("An error occurred: " + ex.getMessage());
        }
    }

    public void cancel() {
        if (cancellationSignal != null)
            cancellationSignal.cancel();
    }

    interface FingerprintHelperListener {
        void authenticationFailed(String error);
        void authenticationSuccess(FingerprintManager.AuthenticationResult result);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        listener.authenticationFailed("AuthenticationError : "+errString);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        listener.authenticationFailed("AuthenticationHelp : "+helpString);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        listener.authenticationSuccess(result);
    }

    @Override
    public void onAuthenticationFailed() {
        listener.authenticationFailed("Authentication Failed!");
    }
}