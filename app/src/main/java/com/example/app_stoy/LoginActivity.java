package com.example.app_stoy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    android.widget.Button msendotp;
    EditText mphonenumber;
    CountryCodePicker mcountrycodepicker;
    String countrycode;
    String phonenumber;
    ProgressBar mprogressbarlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mcountrycodepicker=findViewById(R.id.countrycodepicker);
        msendotp=findViewById(R.id.sendotpbutton);
        mphonenumber =findViewById(R.id.getphonenumber);
        mprogressbarlogin=findViewById(R.id.progressbarlogin);

        countrycode=mcountrycodepicker.getSelectedCountryCodeWithPlus();
        mcountrycodepicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                countrycode=mcountrycodepicker.getSelectedCountryCodeWithPlus();
            }
        });

        msendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number;
                number = mphonenumber.getText().toString();
                if (number.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nhập số điện thoại", Toast.LENGTH_SHORT).show();
                } else if (number.length() < 9) {
                    Toast.makeText(getApplicationContext(), "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                } else {

                    mprogressbarlogin.setVisibility(View.VISIBLE);
                    phonenumber = countrycode + number;
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phonenumber,
                            60,
                            TimeUnit.SECONDS,
                            LoginActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    mprogressbarlogin.setVisibility(View.INVISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    mprogressbarlogin.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getApplicationContext(), "Lỗi Server", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    mprogressbarlogin.setVisibility(View.INVISIBLE);
                                    Intent intent = new Intent(getApplicationContext(), VerityOTPActivity.class);
                                    intent.putExtra("mobile", phonenumber);
                                    intent.putExtra("verificationId", verificationId);
                                    startActivity(intent);
                                }
                            }
                    );
                }
            }
    });
    }
}