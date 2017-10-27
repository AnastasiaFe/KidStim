package ua.nure.fedorenko.kidstim.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Completable;
import rx.Subscription;
import ua.nure.fedorenko.kidstim.activity.SaveRewardActivity;
import ua.nure.fedorenko.kidstim.entity.RewardDTO;
import ua.nure.fedorenko.kidstim.rest.APIServiceImpl;
import ua.nure.fedorenko.train2app.R;

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.ViewHolder> {

    private Context context;
    private List<RewardDTO> rewards;

    public RewardAdapter(List<RewardDTO> rewards, Context context) {
        this.rewards = rewards;
        this.context = context;
    }

    @Override
    public RewardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.reward_recycle_view_item, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(RewardAdapter.ViewHolder holder, int position) {
        RewardDTO reward = rewards.get(position);
        holder.rewardDescTextView.setText(reward.getDescription());
        holder.rewardStatusTextView.setText(reward.getStatus().toString());
        holder.rewardPointsTextView.setText(String.valueOf(reward.getPoints()));

    }

    @Override
    public int getItemCount() {
        return rewards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rewardDescTextView)
        TextView rewardDescTextView;

        @BindView(R.id.rewardStatusTextView)
        TextView rewardStatusTextView;

        @BindView(R.id.rewardPointsTextView)
        TextView rewardPointsTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick(R.id.deleteRewardButton)
        void onDeleteRewardButtonClick() {
            AlertDialog dialog = new AlertDialog.Builder(itemView.getContext())
                    .setTitle(R.string.reward_deleting)
                    .setMessage(R.string.reward_delete_confirmation)
                    .setPositiveButton(R.string.delete, (dialog1, whichButton) -> {
                        deleteReward(rewards.get(getAdapterPosition()).getId());
                        dialog1.dismiss();
                    })
                    .setNegativeButton(R.string.cancel, (dialog12, which) -> dialog12.dismiss())
                    .create();
            dialog.show();
        }

        @OnClick(R.id.showEditRewardButton)
        void onShowEditTaskButtonClick() {
            Intent intent = new Intent(context, SaveRewardActivity.class);
            intent.putExtra("id", rewards.get(getAdapterPosition()).getId());
            context.startActivity(intent);
        }

        private void deleteReward(String id) {
            APIServiceImpl apiService = new APIServiceImpl(context);
            Completable completable = apiService.deleteReward(id);
            Completable.CompletableSubscriber subscriber = new Completable.CompletableSubscriber() {
                @Override
                public void onCompleted() {
                    Toast.makeText(context, "Ok", Toast.LENGTH_LONG).show();
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
    }
}
