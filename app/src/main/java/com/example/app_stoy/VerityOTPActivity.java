package com.example.app_stoy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerityOTPActivity extends AppCompatActivity {
    EditText code1,code2,code3,code4,code5,code6;
    String verificationId, phoneNumber;
    ProgressBar progressbarOtp;
    android.widget.Button btnmdotp;
    TextView btnsendrq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verity_otpactivity);
        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("mobile");
        verificationId = intent.getStringExtra("verificationId");
        code1 = findViewById(R.id.otpcode1);
        code2 = findViewById(R.id.otpcode2);
        code3 = findViewById(R.id.otpcode3);
        code4 = findViewById(R.id.otpcode4);
        code5 = findViewById(R.id.otpcode5);
        code6 = findViewById(R.id.otpcode6);
        progressbarOtp = findViewById(R.id.progressbarOtp);
        btnsendrq = findViewById(R.id.sendrq);
        btnmdotp = findViewById(R.id.btnLogin);
        inputCode();

        btnmdotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(code1.getText().toString().trim().isEmpty()
                        || code2.getText().toString().trim().isEmpty()
                        || code3.getText().toString().trim().isEmpty()
                        || code4.getText().toString().trim().isEmpty()
                        || code5.getText().toString().trim().isEmpty()
                        || code6.getText().toString().trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Nhập mã OTP" , Toast.LENGTH_SHORT).show();
                }
                String codeOTP = code1.getText().toString().trim()
                        + code2.getText().toString().trim()
                        + code3.getText().toString().trim()
                        + code4.getText().toString().trim()
                        + code5.getText().toString().trim()
                        + code6.getText().toString().trim();
                if(verificationId != null){
                    progressbarOtp.setVisibility(View.VISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verificationId,
                            codeOTP
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressbarOtp.setVisibility(View.INVISIBLE);
                                    if(task.isSuccessful()){
                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Mã OTP sai" , Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        btnsendrq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber,
                        60,
                        TimeUnit.SECONDS,
                        VerityOTPActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            }
                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(getApplicationContext(), "Lỗi Server", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onCodeSent(@NonNull String newverificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                verificationId = newverificationId;
                                Toast.makeText(getApplicationContext(), "OTP Sent", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });


    }
    private  void inputCode(){
        code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    code2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    code3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    code4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    code5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        code5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    code6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}