package opensource.databaseschema;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import opensource.databaseschema.database.TaskModel;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<TaskModel> mTaskModels;

    public Adapter() {
        mTaskModels = new ArrayList<>();
    }

    public void setTask(List<TaskModel> taskModels) {
        mTaskModels = taskModels;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tv_title.setText(mTaskModels.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mTaskModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

       @BindView(R.id.tv_title)
       TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
