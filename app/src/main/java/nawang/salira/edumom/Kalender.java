package nawang.salira.edumom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Kalender extends AppCompatActivity {

    ImageButton kal_kembali;
    TextInputLayout kal_laytglhpht;
    TextInputEditText kal_tglghpht;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);

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
