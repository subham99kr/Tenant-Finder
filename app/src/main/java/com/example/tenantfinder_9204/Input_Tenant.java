package com.example.tenantfinder_9204;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Random;

public class Input_Tenant extends AppCompatActivity {
   private EditText name ,phone , details,email;
  private ImageView imgProfile;
     Uri uri;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_tenant);
        Button btnInsertData = findViewById(R.id.btnInsertData);

        imgProfile=findViewById(R.id.imgProfile);

        ActivityResultLauncher<String>  mGetContent;
        mGetContent=registerForActivityResult(new ActivityResultContracts.GetContent(), result -> {
            if(result != null)
                imgProfile.setImageURI(result);
            uri=result;

        });

        imgProfile.setOnClickListener(view -> mGetContent.launch("image/*"));

        btnInsertData.setOnClickListener (view -> insertTenantData());
    }

    private void insertTenantData(){
        name= findViewById(R.id.etName);
        phone= findViewById(R.id.etPhone);
        details= findViewById(R.id.etDetails);
        email=findViewById(R.id.eMailAddress);


        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setTitle("File Uploader");
        dialog.show();

        FirebaseStorage storage=FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference("image1"+ new Random().nextInt(1000));
        uploader.putFile(uri).addOnSuccessListener(taskSnapshot -> {


            uploader.getDownloadUrl().addOnSuccessListener(uri -> {
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference root = db.getReference("TENANTS");

                TenantDB tenantDB = new TenantDB(email.getText().toString(),name.getText().toString(), phone.getText().toString(), details.getText().toString(),  uri.toString());
                String uUploadID= email.getText().toString().replaceAll("[-+.^:,@]","1");
                uUploadID=uUploadID.toLowerCase();
                root.child(uUploadID).setValue(tenantDB);

                dialog.hide();
                name.setText("");
                phone.setText("");
                details.setText("");
                email.setText("");
                imgProfile.setImageResource(R.drawable.ic_baseline_person);

                Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_LONG).show();


            });
        }).addOnProgressListener((OnProgressListener<UploadTask.TaskSnapshot>) snapshot -> {
                   float percent = ((100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount());
                    dialog.setMessage("Uploaded : "+ (int)percent + "%");
                });

    }

}