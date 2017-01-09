package opensource.databaseschema;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import opensource.databaseschema.database.DatabaseHelper;
import opensource.databaseschema.database.DbOpenHelper;
import opensource.databaseschema.database.TaskModel;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DbOpenHelper dbOpenHelper = new DbOpenHelper(this);
        DatabaseHelper databaseHelper = new DatabaseHelper(dbOpenHelper);
        List<TaskModel> taskModels = new ArrayList<>();
        for (int i=0; i<20; ++i) {
            taskModels.add(new TaskModel("Task Title " + i, "Title Description " + i));
        }
        databaseHelper.setTask(taskModels)
                .subscribe(new Action1<List<TaskModel>>() {
                    @Override
                    public void call(List<TaskModel> taskModels) {
                        Toast.makeText(MainActivity.this, "Sync", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @OnClick(R.id.card_view)
    void onClickTask() {
        startActivity(new Intent(this, TaskList.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
