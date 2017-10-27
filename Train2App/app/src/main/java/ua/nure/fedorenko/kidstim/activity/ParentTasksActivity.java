package ua.nure.fedorenko.kidstim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Single;
import rx.SingleSubscriber;
import ua.nure.fedorenko.kidstim.adapter.TaskAdapter;
import ua.nure.fedorenko.kidstim.entity.TaskDTO;
import ua.nure.fedorenko.kidstim.rest.APIServiceImpl;
import ua.nure.fedorenko.train2app.R;

public class ParentTasksActivity extends BaseActivity {

    private List<TaskDTO> tasks;
    @BindView(R.id.tasksRecycleView)
    RecyclerView tasksRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_tasks);
        APIServiceImpl apiService = new APIServiceImpl(this);
        String parentId = getSharedPreferences("MyPrefs", MODE_PRIVATE).getString("Id", "");
        Single<List<TaskDTO>> taskSingle = apiService.getTasksByParent(parentId);
        SingleSubscriber<List<TaskDTO>> subscriber = new SingleSubscriber<List<TaskDTO>>() {
            @Override
            public void onSuccess(List<TaskDTO> value) {
                tasks = value;
                TaskAdapter adapter = new TaskAdapter(tasks, ParentTasksActivity.this);
                tasksRecycleView.setAdapter(adapter);
                tasksRecycleView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            }

            @Override
            public void onError(Throwable error) {

            }
        };
        taskSingle.subscribe(subscriber);
    }

    @OnClick(R.id.addTaskFloatingActionButton)
    void onAddTaskFloatingButtonClick() {
        startActivity(new Intent(this, SaveTaskActivity.class));
    }
}
