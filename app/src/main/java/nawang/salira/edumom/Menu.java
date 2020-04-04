package nawang.salira.edumom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {

    private FirebaseAuth mAuth;

    LinearLayout mn_kalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.mn_toolbar);
        setSupportActionBar(toolbar);


        mAuth = FirebaseAuth.getInstance();

       /* Button btn_logout = findViewById(R.id.logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(Menu.this, "Logout berhasil.", Toast.LENGTH_SHORT).show();
                Intent goLogin = new Intent(Menu.this, Login.class);
                startActivity(goLogin);
                finish();
            }
        });*/

       mn_kalender = findViewById(R.id.mn_kalender);
       mn_kalender.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent goKalender = new Intent(Menu.this, Kalender.class);
               startActivity(goKalender);
               finish();
           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mn_logout) {
            mAuth.signOut();
            Toast.makeText(Menu.this, "Logout berhasil.", Toast.LENGTH_SHORT).show();
            Intent goLogin = new Intent(Menu.this, Login.class);
            startActivity(goLogin);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
