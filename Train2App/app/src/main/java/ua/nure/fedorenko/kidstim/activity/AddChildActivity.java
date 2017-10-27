package ua.nure.fedorenko.kidstim.activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Completable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import ua.nure.fedorenko.kidstim.entity.ChildDTO;
import ua.nure.fedorenko.kidstim.entity.ParentDTO;
import ua.nure.fedorenko.kidstim.rest.APIService;
import ua.nure.fedorenko.kidstim.rest.APIServiceImpl;
import ua.nure.fedorenko.kidstim.utils.ImageUtils;
import ua.nure.fedorenko.kidstim.utils.Validator;
import ua.nure.fedorenko.train2app.R;

public class AddChildActivity extends BaseActivity {

    private static final int PICK_IMAGE_FROM_GALLERY_CODE = 1;
    @BindView(R.id.childEmailEditText)
    EditText emailEditText;

    @BindView(R.id.childPasswordEditText)
    EditText passwordEditText;

    @BindView(R.id.emailLayout)
    TextInputLayout emailLayout;

    @BindView(R.id.passwordLayout)
    TextInputLayout passwordLayout;

    @BindView(R.id.nameLayout)
    TextInputLayout nameLayout;

    @BindView(R.id.surnameLayout)
    TextInputLayout surnameLayout;

    @BindView(R.id.childNameEditText)
    EditText nameEditText;

    @BindView(R.id.childSurnameEditText)
    EditText surnameEditText;

    @BindView(R.id.uploadedImage)
    ImageView uploadedImageView;
    @BindView(R.id.openCalendarButton)
    Button openCalendarButton;

    @BindView(R.id.genderRadioGroup)
    RadioGroup genderRadioGroup;
    private String filePath;
    private Calendar birthDate;
    private ParentDTO parent;

    private APIServiceImpl apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        birthDate = Calendar.getInstance();
        apiService = new APIServiceImpl(this);
        String parentEmail = getSharedPreferences("MyPrefs", MODE_PRIVATE).getString("User", "");
        getParentByEmail(parentEmail);

    }

    @OnClick(R.id.addChildButton)
    void onAddChildButtonClick() {
        clearErrors();
        if (isInputValid()) {
            ChildDTO child = new ChildDTO();
            child.setEmail(emailEditText.getText().toString());
            child.setPassword(passwordEditText.getText().toString());
            child.setName(nameEditText.getText().toString());
            child.setSurname(surnameEditText.getText().toString());
            child.setPoints(0);
            switch (genderRadioGroup.getCheckedRadioButtonId()) {
                case R.id.maleRadio:
                    child.setGender(0);
                    break;
                case R.id.femaleRadio:
                    child.setGender(1);
                    break;
            }
            // File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/images" + "/" + child.getName() + ".png");
            child.setPhoto(child.getEmail()+".png");
            child.setDateOfBirth(birthDate.getTimeInMillis());
            updateParent(child);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE_FROM_GALLERY_CODE && resultCode == RESULT_OK && null != data) {
            Uri uri = data.getData();
            filePath = getRealPathFromURIPath(uri, AddChildActivity.this);
            Bitmap bitmap = ImageUtils.decodeFile(filePath);
            uploadedImageView.setImageBitmap(bitmap);
        }
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @OnClick(R.id.uploadImageButton)
    void onUploadImageButtonClick() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_FROM_GALLERY_CODE);
    }

    private void updateParent(ChildDTO child) {
        parent.getChildren().add(child);
        Completable comp = apiService.updateParent(parent);
        Completable.CompletableSubscriber subs = new Completable.CompletableSubscriber() {
            @Override
            public void onCompleted() {
                APIServiceImpl apiService = new APIServiceImpl(AddChildActivity.this);
                Completable completable = apiService.uploadImage(new File(filePath), child.getEmail() + ".png");
                Completable.CompletableSubscriber subscriber = new Completable.CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(AddChildActivity.this, "Image upload", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onSubscribe(Subscription d) {

                    }
                };
                completable.subscribe(subscriber);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onSubscribe(Subscription d) {

            }
        };
        comp.subscribe(subs);
    }

    private void getParentByEmail(String parentEmail) {
        Single<ParentDTO> parentSingle = apiService.getParentByEmail(parentEmail);
        SingleSubscriber<ParentDTO> subscriber = new SingleSubscriber<ParentDTO>() {
            @Override
            public void onSuccess(ParentDTO value) {
                parent = value;

            }

            @Override
            public void onError(Throwable error) {

            }
        };
        parentSingle.subscribe(subscriber);
    }

    private boolean isInputValid() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String name = nameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();
        if (Validator.isEmailValid(email) && Validator.isPasswordValid(password) && !name.isEmpty() && !surname.isEmpty()) {
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

    @OnClick(R.id.openCalendarButton)
    public void onOpenCalendarButtonClick() {
        new DatePickerDialog(this, date, birthDate.get(Calendar.YEAR), birthDate.get(Calendar.MONTH),
                birthDate.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            birthDate.set(year, monthOfYear, dayOfMonth);
        }

    };
}
