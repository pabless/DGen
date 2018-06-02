package db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "access_requirements")
public class AccessRequirements {

    @DatabaseField(generatedId = true, useGetSet = true)
    private int id;

    @DatabaseField(canBeNull = false, useGetSet = true, unique = true)
    private String name;

    public AccessRequirements() {
    }

    public AccessRequirements(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
