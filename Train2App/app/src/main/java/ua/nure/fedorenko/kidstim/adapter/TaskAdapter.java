package ua.nure.fedorenko.kidstim.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.nure.fedorenko.kidstim.entity.TaskDTO;
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
        holder.childPointsTextView.setText(String.valueOf(task.getPoints()));

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.taskDescTextView)
        TextView taskDescTextView;

        @BindView(R.id.taskStatusTextView)
        TextView taskStatusTextView;

        @BindView(R.id.childPointsTextView)
        TextView childPointsTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}

