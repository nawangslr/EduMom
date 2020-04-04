package nawang.salira.edumom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Registrasi extends AppCompatActivity {

    FirebaseAuth mAuth;

    TextInputEditText txt_email, txt_password, txt_hpht, txt_siklus, txt_usiakehamilan, txt_nohp, txt_hpl;
    TextInputLayout lay_email, lay_password, lay_hpht, lay_siklus, lay_usiakehamilan, lay_nohp, lay_hpl;


    private DatabaseReference database;
    private ProgressDialog loading;


    DatePickerDialog datePickerDialog;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        txt_email = findViewById(R.id.rg_email);
        lay_email = findViewById(R.id.rg_layemail);
        txt_password = findViewById(R.id.rg_password);
        lay_password = findViewById(R.id.rg_laypassword);
        txt_hpht = findViewById(R.id.rg_hpht);
        lay_hpht = findViewById(R.id.rg_layhpht);
        txt_siklus = findViewById(R.id.rg_siklus);
        lay_siklus = findViewById(R.id.rg_laysiklus);
        txt_usiakehamilan = findViewById(R.id.rg_usiakehamilan);
        lay_usiakehamilan = findViewById(R.id.rg_layusiakehamilan);
        txt_nohp = findViewById(R.id.rg_nohp);
        lay_nohp = findViewById(R.id.rg_laynohp);
        txt_hpl = findViewById(R.id.rg_hpl);
        lay_hpl = findViewById(R.id.rg_layhpl);

        /*progressBar = findViewById(R.id.rg_load);

        if (mAuth.getCurrentUser() != null) {
            showLogin();
        }*/

        //datepicker
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Button btn_hpht = findViewById(R.id.rg_btnhpht);
        btn_hpht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

       /* //Material datepicker
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Pilih Tanggal HPHT");
        final MaterialDatePicker materialDatePicker = builder.build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                txt_hpht.setText(materialDatePicker.getHeaderText());
                String tgl = Integer.toString(txt_hpht.get);

            }
        });

        Button btn_hpht = findViewById(R.id.rg_btnhpht);
        btn_hpht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });
*/


        Button btn_login = findViewById(R.id.rg_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogin();
            }
        });

        Button btn_info = findViewById(R.id.rg_info);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo();
            }
        });

        Button btn_daftar = findViewById(R.id.rg_daftar);
        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Semail = txt_email.getText().toString();
                String Spassword = txt_password.getText().toString();
                String SnoHp = txt_nohp.getText().toString();

                
                String StglHpht = txt_hpht.getText().toString();
                Date dateHpht = null;
                try {
                    dateHpht = new SimpleDateFormat("dd-MM-yyyy").parse(StglHpht);
                } catch (ParseException eparse) {
                    throw new RuntimeException(eparse);
                }

                String Ssiklus = txt_siklus.getText().toString();
                Integer intSiklus = Integer.parseInt(Ssiklus);

                String SusiaKehamilan = txt_usiakehamilan.getText().toString();
                Integer intUsiaKehamilan = Integer.parseInt(SusiaKehamilan);

                String StglHpl = txt_hpl.getText().toString();
                Date dateHpl = null;
                try {
                    dateHpl = new SimpleDateFormat("dd-MM-yyyy").parse(StglHpl);
                } catch (ParseException eparse) {
                    throw new RuntimeException(eparse);
                }


                if (Semail.equals("")) {
                    lay_email.setError("Lengkapi email Anda.");
                    lay_email.requestFocus();
                }
                else if (Spassword.equals("")) {
                    lay_password.setError("Lengkapi password Anda.");
                    lay_password.requestFocus();
                }
                else if (Spassword.length() < 6) {
                    lay_password.setError("Minimal terdiri dari 6 karakter.");
                    lay_password.requestFocus();
                }
                else if (StglHpht.equals("")) {
                    lay_hpht.setError("Lengkapi tanggal HPHT.");
                    lay_hpht.requestFocus();
                }
                else if (Ssiklus.equals("")) {
                    lay_siklus.setError("Lengkapi lama siklus menstruasi Anda.");
                    lay_siklus.requestFocus();
                }
                else if (SusiaKehamilan.equals("")) {
                    lay_usiakehamilan.setError("Lengkapi usia kehamilan Anda.");
                    lay_usiakehamilan.requestFocus();
                }
                else if (SnoHp.equals("")) {
                    lay_nohp.setError("Lengkapi no HP suami atau kerabat dekat Anda.");
                    lay_nohp.requestFocus();
                }
                /*if (TextUtils.isEmpty(Semail) || TextUtils.isEmpty(Spassword) || TextUtils.isEmpty(StglHpht) || TextUtils.isEmpty(Ssiklus) || TextUtils.isEmpty(SusiaKehamilan)|| TextUtils.isEmpty(SnoHp)) {
                    Toast.makeText(Registrasi.this, "Harap lengkapi data!", Toast.LENGTH_SHORT).show();
                }*/
                else {
                    mAuth.createUserWithEmailAndPassword(Semail,Spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Registrasi.this, "Registrasi berhasil.", Toast.LENGTH_SHORT).show();
                            showLogin();
                        }
                        else {
                            Toast.makeText(Registrasi.this, "Registrasi gagal. Silakan coba lagi." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    });

                    loading = ProgressDialog.show(Registrasi.this, null, "Tunggu sebentar ya, Bonda..", true, true);
                    submitUser(new IbuHamil(
                            Semail.toLowerCase(),
                            Spassword,
                            dateHpht,
                            intSiklus,
                            intUsiaKehamilan,
                            SnoHp,
                            dateHpl

                    ));
                }

//                progressBar.setVisibility(View.VISIBLE);

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


    private void showLogin() {
        Intent goLogin = new Intent(Registrasi.this, Login.class);
        startActivity(goLogin);
        finish();
    }

    private void showInfo(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.DialogInfo);

        // set title dialog
        alertDialogBuilder.setTitle("Baca dulu ya, Bon!");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("* Isi lama siklus menstruasi berdasarkan hitungan dalam hari. \n\n" +
                        "* Isi usia kehamilan berdasarkan hitungan dalam bulan. \n\n" +
                        "* Isi nomor HP suami dengan nomor HP suami atau kerabat terdekat yang dapat dimintai bantuan apabila Bonda dalam keadaan darurat.")
                .setIcon(R.drawable.ic_info_black_24dp)
                .setCancelable(false)
                .setPositiveButton("Paham", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

    private void submitUser(IbuHamil ibuHamil) {
        database.child("IbuHamil")
                .push()
                .setValue(ibuHamil)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        loading.dismiss();

                        txt_email.setText("");
                        txt_password.setText("");
                        txt_hpht.setText("");
                        txt_siklus.setText("");
                        txt_usiakehamilan.setText("");
                        txt_nohp.setText("");
                        txt_hpl.setText("");

                       /* Toast.makeText(Registrasi.this, "Data berhasil ditambahkan.", Toast.LENGTH_SHORT).show();
                        showLogin();*/

                    }
                });
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);

                txt_hpht.setText(simpleDateFormat.format(newDate.getTime()));
                txt_hpl.setText(simpleDateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
    /*//parsing date
    public void main(String[] args) throws ParseException {
        String StglHpht = txt_hpht.getText().toString();
        DateFormat formatHpHt = new SimpleDateFormat(StglHpht);

    }*/
}
