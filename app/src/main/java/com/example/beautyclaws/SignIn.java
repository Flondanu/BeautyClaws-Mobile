package com.example.beautyclaws;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyclaws.models.APIResponse;
import com.example.beautyclaws.models.LoginReq;
import com.example.beautyclaws.models.UserReq;
import com.example.beautyclaws.retro.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {
    // Declare the TextInputEditText and AppCompatButton variables
    TextInputEditText email;
    TextInputEditText password;
    AppCompatButton loginButton;
    TextView signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        // Initialize the TextInputEditText and AppCompatButton variables
        email = findViewById(R.id.email_tv);
        password = findViewById(R.id.password_tv);
        loginButton = findViewById(R.id.login_btn);
        signupButton = findViewById(R.id.don_t_have_sign_up_tv);


        // Set an OnClickListener on the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(); // Call the loginUser function when the button is clicked
            }
        });

        // Set an OnClickListener on the login button
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to signup when the button is clicked
                Toast.makeText(getApplicationContext(), "Go to signup page", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent2);
            }
        });
    }
    // Private method to login a user
    private void loginUser() {
        LoginReq req = new LoginReq(); // login UserReq object
        // Set the fields of the UserReq object with the input values from the text fields
        req.setEmail(email.getEditableText().toString());
        req.setPassword(password.getEditableText().toString());


        // Make the network call to login user using Retrofit
        Call<APIResponse> call = RetrofitClient.getInstance().getMyApi().login(req);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse createResponse = response.body();
                if (createResponse != null) {
                    if (createResponse.getStatusCode()!=200){
                        Log.d("RESPONSE", "User created with ID: " + createResponse.getCreatedResourceId());
                        Toast.makeText(getApplicationContext(), createResponse.getStatusDescription(), Toast.LENGTH_LONG).show();
                    } else{
                        Log.d("RESPONSE", "User created with ID: " + createResponse.getCreatedResourceId());
                        Toast.makeText(getApplicationContext(), "Login successfully! UserId:"+
                                createResponse.getCreatedResourceId(), Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(getApplicationContext(), homepage.class);
                        startActivity(intent2);
                    }
                } else {
                    Log.d("RESPONSE", "Response body is null");
                    Toast.makeText(getApplicationContext(), "Failed to login!", Toast.LENGTH_LONG).show();
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