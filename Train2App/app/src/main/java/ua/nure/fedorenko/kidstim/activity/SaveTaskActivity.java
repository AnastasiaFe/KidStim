package ua.nure.fedorenko.kidstim.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Completable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import ua.nure.fedorenko.kidstim.entity.ChildDTO;
import ua.nure.fedorenko.kidstim.entity.ParentDTO;
import ua.nure.fedorenko.kidstim.entity.TaskDTO;
import ua.nure.fedorenko.kidstim.entity.TaskStatus;
import ua.nure.fedorenko.kidstim.rest.APIServiceImpl;
import ua.nure.fedorenko.kidstim.utils.Validator;
import ua.nure.fedorenko.train2app.R;

public class SaveTaskActivity extends BaseActivity {

    @BindView(R.id.taskDescEditText)
    EditText taskDescEditText;
    @BindView(R.id.taskPointsEditText)
    EditText taskPointsEditText;
    @BindView(R.id.taskDescLayout)
    TextInputLayout taskDescLayout;
    @BindView(R.id.taskPointsLayout)
    TextInputLayout taskPointsLayout;
    @BindView(R.id.openCalendarButton)
    Button openCalendarButton;
    private TaskDTO task;
    private Calendar expirationDate;
    private APIServiceImpl apiService;
    private ParentDTO parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_task);
        expirationDate = Calendar.getInstance();
        apiService = new APIServiceImpl(this);
        String taskId = getIntent().getStringExtra(getString(R.string.id));
        setTask(taskId);
    }

    @OnClick(R.id.addTaskButton)
    void onAddTaskButtonClick() {
        clearErrors();
        if (isInputValid()) {
            execute();
        }
    }

    private void setTask(String id) {
        if (id != null) {
            Single<TaskDTO> single = apiService.getTaskById(id);
            SingleSubscriber<TaskDTO> subscriber = new SingleSubscriber<TaskDTO>() {
                @Override
                public void onSuccess(TaskDTO value) {
                    task = value;
                    taskDescEditText.setText(task.getDescription());
                    taskPointsEditText.setText(String.valueOf(task.getPoints()));
                    openCalendarButton.setText(new Date(task.getExpirationDate()).toString());
                }

                @Override
                public void onError(Throwable error) {
                    Toast.makeText(SaveTaskActivity.this, getString(R.string.error_task_retrieving), Toast.LENGTH_LONG).show();
                }
            };
            single.subscribe(subscriber);
        } else {
            task = new TaskDTO();
        }
    }

    private void execute() {
        String id = getSharedPreferences(getString(R.string.prefs), MODE_PRIVATE).getString(getString(R.string.user), "");
        Single<ParentDTO> parentSingle = apiService.getParentByEmail(id);
        SingleSubscriber<ParentDTO> subscriber = new SingleSubscriber<ParentDTO>() {
            @Override
            public void onSuccess(ParentDTO value) {
                parent = value;
                showAlertDialog();
            }

            @Override
            public void onError(Throwable error) {
                Toast.makeText(SaveTaskActivity.this, getString(R.string.error_parent_retrieving), Toast.LENGTH_LONG).show();
            }
        };
        parentSingle.subscribe(subscriber);
    }


    public void showAlertDialog() {
        final List<ChildDTO> selectedItems = new ArrayList<>();
        final List<String> names = new ArrayList<>();
        for (ChildDTO child : parent.getChildren()) {
            names.add(child.getName());
        }
        boolean[] checked = new boolean[names.size()];
        if (task.getId() != null) {
            for (int i = 0; i < parent.getChildren().size(); i++) {
                if (task.getChildren().contains(parent.getChildren().get(i))) {
                    selectedItems.add(parent.getChildren().get(i));
                    checked[i] = true;
                }
            }
        }
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.select_children)
                .setMultiChoiceItems(names.toArray(new CharSequence[names.size()]), checked, (dialog12, indexSelected, isChecked) -> {
                    ChildDTO child = parent.getChildren().get(indexSelected);
                    if (isChecked) {
                        selectedItems.add(child);
                    } else if (selectedItems.contains(child)) {
                        selectedItems.remove(child);
                    }
                }).setPositiveButton(R.string.ok, (dialog1, id) -> {
                    task.setChildren(selectedItems);
                    task.setDescription(taskDescEditText.getText().toString());
                    task.setParent(parent);
                    task.setPoints(Integer.parseInt(taskPointsEditText.getText().toString()));
                    task.setCreationDate(new Date().getTime());
                    task.setExpirationDate(expirationDate.getTime().getTime());
                    task.setStatus(TaskStatus.CREATED);
                    save(task);
                }).create();
        dialog.show();

    }

    @OnClick(R.id.openCalendarButton)
    void onOpenCalendarButtonClick() {
        new DatePickerDialog(this, date, expirationDate.get(Calendar.YEAR), expirationDate.get(Calendar.MONTH),
                expirationDate.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            expirationDate.set(year, monthOfYear, dayOfMonth);
        }

    };

    private boolean isInputValid() {
        String desc = taskDescEditText.getText().toString();
        String points = taskPointsEditText.getText().toString();
        if (Validator.isDescriptionValid(desc) && Validator.isPointsValid(points)) {
            return true;
        } else {
            if (!Validator.isDescriptionValid(desc)) {
                taskDescLayout.setError(getString(R.string.error_invalid_desc));
            }
            if (!Validator.isPointsValid(points)) {
                taskPointsLayout.setError(getString(R.string.error_invalid_points));
            }
            return false;
        }
    }

    private void clearErrors() {
        taskDescLayout.setError(null);
        taskPointsLayout.setError(null);
    }

    public SaveTaskActivity() {
    }


    // TODO: 10/23/2017 on completed implementation
    private void save(TaskDTO task) {
        Completable completable;
        if (task.getId() != null) {
            completable = apiService.updateTask(task);
        } else {
            completable = apiService.addTask(task);
        }
        Completable.CompletableSubscriber subscriber = new Completable.CompletableSubscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(SaveTaskActivity.this, getString(R.string.error_task_saving), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSubscribe(Subscription d) {

            }
        };
        completable.subscribe(subscriber);
    }


}
