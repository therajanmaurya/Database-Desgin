package opensource.databaseschema;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import opensource.databaseschema.database.DatabaseHelper;
import opensource.databaseschema.database.DbOpenHelper;
import opensource.databaseschema.database.TaskModel;
import rx.functions.Action1;

/**
 * Created by Rajan Maurya on 09/01/17.
 */

public class TaskList extends AppCompatActivity
        implements RecyclerItemClickListener.OnItemClickListener {

    @BindView(R.id.rv_task)
    RecyclerView mRecyclerView;

    private Adapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ButterKnife.bind(this);
        mAdapter = new Adapter();
        DbOpenHelper dbOpenHelper = new DbOpenHelper(this);
        DatabaseHelper databaseHelper = new DatabaseHelper(dbOpenHelper);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        databaseHelper.getTasks().subscribe(new Action1<List<TaskModel>>() {
            @Override
            public void call(List<TaskModel> taskModels) {
                mAdapter.setTask(taskModels);
            }
        });

    }

    @Override
    public void onItemClick(View childView, int position) {

    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }
}
