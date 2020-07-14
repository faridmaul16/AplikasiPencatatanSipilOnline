package com.e.oraapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuDaftar extends AppCompatActivity {
    Databasehelper db;
    Button Masuk, Daftar;
    EditText Nama,Username,Email,Nohp,Nik,Password,KonfirmasiPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_daftar);
        ImageButton btnKembali = (ImageButton) findViewById(R.id.btnKembali);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MenuLogin.class);
                startActivity(i);
            }

        });
        db = new Databasehelper(this);

        Nama = (EditText)findViewById(R.id.namadaftar);
        Username = (EditText)findViewById(R.id.usernamedaftar);
        Email = (EditText)findViewById(R.id.emaildaftar);
        Nohp = (EditText)findViewById(R.id.nohpdaftar);
        Nik = (EditText)findViewById(R.id.nikdaftar);
        Password = (EditText)findViewById(R.id.passworddaftar);
        KonfirmasiPassword = (EditText)findViewById(R.id.passworddaftar1);
        Masuk = (Button)findViewById(R.id.btnMasukDaftar);
        Daftar = (Button) findViewById(R.id.btnDaftar);

        //login
        Masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MasukIntent = new Intent(MenuDaftar.this, MenuDaftar.class);
                startActivity(MasukIntent);
                finish();
            }
        });

        //register
        Daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNama = Nama.getText().toString();
                String strUsername = Username.getText().toString();
                String strEmail = Email.getText().toString();
                String strNohp = Nohp.getText().toString();
                String strNik = Nik.getText().toString();
                String strPassword = Nama.getText().toString();
                String strKonfirmasiPassword = KonfirmasiPassword.getText().toString();

                if (strPassword.equals(strKonfirmasiPassword)) {
                    Boolean daftar = db.insertUser(strUsername,strPassword);
                    if (daftar == true) {
                        Toast.makeText(getApplicationContext(), "Daftar Berhasil", Toast.LENGTH_SHORT).show();
                        Intent MasukIntent = new Intent(MenuDaftar.this, MenuLogin.class);
                        startActivity(MasukIntent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Daftar Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Password Tidak Cocok", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
