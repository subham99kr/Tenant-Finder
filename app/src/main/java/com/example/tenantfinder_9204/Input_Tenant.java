package com.example.tenantfinder_9204;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class Input_Tenant extends AppCompatActivity {
    EditText etName , etPhone , etDetails ;
    ImageView imgGallery;
    Button btnInsertData , uploadBtn;
    ProgressBar progressBar;
     Uri imageUri;
    public String uploadID="";

    DatabaseReference DataBaseTO;
    StorageReference referenceTO = FirebaseStorage.getInstance().getReference();

    //private final int GALLERY_REQ_CODE = 101;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_tenant);
        etName =  findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etDetails = findViewById(R.id.etDetails);
        btnInsertData = findViewById(R.id.btnInsertData);

        imgGallery=findViewById(R.id.imgProfile);
        progressBar=findViewById(R.id.progressBar3);

        ActivityResultLauncher<String>  mGetContent;

        progressBar.setVisibility(View.INVISIBLE);



        mGetContent=registerForActivityResult(new ActivityResultContracts.GetContent(), result -> {
            if (result!=null)
            imgGallery.setImageURI(result);
            //result saved to imageUri.
            imageUri=result;
        });
        imgGallery.setOnClickListener(view -> mGetContent.launch("image/*"));




 //      imgGallery.setOnClickListener(view -> {
  //          Intent iGallery = new Intent(Intent.ACTION_PICK);
  //              iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
  //          startActivityForResult(iGallery,GALLERY_REQ_CODE);
  //      });
        uploadBtn.setOnClickListener(view -> {
            if (imageUri!=null)
                uploadToFirebase(imageUri);
                else
            Toast.makeText(Input_Tenant.this, "Please select image", Toast.LENGTH_SHORT).show();
        });

        DataBaseTO = FirebaseDatabase.getInstance().getReference().child("TENANTS");
        btnInsertData.setOnClickListener (view -> insertTenantData());

    }

    private void uploadToFirebase(Uri uri) {

        StorageReference fileRef = referenceTO.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(taskSnapshot -> {
            progressBar.setVisibility(View.INVISIBLE);
            fileRef.getDownloadUrl().addOnSuccessListener(uri1 -> {
                TenantDB tenantDB = new TenantDB(uri1.toString());
                uploadID = DataBaseTO.push().getKey();
                DataBaseTO.child(uploadID).setValue(tenantDB);



                Toast.makeText(Input_Tenant.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
            });

        }).addOnProgressListener(snapshot -> progressBar.setVisibility(View.VISIBLE)).addOnFailureListener(e -> {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(Input_Tenant.this, "Uploading Failed!", Toast.LENGTH_SHORT).show();
        });

    }

    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }


   /* @Override
    protected void  onActivityResult(int requestCode, int resultCode , @Nullable Intent data) {
       super.onActivityResult(requestCode, resultCode, data);

       if (resultCode==RESULT_OK){

           if (requestCode==GALLERY_REQ_CODE && data != null){

                imageUri=data.getData();
               imgGallery.setImageURI(imageUri);
            }
      }
  }

    */

    private void insertTenantData(){
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String details = etDetails.getText().toString();

        TenantDB tenantsDB = new TenantDB(name,phone,details);

if (uploadID!=null)
{ DataBaseTO.child(uploadID).setValue(tenantsDB);
        Toast.makeText(Input_Tenant.this, "Data Inserted", Toast.LENGTH_SHORT).show();}
else
    Toast.makeText(this, "Choose image and lock it!!", Toast.LENGTH_SHORT).show();
    }

}