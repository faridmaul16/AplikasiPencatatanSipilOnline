package com.e.oraapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MenuBuat extends AppCompatActivity implements View.OnClickListener{
    Button btnPilih,btnPilih1,btnPilih2, btnUpload,btnUpload1,btnUpload2;
    TextView keteranganfile,keteranganfile1,keteranganfile2;
    FirebaseStorage storage;
    FirebaseDatabase database;
    Uri pdfUri;
    ProgressDialog progressDialog;
    String url;
    private Toast notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_buat);

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        btnUpload = findViewById(R.id.btnUpload);
        btnUpload1 = findViewById(R.id.btnUpload1);
        btnUpload2 = findViewById(R.id.btnUpload2);
        btnPilih = findViewById(R.id.btnPilih);
        btnPilih1 = findViewById(R.id.btnPilih1);
        btnPilih2 = findViewById(R.id.btnPilih2);
        keteranganfile = findViewById(R.id.keteranganfile);
        keteranganfile1 = findViewById(R.id.keteranganfile1);
        keteranganfile2 = findViewById(R.id.keteranganfile2);

        btnPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MenuBuat.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    selectPdf();
                }
                else
                    ActivityCompat.requestPermissions(MenuBuat.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfUri!=null)
                    btnUpload(pdfUri);
                else
                    Toast.makeText(MenuBuat.this, "Mohon Pilih File",Toast.LENGTH_SHORT).show();
            }
        });
        btnPilih1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MenuBuat.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    selectPdf();
                }
                else
                    ActivityCompat.requestPermissions(MenuBuat.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
            }
        });
        btnUpload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfUri!=null)
                    btnUpload(pdfUri);
                else
                    Toast.makeText(MenuBuat.this, "Mohon Pilih File",Toast.LENGTH_SHORT).show();
            }
        });
        btnPilih2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MenuBuat.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    selectPdf();
                }
                else
                    ActivityCompat.requestPermissions(MenuBuat.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
            }
        });
        btnUpload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfUri!=null)
                    btnUpload(pdfUri);
                else
                    Toast.makeText(MenuBuat.this, "Mohon Pilih File",Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton btnKembali = (ImageButton) findViewById(R.id.btnKembali);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MenuLayanan.class);
                startActivity(i);
            }

        });
        Button btnProses = (Button) findViewById(R.id.btnProses);
        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MenuProses.class);
                startActivity(i);
            }

        });
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MenuLayanan.class);
                startActivity(i);
            }

        });
    }
    private void btnUpload(Uri pdfUri) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("File Upload");
        progressDialog.setProgress(0);
        progressDialog.show();

        final String filename = System.currentTimeMillis()+"";
        StorageReference storageReference=storage.getReference();

        storageReference.child("Uploads").child(filename).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        url = taskSnapshot.getStorage().toString();
                        DatabaseReference reference=database.getReference();
                        reference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                    Toast.makeText(MenuBuat.this,"File Berhasil Di Upload",Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(MenuBuat.this,"File Gagal Di Upload",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MenuBuat.this,"File Gagal Di Upload",Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            private int currentProgress;
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int CurrentProgress= (int)( 100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
            }
        });
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            selectPdf();
        }
        else
            Toast.makeText(MenuBuat.this, "Tolong Izinkan Aplikasi",Toast.LENGTH_SHORT).show();

    }

    private void selectPdf() {
        Intent intent= new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {
            pdfUri = data.getData();
            notification.setText("File Terpilih :"+data.getData().getLastPathSegment());
        } else
            Toast.makeText(MenuBuat.this, "Mohon Pilih File", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View v) {

    }
}
