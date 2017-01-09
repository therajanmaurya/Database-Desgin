package opensource.databaseschema.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class DatabaseHelper {

    private final BriteDatabase mDb;

    public DatabaseHelper(DbOpenHelper dbOpenHelper) {
        SqlBrite.Builder briteBuilder = new SqlBrite.Builder();
        mDb = briteBuilder.build().wrapDatabaseHelper(dbOpenHelper, Schedulers.immediate());
    }

    public BriteDatabase getBriteDb() {
        return mDb;
    }

    public Observable<List<TaskModel>> setTask(final List<TaskModel> taskModels) {
        return Observable.create(new Observable.OnSubscribe<List<TaskModel>>() {
            @Override
            public void call(Subscriber<? super List<TaskModel>> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(Db.TaskTable.TABLE_NAME, null);
                    for (TaskModel taskModel : taskModels) {
                        mDb.insert(Db.TaskTable.TABLE_NAME,
                                Db.TaskTable.toContentValues(taskModel),
                                SQLiteDatabase.CONFLICT_REPLACE);
                        Log.d("Inserted", "Yes");
                    }
                    transaction.markSuccessful();
                    subscriber.onNext(taskModels);
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<List<TaskModel>> getTasks() {
        return mDb.createQuery(Db.TaskTable.TABLE_NAME,
                "SELECT * FROM " + Db.TaskTable.TABLE_NAME)
                .mapToList(new Func1<Cursor, TaskModel>() {
                    @Override
                    public TaskModel call(Cursor cursor) {
                        return Db.TaskTable.parseCursor(cursor);
                    }
                });
    }

}
