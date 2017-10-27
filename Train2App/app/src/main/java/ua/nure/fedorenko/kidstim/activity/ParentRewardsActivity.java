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
import ua.nure.fedorenko.kidstim.adapter.RewardAdapter;
import ua.nure.fedorenko.kidstim.entity.RewardDTO;
import ua.nure.fedorenko.kidstim.rest.APIServiceImpl;
import ua.nure.fedorenko.train2app.R;

public class ParentRewardsActivity extends BaseActivity {

    private List<RewardDTO> rewards;

    @BindView(R.id.rewardsRecycleView)
    RecyclerView rewardsRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_rewards);
        APIServiceImpl apiService = new APIServiceImpl(this);
        String parentId = getSharedPreferences("MyPrefs", MODE_PRIVATE).getString("Id", "");
        Single<List<RewardDTO>> rewardsSingle = apiService.getRewardsByParent(parentId);
        SingleSubscriber<List<RewardDTO>> subscriber = new SingleSubscriber<List<RewardDTO>>() {
            @Override
            public void onSuccess(List<RewardDTO> value) {
                rewards = value;
                RewardAdapter adapter = new RewardAdapter(rewards, ParentRewardsActivity.this);
                rewardsRecycleView.setAdapter(adapter);
                rewardsRecycleView.setLayoutManager(new LinearLayoutManager(ParentRewardsActivity.this));
            }

            @Override
            public void onError(Throwable error) {

            }
        };
        rewardsSingle.subscribe(subscriber);
    }

    @OnClick(R.id.addRewardFloatingActionButton)
    void onShowAddRewardFloatingButtonClick() {
        startActivity(new Intent(this, SaveRewardActivity.class));
    }
}
