package ua.nure.fedorenko.kidstim.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import rx.Single;
import rx.SingleSubscriber;
import ua.nure.fedorenko.kidstim.adapter.ChildAdapter;
import ua.nure.fedorenko.kidstim.adapter.TaskAdapter;
import ua.nure.fedorenko.kidstim.entity.TaskDTO;
import ua.nure.fedorenko.kidstim.rest.APIServiceImpl;
import ua.nure.fedorenko.train2app.R;

public class ParentTasksActivity extends BaseActivity {

    private List<TaskDTO> tasks;
    private APIServiceImpl apiService;
    @BindView(R.id.tasksRecycleView)
    RecyclerView tasksRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_tasks);
        apiService = new APIServiceImpl(this);
        String parentId = getSharedPreferences("MyPrefs", MODE_PRIVATE).getString("Id", "");
        Single<List<TaskDTO>> taskSingle = apiService.getTasksByParent(parentId);
        SingleSubscriber<List<TaskDTO>> subscriber = new SingleSubscriber<List<TaskDTO>>() {
            @Override
            public void onSuccess(List<TaskDTO> value) {
                tasks = value;
                TaskAdapter adapter = new TaskAdapter(tasks, getBaseContext());
                tasksRecycleView.setAdapter(adapter);
                tasksRecycleView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            }

            @Override
            public void onError(Throwable error) {

            }
        };
        taskSingle.subscribe(subscriber);

    }
}
