package ua.nure.fedorenko.kidstim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import ua.nure.fedorenko.kidstim.entity.ParentDTO;
import ua.nure.fedorenko.kidstim.entity.UserDTO;
import ua.nure.fedorenko.kidstim.rest.APIServiceImpl;
import ua.nure.fedorenko.kidstim.utils.Validator;
import ua.nure.fedorenko.train2app.R;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.emailEditText)
    EditText emailEditText;

    @BindView(R.id.passwordEditText)
    EditText passwordEditText;

    @BindView(R.id.emailLayout)
    TextInputLayout emailLayout;

    @BindView(R.id.passwordLayout)
    TextInputLayout passwordLayout;

    private APIServiceImpl apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiService = new APIServiceImpl(this);
    }

    @OnClick(R.id.loginButton)
    void onLoginButtonClick() {
        clearErrors();
        if (isInputValid()) {
            UserDTO parent = new ParentDTO();
            parent.setEmail(emailEditText.getText().toString());
            parent.setPassword(passwordEditText.getText().toString());
            apiService.login(parent);

        }
    }

    @OnClick(R.id.showRegisterButton)
    void onShowRegisterButtonClick() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private boolean isInputValid() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (Validator.isEmailValid(email) && Validator.isPasswordValid(password)) {
            return true;
        } else {
            if (!Validator.isEmailValid(email)) {
                emailLayout.setError(getString(R.string.error_invalid_email));
            }
            if (!Validator.isPasswordValid(password)) {
                passwordLayout.setError(getString(R.string.error_invalid_password));
            }
            return false;
        }
    }

    private void clearErrors() {
        emailLayout.setError(null);
        passwordLayout.setError(null);
    }


}
