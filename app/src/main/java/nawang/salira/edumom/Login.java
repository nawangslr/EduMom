package nawang.salira.edumom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;

    TextInputEditText txt_email, txt_password;
    TextInputLayout lay_email, lay_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            showMenu();
        }

        txt_email = findViewById(R.id.lg_email);
        lay_email = findViewById(R.id.lg_layemail);
        txt_password = findViewById(R.id.lg_password);
        lay_password = findViewById(R.id.lg_laypassword);

        Button btn_masuk = findViewById(R.id.lg_masuk);
        btn_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();

                if (email.equals("")) {
                    lay_email.setError("Lengkapi email Anda.");
                }
                else if (password.equals("")) {
                    lay_password.setError("Lengkapi password Anda.");
                }
                else if (password.length() < 6) {
                    lay_password.setError("Minimal terdiri dari 6 karakter.");
                }
                else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(Login.this, "Login berhasil.", Toast.LENGTH_SHORT).show();
                                        showMenu();
                                    }
                                    else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Login.this, "Login gagal. Silakan coba lagi." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

        Button btn_registrasi = findViewById(R.id.lg_registrasi);
        btn_registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegistrasi();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }

    public void showRegistrasi() {
        Intent goRegistrasi = new Intent(Login.this, Registrasi.class);
        startActivity(goRegistrasi);
        finish();
    }

    public void showMenu() {
        Intent goMenu = new Intent(Login.this, Menu.class);
        startActivity(goMenu);
        finish();
    }
}
