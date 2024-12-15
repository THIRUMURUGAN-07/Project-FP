import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import androidx.core.os.CancellationSignal;
import androidx.core.app.ActivityCompat;

@RequiresApi(api = Build.VERSION_CODES.P)
public class MainActivity extends AppCompatActivity {

    private CancellationSignal cancellationSignal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the BiometricPrompt
        BiometricPrompt biometricPrompt = new BiometricPrompt(this,
                ActivityCompat.getMainExecutor(this),
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        // Authentication successful
                        Toast.makeText(MainActivity.this, "Fingerprint Authentication Successful", Toast.LENGTH_SHORT).show();
                        // Proceed with Aadhaar/Banking related operations
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        // Authentication failed
                        Toast.makeText(MainActivity.this, "Fingerprint Authentication Failed", Toast.LENGTH_SHORT).show();
                    }
                });

        // Configure BiometricPrompt
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Aadhaar and Banking Authentication")
                .setSubtitle("Place your finger on the sensor")
                .setConfirmationRequired(true)
                .build();

        // Start biometric authentication
        biometricPrompt.authenticate(promptInfo);
    }
}
