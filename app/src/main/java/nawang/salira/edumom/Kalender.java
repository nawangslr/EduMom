package nawang.salira.edumom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Kalender extends AppCompatActivity {

    ImageButton kal_kembali;
    ImageView kal_janin;
    TextView kal_tglHpl, kal_deskripsi, kal_usiakehamilan;
    FirebaseAuth mAuth;
    DatabaseReference database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);

        mAuth = FirebaseAuth.getInstance();
        kal_tglHpl = findViewById(R.id.kal_tglHpl);
        kal_deskripsi = findViewById(R.id.kal_deskripsi);
        kal_usiakehamilan = findViewById(R.id.kal_usiakehamilan);

        kal_janin = findViewById(R.id.kal_janin);
        FirebaseUser user = mAuth.getCurrentUser();


        Query query = FirebaseDatabase.getInstance().getReference("IbuHamil").orderByChild("email").equalTo(user.getEmail());
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    IbuHamil ibuHamil = postSnapshot.getValue(IbuHamil.class);
                    kal_tglHpl.setText(ibuHamil.getTglHpl());
                    kal_usiakehamilan.setText(String.valueOf(ibuHamil.getUsiaKehamilan()));
                    System.out.println("Jadi isi usia kehamilan adalaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaah" +kal_usiakehamilan);
//                    Toast.makeText(getApplicationContext(), "Ambil user", Toast.LENGTH_SHORT).show();

                    if (!kal_usiakehamilan.equals(R.string.janin3)) {
                        FirebaseDatabase.getInstance().getReference("image").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String link = dataSnapshot.getValue(String.class);
                                Picasso.get().load(link).into(kal_janin);
                                Toast.makeText(getApplicationContext(), "Ambil gambar", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addValueEventListener(postListener);


        /*if (txt_usia.getText().toString().equals(R.string.janin3)) {
            Query query1 = FirebaseDatabase.getInstance().getReference("image");
            ValueEventListener cekImage = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String link = dataSnapshot.getValue(String.class);
                    Picasso.get().load(link).into(kal_janin);
                    Toast.makeText(getApplicationContext(), "Ambil gambar", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            query1.addValueEventListener(cekImage);
        } else {
            Toast.makeText(getApplicationContext(), "Tidak Ambil Gambar", Toast.LENGTH_SHORT).show();
        }
*/

        kal_kembali = findViewById(R.id.kal_kembali);
        kal_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMenu = new Intent(Kalender.this, Menu.class);
                startActivity(goMenu);
                finish();
            }
        });


    }
}
