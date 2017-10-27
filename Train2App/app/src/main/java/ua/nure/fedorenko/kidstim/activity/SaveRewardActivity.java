package ua.nure.fedorenko.kidstim.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Completable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import ua.nure.fedorenko.kidstim.entity.ChildDTO;
import ua.nure.fedorenko.kidstim.entity.ParentDTO;
import ua.nure.fedorenko.kidstim.entity.RewardDTO;
import ua.nure.fedorenko.kidstim.entity.RewardStatus;
import ua.nure.fedorenko.kidstim.rest.APIServiceImpl;
import ua.nure.fedorenko.kidstim.utils.Validator;
import ua.nure.fedorenko.train2app.R;

public class SaveRewardActivity extends BaseActivity {

    private APIServiceImpl apiService;
    @BindView(R.id.rewardDescEditText)
    EditText rewardDescEditText;
    @BindView(R.id.rewardPointsEditText)
    EditText rewardPointsEditText;
    @BindView(R.id.rewardDescLayout)
    TextInputLayout rewardDescLayout;
    @BindView(R.id.rewardPointsLayout)
    TextInputLayout rewardPointsLayout;
    private ParentDTO parent;
    private RewardDTO reward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_reward);
        apiService = new APIServiceImpl(this);
        String rewardId = getIntent().getStringExtra(getString(R.string.id));
        setReward(rewardId);
    }

    @OnClick(R.id.addRewardButton)
    void onAddRewardButtonClick() {
        clearErrors();
        if (isInputValid()) {
            execute();
        }
    }

    private void setReward(String rewardId) {
        if (rewardId != null) {
            Single<RewardDTO> single = apiService.getRewardById(rewardId);
            SingleSubscriber<RewardDTO> subscriber = new SingleSubscriber<RewardDTO>() {
                @Override
                public void onSuccess(RewardDTO value) {
                    reward = value;
                    rewardDescEditText.setText(reward.getDescription());
                    rewardPointsEditText.setText(String.valueOf(reward.getPoints()));
                }

                @Override
                public void onError(Throwable error) {
                    Toast.makeText(SaveRewardActivity.this, getString(R.string.error_reward_retrieving), Toast.LENGTH_LONG).show();
                }
            };
            single.subscribe(subscriber);
        } else {
            reward = new RewardDTO();
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
                Toast.makeText(SaveRewardActivity.this, getString(R.string.error_parent_retrieving), Toast.LENGTH_LONG).show();
            }
        };
        parentSingle.subscribe(subscriber);
    }


    private void showAlertDialog() {
        final List<ChildDTO> selectedItems = new ArrayList<>();
        final List<String> names = new ArrayList<>();
        for (ChildDTO child : parent.getChildren()) {
            names.add(child.getName());
        }
        boolean[] checked = new boolean[names.size()];
        if (reward.getId() != null) {
            for (int i = 0; i < parent.getChildren().size(); i++) {
                if (reward.getChildren().contains(parent.getChildren().get(i))) {
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
                    reward.setChildren(selectedItems);
                    reward.setDescription(rewardDescEditText.getText().toString());
                    reward.setParent(parent);
                    reward.setPoints(Integer.parseInt(rewardPointsEditText.getText().toString()));
                    reward.setStatus(RewardStatus.CREATED);
                    save(reward);
                }).create();
        dialog.show();
    }

    private boolean isInputValid() {
        String desc = rewardDescEditText.getText().toString();
        String points = rewardPointsEditText.getText().toString();
        if (Validator.isDescriptionValid(desc) && Validator.isPointsValid(points)) {
            return true;
        } else {
            if (!Validator.isDescriptionValid(desc)) {
                rewardDescLayout.setError(getString(R.string.error_invalid_desc));
            }
            if (!Validator.isPointsValid(points)) {
                rewardPointsLayout.setError(getString(R.string.error_invalid_points));
            }
            return false;
        }
    }

    private void clearErrors() {
        rewardDescLayout.setError(null);
        rewardPointsLayout.setError(null);
    }


    // TODO: 10/23/2017 onCompleted implementation
    private void save(RewardDTO reward) {
        Completable completable;
        if (reward.getId() != null) {
            completable = apiService.updateReward(reward);
        } else {
            completable = apiService.addReward(reward);
        }
        Completable.CompletableSubscriber subscriber = new Completable.CompletableSubscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(SaveRewardActivity.this, getString(R.string.error_reward_saving), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSubscribe(Subscription d) {

            }
        };
        completable.subscribe(subscriber);
    }


}
