package opensource.databaseschema.database;

/**
 * Created by Rajan Maurya on 09/01/17.
 */

public class TaskModel {

    String title;

    String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskModel(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
