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

public class Kalender extends AppCompatActivity {

    ImageButton kal_kembali;
    TextView kal_tglHpl, kal_deskripsi;
    FirebaseAuth mAuth;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);

        mAuth = FirebaseAuth.getInstance();
        kal_tglHpl = findViewById(R.id.kal_tglHpl);
        kal_deskripsi = findViewById(R.id.kal_deskripsi);
        FirebaseUser user = mAuth.getCurrentUser();

        Query query = FirebaseDatabase.getInstance().getReference("IbuHamil").orderByChild("email").equalTo(user.getEmail());
        final ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    IbuHamil ibuHamil = postSnapshot.getValue(IbuHamil.class);
                    kal_tglHpl.setText(ibuHamil.getTglHpl());
                    Toast.makeText(getApplicationContext(), "Ambil user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addValueEventListener(postListener);

//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String hpl = dataSnapshot.getValue(String.class);
//                kal_tglHpl.setText(hpl);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                throw databaseError.toException();
//            }
//        });

        /*database = FirebaseDatabase.getInstance().getReference().child("IbuHamil").child(getUserID);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                IbuHamil ibuHamil = dataSnapshot.getValue(IbuHamil.class);
                if (kal_tglHpl != null) {

                }
                kal_tglHpl.setText(ibuHamil.getTglHpl());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/
        /*database.child("IbuHamil").child(getUserID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot postSnapshot, String s) {
                IbuHamil ibuHamil = postSnapshot.getValue(IbuHamil.class);
                kal_tglHpl.setText(ibuHamil.getTglHpl());
                kal_deskripsi.setText(ibuHamil.getNoHp());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/
        /*database.child("IbuHamil").child(getUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    IbuHamil ibuHamil = snapshot.getValue(IbuHamil.class);
                    kal_tglHpl.setText(ibuHamil.getTglHpht());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("loadPost:onCancelled", databaseError.toException());
            }
        });*/

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
