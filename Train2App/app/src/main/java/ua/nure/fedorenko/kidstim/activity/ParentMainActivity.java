package ua.nure.fedorenko.kidstim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Single;
import rx.SingleSubscriber;
import ua.nure.fedorenko.kidstim.adapter.ChildAdapter;
import ua.nure.fedorenko.kidstim.entity.ChildDTO;
import ua.nure.fedorenko.kidstim.entity.ParentDTO;
import ua.nure.fedorenko.kidstim.rest.APIServiceImpl;
import ua.nure.fedorenko.train2app.R;

public class ParentMainActivity extends BaseActivity {

    @BindView(R.id.childrenRecycleView)
    RecyclerView childrenRecycleView;

    private List<ChildDTO> children;
    private ParentDTO parent;
    private APIServiceImpl apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        apiService = new APIServiceImpl(this);
        String parentEmail = getSharedPreferences("MyPrefs", MODE_PRIVATE).getString("User", "");
        Single<ParentDTO> parentSingle = apiService.getParentByEmail(parentEmail);
        SingleSubscriber<ParentDTO> subscriber = new SingleSubscriber<ParentDTO>() {
            @Override
            public void onSuccess(ParentDTO value) {
                parent = value;
                getSharedPreferences("MyPrefs", MODE_PRIVATE).edit().putString("Id", parent.getId()).apply();
                children = value.getChildren();
                ChildAdapter adapter = new ChildAdapter(children, getBaseContext());
                childrenRecycleView.setAdapter(adapter);
                childrenRecycleView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            }

            @Override
            public void onError(Throwable error) {

            }
        };
        parentSingle.subscribe(subscriber);
    }

    @OnClick(R.id.addChildFloatingActionButton)
    void onAddChildFloatingButtonClick() {
        startActivity(new Intent(ParentMainActivity.this, AddChildActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.parent_main_menu, menu);
        return true;
    }

    // TODO: 10/13/2017 process menu items clicking
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tasksMenuItem:
                startActivity(new Intent(ParentMainActivity.this, ParentTasksActivity.class));
                return true;
            case R.id.rewardsMenuItem:
                //
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
