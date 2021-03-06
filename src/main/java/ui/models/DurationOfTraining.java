package ui.models;

import javafx.beans.property.*;

public class DurationOfTraining {

    private IntegerProperty id;
    private StringProperty name;
    private ObjectProperty<ModeOfStudy> modeOfStudy;
    private ObjectProperty<DurationOfStudy> durationOfStudy;

    public DurationOfTraining() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.modeOfStudy = new SimpleObjectProperty<>();
        this.durationOfStudy = new SimpleObjectProperty<>();
    }

    public DurationOfTraining(int id, String name, ModeOfStudy modeOfStudy,
                              DurationOfStudy durationOfStudy) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.modeOfStudy = new SimpleObjectProperty<>(modeOfStudy);
        this.durationOfStudy = new SimpleObjectProperty<>(durationOfStudy);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public ModeOfStudy getModeOfStudy() {
        return modeOfStudy.get();
    }

    public ObjectProperty<ModeOfStudy> modeOfStudyProperty() {
        return modeOfStudy;
    }

    public void setModeOfStudy(ModeOfStudy modeOfStudy) {
        this.modeOfStudy.set(modeOfStudy);
    }

    @Override
    public String toString() {
        return name.get();
    }

    public DurationOfStudy getDurationOfStudy() {
        return durationOfStudy.get();
    }

    public ObjectProperty<DurationOfStudy> durationOfStudyProperty() {
        return durationOfStudy;
    }

    public void setDurationOfStudy(DurationOfStudy durationOfStudy) {
        this.durationOfStudy.set(durationOfStudy);
    }
}
