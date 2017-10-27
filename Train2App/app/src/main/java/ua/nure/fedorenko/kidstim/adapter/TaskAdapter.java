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
import ua.nure.fedorenko.kidstim.activity.SaveTaskActivity;
import ua.nure.fedorenko.kidstim.entity.TaskDTO;
import ua.nure.fedorenko.kidstim.rest.APIServiceImpl;
import ua.nure.fedorenko.train2app.R;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private Context context;
    private List<TaskDTO> tasks;

    public TaskAdapter(List<TaskDTO> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.task_recycle_view_item, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskDTO task = tasks.get(position);
        holder.taskDescTextView.setText(task.getDescription());
        holder.taskStatusTextView.setText(task.getStatus().toString());
        holder.taskPointsTextView.setText(String.valueOf(task.getPoints()));

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.taskDescTextView)
        TextView taskDescTextView;

        @BindView(R.id.taskStatusTextView)
        TextView taskStatusTextView;

        @BindView(R.id.taskPointsTextView)
        TextView taskPointsTextView;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.deleteTaskButton)
        void onDeleteTaskButtonClick() {
            AlertDialog dialog = new AlertDialog.Builder(itemView.getContext())
                    .setTitle(R.string.task_deleting)
                    .setMessage(R.string.task_delete_confirmation)
                    .setPositiveButton(R.string.delete, (dialog1, whichButton) -> {
                        deleteTask(tasks.get(getAdapterPosition()).getId());
                        dialog1.dismiss();
                    })
                    .setNegativeButton(R.string.cancel, (dialog12, which) -> dialog12.dismiss())
                    .create();
            dialog.show();
        }

        @OnClick(R.id.showEditTaskButton)
        void onShowEditTaskButtonClick() {
            Intent intent = new Intent(context, SaveTaskActivity.class);
            intent.putExtra("id", tasks.get(getAdapterPosition()).getId());
            context.startActivity(intent);
        }

        private void deleteTask(String id) {
            APIServiceImpl apiService = new APIServiceImpl(context);
            Completable completable = apiService.deleteTask(id);
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

