package com.example.beautyclaws;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.beautyclaws.models.APIResponse;
import com.example.beautyclaws.models.UserReq;
import com.example.beautyclaws.retro.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    // Declare the TextInputEditText and AppCompatButton variables
    TextInputEditText username;
    TextInputEditText email;
    TextInputEditText password;
    TextInputEditText confirmpassword;
    TextInputEditText mobile;
    AppCompatButton registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize the TextInputEditText and AppCompatButton variables
        username = findViewById(R.id.username_tv);
        email = findViewById(R.id.email_tv);
        password = findViewById(R.id.password_tv);
        confirmpassword = findViewById(R.id.confirmpassword_tv);
        registerButton = findViewById(R.id.register_btn);

        // Set an OnClickListener on the register button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser(); // Call the createUser function when the button is clicked
            }
        });
    }

    // Private method to create a user
    private void createUser() {
        UserReq req = new UserReq(); // Create a new UserReq object
        // Set the fields of the UserReq object with the input values from the text fields
        req.setUsername(username.getEditableText().toString());
        req.setEmail(email.getEditableText().toString());
        req.setPassword(password.getEditableText().toString());
        //req.setConfirmpassord(confirmpassword.getEditableText().toString());
        req.setMobile(mobile.getEditableText().toString());

        // Make the network call to create the user using Retrofit
        Call<APIResponse> call = RetrofitClient.getInstance().getMyApi().createUser(req);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse createResponse = response.body();
                if (createResponse != null) {
                    if (createResponse.getStatusCode()!=200){
                        Log.d("RESPONSE", "Error Response: " + createResponse.getStatusDescription());
                        Toast.makeText(getApplicationContext(), createResponse.getStatusDescription(), Toast.LENGTH_LONG).show();
                    } else{
                        Log.d("RESPONSE", "User created with ID: " + createResponse.getCreatedResourceId());
                        Toast.makeText(getApplicationContext(), "User created successfully! UserId:"+
                                createResponse.getCreatedResourceId(), Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(getApplicationContext(), SignIn.class);
                        startActivity(intent2);
                    }
                } else {
                    Log.d("RESPONSE", "Response body is null");
                    Toast.makeText(getApplicationContext(), "Failed to create user!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e("RESPONSE", "Error: " + t.getMessage());
                Toast.makeText(getApplicationContext(), "An error has occurred", Toast.LENGTH_LONG).show();
            }
        });
    }
}
