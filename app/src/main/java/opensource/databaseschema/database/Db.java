package opensource.databaseschema.database;

import android.content.ContentValues;
import android.database.Cursor;

public class Db {

    public Db() {
    }

    public abstract static class TaskTable {
        public static final String TABLE_NAME = "task";

        public static final String COLUMN_TITLE = "task_title";
        public static final String COLUMN_DESCRIPTION = "description";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_TITLE + " TEXT PRIMARY KEY, " +
                        COLUMN_DESCRIPTION + " TEXT NOT NULL " +
                        " ); ";

        public static ContentValues toContentValues(TaskModel taskModel) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, taskModel.getTitle());
            values.put(COLUMN_DESCRIPTION, taskModel.getDescription());
            return values;
        }

        public static TaskModel parseCursor(Cursor cursor) {
            return new TaskModel(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
        }
    }
}
