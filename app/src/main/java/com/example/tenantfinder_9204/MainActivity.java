package com.example.tenantfinder_9204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
 GoogleSignInClient mGoogleSignInClient;
 private static int RC_SIGN_IN = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();
    mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    SignInButton SignInbutton = findViewById(R.id.sign_in_button);
        SignInButton SignInButton = null;
        SignInButton.setSize(SignInbutton.SIZE_STANDARD);
    SignInButton.setnClickListner(new View.OnClickListener() {
        @Override
        public void onClick(View view){
            switch ( (view.getId())){
            case R.id.sign_in_button:
                signIn();
                }}
        });
    }
         private void signIn() {
             Intent signInIntent = mGoogleSignInClient.getSignInIntent();
             startActivity(signInIntent, getRcSignIn());

         }

    private int getRcSignIn() {
        return RC_SIGN_IN;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

}


    }
    private void handleSignInResult(Task<GoogleSignInAccount> completeTask){
        try{
            GoogleSignInAccount account= completeTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personEmail = acct.getEmail();
                String personFamilyname = acct.getFamilyName();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                Toast.makeText(this, "user email :"+personEmail, Toast.LENGTH_SHORT);

            }
            startActivity(new Intent(MainActivity.this , You_Are.class));




        } catch (ApiException e) {
            Log.w( Tag"signInResult:failed code=" + e.getStatusCode());
            Log.d("Message", e.toString());


        }
    }


}