package ua.nure.fedorenko.kidstim.activity;

import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import ua.nure.fedorenko.kidstim.entity.ParentDTO;
import ua.nure.fedorenko.kidstim.rest.APIServiceImpl;
import ua.nure.fedorenko.kidstim.utils.Validator;
import ua.nure.fedorenko.train2app.R;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.emailEditText)
    EditText emailEditText;

    @BindView(R.id.passwordEditText)
    EditText passwordEditText;

    @BindView(R.id.emailLayout)
    TextInputLayout emailLayout;

    @BindView(R.id.passwordLayout)
    TextInputLayout passwordLayout;

    @BindView(R.id.nameLayout)
    TextInputLayout nameLayout;

    @BindView(R.id.surnameLayout)
    TextInputLayout surnameLayout;

    @BindView(R.id.nameEditText)
    EditText nameEditText;

    @BindView(R.id.surnameEditText)
    EditText surnameEditText;

    private APIServiceImpl apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        apiService = new APIServiceImpl(this);
    }

    @OnClick(R.id.registerButton)
    void onRegisterButtonClick() {
        clearErrors();
        if (isInputValid()) {
            ParentDTO parent = new ParentDTO();
            parent.setEmail(emailEditText.getText().toString());
            parent.setPassword(passwordEditText.getText().toString());
            parent.setName(nameEditText.getText().toString());
            parent.setSurname(surnameEditText.getText().toString());
            apiService.register(parent);
        }
    }

    private boolean isInputValid() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String name = nameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();
        if (Validator.isEmailValid(email) && Validator.isPasswordValid(password) && !name.isEmpty() && !surname.isEmpty() ) {
            return true;
        } else {
            if (!Validator.isEmailValid(email)) {
                emailLayout.setError(getString(R.string.error_invalid_email));
            }
            if (!Validator.isPasswordValid(password)) {
                passwordLayout.setError(getString(R.string.error_invalid_password));
            }
            if (name.isEmpty()) {
                nameLayout.setError(getString(R.string.error_invalid_name));
            }
            if (surname.isEmpty()) {
                surnameLayout.setError(getString(R.string.error_invalid_surname));
            }
            return false;
        }
    }

    private void clearErrors() {
        emailLayout.setError(null);
        passwordLayout.setError(null);
        nameLayout.setError(null);
        surnameLayout.setError(null);
    }
}
