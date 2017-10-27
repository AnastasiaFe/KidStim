package ua.nure.fedorenko.kidstim.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import ua.nure.fedorenko.kidstim.entity.ChildDTO;
import ua.nure.fedorenko.kidstim.rest.APIServiceImpl;
import ua.nure.fedorenko.train2app.R;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    private List<ChildDTO> children;
    private Context context;
    private Bitmap image;
    private APIServiceImpl apiService;

    public ChildAdapter(List<ChildDTO> children, Context context) {
        this.children = children;
        this.context = context;
        apiService = new APIServiceImpl(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.child_recycle_view_item, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChildDTO child = children.get(position);
        holder.childNameTextView.setText(child.getName());
        holder.childPointsTextView.setText(String.valueOf(child.getPoints()));
        Single<Byte[]> single = apiService.getImage(child.getPhoto());
        SingleSubscriber<Byte[]> singleSubscriber = new SingleSubscriber<Byte[]>() {
            @Override
            public void onSuccess(Byte[] value) {
                image = BitmapFactory.decodeByteArray(ArrayUtils.toPrimitive(value), 0, value.length);
                holder.childImageView.setImageBitmap(image);
            }

            @Override
            public void onError(Throwable error) {

            }
        };
        single.subscribe(singleSubscriber);

    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView childImageView;
        TextView childNameTextView;
        TextView childPointsTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            childNameTextView = (TextView) itemView.findViewById(R.id.childNameTextView);
            childImageView = (ImageView) itemView.findViewById(R.id.childImageView);
            childPointsTextView = (TextView) itemView.findViewById(R.id.childPointsTextView);
        }
    }
}
