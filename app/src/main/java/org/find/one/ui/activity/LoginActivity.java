package org.find.one.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.find.one.MainActivity;
import org.find.one.R;
import org.find.one.data.LoginRepository;
import org.find.one.data.model.Result;
import org.find.one.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginRepository loginRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginRepository = LoginRepository.getInstance();
        initView();
    }

    private void initView() {
        final EditText usernameET = binding.username;
        final EditText passwordET = binding.password;
        final Button loginBTN = binding.login;
        final ProgressBar loading = binding.loading;


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginBTN.setEnabled(loginFormState(usernameET.getText().toString(),
                        passwordET.getText().toString()));
            }
        };
        usernameET.addTextChangedListener(afterTextChangedListener);
        passwordET.addTextChangedListener(afterTextChangedListener);
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Result result = loginRepository.login(usernameET.getText().toString(),
                        passwordET.getText().toString());
                if(result instanceof Result.Success) {
                    login();
                } else if(result instanceof Result.Error) {
                    loginFail(usernameET, passwordET);
                }
            }
        });
    }

    private boolean loginFormState(String username, String password) {
        Log.d("loginFormState", "username:\t"+username+"pwd:\t"+password);
        return (username != null && username.length() >= 3) && (password != null && password.length() >= 6);
    }

    private void login() {
        Intent intent = new Intent(binding.getRoot().getContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void loginFail(EditText usernameET, EditText passwordET) {
        Toast.makeText(getApplicationContext(),"id wrong or password wrong", Toast.LENGTH_LONG).show();
        passwordET.setText(null);

    }
}