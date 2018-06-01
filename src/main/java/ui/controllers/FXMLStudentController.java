package ui.controllers;

import db.entities.ClassificationSystemConst;
import db.mapper.*;
import db.services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ui.Main;
import ui.models.*;
import ui.utils.SpringFXMLLoader;
import ui.utils.Validation;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Controller("fxmlStudentController")
public class FXMLStudentController implements Initializable {

    @FXML
    private TableView<EducationalComponent> tvGrades;

    @FXML
    private TableColumn<EducationalComponent, Integer> tcNumber;

    @FXML
    private TableColumn<EducationalComponent, String> tcType;

    @FXML
    private TableColumn<EducationalComponent, String> tcName;

    @FXML
    private TableColumn<EducationalComponent, Double> tcCredit;

    @FXML
    private TableColumn<EducationalComponent, Integer> tcGrade;

    @FXML
    private TextField tfFamilyName;

    @FXML
    private TextField tfFamilyNameTr;

    @FXML
    private TextField tfGivenName;

    @FXML
    private TextField tfGivenNameTr;

    @FXML
    private TextField tfDiplomaSubjectUk;

    @FXML
    private TextField tfDiplomaSubjectEn;

    @FXML
    private TextField tfNumber;

    @FXML
    private TextField tfRegistrationNumber;

    @FXML
    private TextField tfPreviousDocument;

    @FXML
    private TextField tfAdditionRegistrationNumber;

    @FXML
    private TextArea taDurationOfTraining;

    @FXML
    private TextArea taInformationOnCertification;

    @FXML
    private DatePicker dpDateOfBirth;

    @FXML
    private DatePicker dpDate;

    @FXML
    private ComboBox<MainField> cbMainField;

    @FXML
    private ComboBox<FieldOfStudy> cbFieldOfStudy;

    @FXML
    private ComboBox<AccessRequirements> cbAccessRequirements;

    @FXML
    private ComboBox<ModeOfStudy> cbModeOfStudy;

    @FXML
    private ComboBox<Protocol> cbProtocol;

    @FXML
    private ComboBox<DurationOfStudy> cbDurationOfStudy;

    @FXML
    private ComboBox<Group> cbGroup;

    @FXML
    private CheckBox chkboxClassificationSystem;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    private Stage stage;

    private Integer studentId;

    private StudentService studentService;
    private ProtocolService protocolService;
    private PreviousDocumentService previousDocumentService;
    private MainFieldService mainFieldService;
    private FieldOfStudyService fieldOfStudyService;
    private OfficialDurationOfProgrammeService officialDurationOfProgrammeService;
    private AccessRequirementsService accessRequirementsService;
    private ModeOfStudyService modeOfStudyService;
    private DurationOfTrainingService durationOfTrainingService;
    private DiplomaSubjectService diplomaSubjectService;
    private EducationalComponentTypeService educationalComponentTypeService;
    private EducationalComponentTemplateService educationalComponentTemplateService;
    private EducationalComponentService educationalComponentService;
    private DurationOfStudyService durationOfStudyService;
    private ArDosService arDosService;
    private GroupService groupService;
    private DiplomaService diplomaService;
    private EctsCreditsService ectsCreditsService;
    private ClassificationSystemService classificationSystemService;

    private ProtocolMapper protocolMapper;
    private PreviousDocumentMapper previousDocumentMapper;
    private MainFieldMapper mainFieldMapper;
    private FieldOfStudyMapper fieldOfStudyMapper;
    private OfficialDurationOfProgrammeMapper officialDurationOfProgrammeMapper;
    private AccessRequirementsMapper accessRequirementsMapper;
    private ModeOfStudyMapper modeOfStudyMapper;
    private DurationOfTrainingMapper durationOfTrainingMapper;
    private EducationalComponentTypeMapper educationalComponentTypeMapper;
    private EducationalComponentTemplateMapper educationalComponentTemplateMapper;
    private EducationalComponentMapper educationalComponentMapper;
    private DurationOfStudyMapper durationOfStudyMapper;
    private GroupMapper groupMapper;
    private EctsCreditsMapper ectsCreditsMapper;
    private ClassificationSystemMapper classificationSystemMapper;
    private StudentMapper studentMapper;
    private DiplomaMapper diplomaMapper;
    private DiplomaSubjectMapper diplomaSubjectMapper;

    private ObservableList<Protocol> protocolObservableList = FXCollections.observableArrayList();
    private ObservableList<MainField> mainFieldObservableList = FXCollections.observableArrayList();
    private ObservableList<FieldOfStudy> fieldOfStudyObservableList = FXCollections.observableArrayList();
    private ObservableList<OfficialDurationOfProgramme> officialDurationOfProgrammeObservableList = FXCollections
            .observableArrayList();
    private ObservableList<AccessRequirements> accessRequirementsObservableList = FXCollections.observableArrayList();
    private ObservableList<ModeOfStudy> modeOfStudyObservableList = FXCollections.observableArrayList();
    private ObservableList<DurationOfTraining> durationOfTrainingObservableList = FXCollections.observableArrayList();
    private ObservableList<EducationalComponentType> educationalComponentTypeObservableList = FXCollections
            .observableArrayList();
    private ObservableList<EducationalComponentTemplate> educationalComponentTemplateObservableList = FXCollections
            .observableArrayList();
    private ObservableList<EducationalComponent> educationalComponentObservableList = FXCollections
            .observableArrayList();
    private ObservableList<DurationOfStudy> durationOfStudyObservableList = FXCollections
            .observableArrayList();
    private ObservableList<Group> groupObservableList = FXCollections.observableArrayList();

    @Autowired
    public FXMLStudentController(StudentService studentService,
                                 ProtocolService protocolService,
                                 PreviousDocumentService previousDocumentService,
                                 MainFieldService mainFieldService,
                                 FieldOfStudyService fieldOfStudyService,
                                 OfficialDurationOfProgrammeService officialDurationOfProgrammeService,
                                 AccessRequirementsService accessRequirementsService,
                                 ModeOfStudyService modeOfStudyService,
                                 DurationOfTrainingService durationOfTrainingService,
                                 DiplomaSubjectService diplomaSubjectService,
                                 EducationalComponentTypeService educationalComponentTypeService,
                                 EducationalComponentTemplateService educationalComponentTemplateService,
                                 EducationalComponentService educationalComponentService,
                                 DurationOfStudyService durationOfStudyService,
                                 ArDosService arDosService,
                                 GroupService groupService,
                                 DiplomaService diplomaService,
                                 EctsCreditsService ectsCreditsService,
                                 ClassificationSystemService classificationSystemService,
                                 ProtocolMapper protocolMapper,
                                 PreviousDocumentMapper previousDocumentMapper,
                                 MainFieldMapper mainFieldMapper,
                                 FieldOfStudyMapper fieldOfStudyMapper,
                                 OfficialDurationOfProgrammeMapper officialDurationOfProgrammeMapper,
                                 AccessRequirementsMapper accessRequirementsMapper,
                                 ModeOfStudyMapper modeOfStudyMapper,
                                 DurationOfTrainingMapper durationOfTrainingMapper,
                                 ClassificationSystemMapper classificationSystemMapper,
                                 EducationalComponentTypeMapper educationalComponentTypeMapper,
                                 EducationalComponentTemplateMapper educationalComponentTemplateMapper,
                                 EducationalComponentMapper educationalComponentMapper,
                                 DurationOfStudyMapper durationOfStudyMapper,
                                 GroupMapper groupMapper,
                                 EctsCreditsMapper ectsCreditsMapper, StudentMapper studentMapper, DiplomaMapper diplomaMapper, DiplomaSubjectMapper diplomaSubjectMapper) {
        this.studentService = studentService;
        this.protocolService = protocolService;
        this.previousDocumentService = previousDocumentService;
        this.mainFieldService = mainFieldService;
        this.fieldOfStudyService = fieldOfStudyService;
        this.officialDurationOfProgrammeService = officialDurationOfProgrammeService;
        this.accessRequirementsService = accessRequirementsService;
        this.modeOfStudyService = modeOfStudyService;
        this.durationOfTrainingService = durationOfTrainingService;
        this.diplomaSubjectService = diplomaSubjectService;
        this.educationalComponentTypeService = educationalComponentTypeService;
        this.educationalComponentTemplateService = educationalComponentTemplateService;
        this.educationalComponentService = educationalComponentService;
        this.durationOfStudyService = durationOfStudyService;
        this.arDosService = arDosService;
        this.groupService = groupService;
        this.ectsCreditsService = ectsCreditsService;
        this.classificationSystemService = classificationSystemService;
        this.diplomaService = diplomaService;

        this.protocolMapper = protocolMapper;
        this.previousDocumentMapper = previousDocumentMapper;
        this.mainFieldMapper = mainFieldMapper;
        this.fieldOfStudyMapper = fieldOfStudyMapper;
        this.officialDurationOfProgrammeMapper = officialDurationOfProgrammeMapper;
        this.accessRequirementsMapper = accessRequirementsMapper;
        this.modeOfStudyMapper = modeOfStudyMapper;
        this.durationOfTrainingMapper = durationOfTrainingMapper;
        this.educationalComponentTypeMapper = educationalComponentTypeMapper;
        this.educationalComponentTemplateMapper = educationalComponentTemplateMapper;
        this.educationalComponentMapper = educationalComponentMapper;
        this.durationOfStudyMapper = durationOfStudyMapper;
        this.ectsCreditsMapper = ectsCreditsMapper;
        this.classificationSystemMapper = classificationSystemMapper;
        this.studentMapper = studentMapper;
        this.diplomaMapper = diplomaMapper;
        this.groupMapper = groupMapper;
        this.diplomaSubjectMapper = diplomaSubjectMapper;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearObservableLists();
        initializeObservableLists();
        initializeComboBoxes();
        initializeTableView();
        setListenersOnButtons();
        setListenersOnInputs();
    }

    private void initializeComboBoxes() {
        cbModeOfStudy.getItems().addAll(modeOfStudyObservableList);
        cbDurationOfStudy.getItems().addAll(durationOfStudyObservableList);
        cbMainField.getItems().addAll(mainFieldObservableList);
        cbFieldOfStudy.getItems().addAll(fieldOfStudyObservableList);
        cbProtocol.getItems().addAll(protocolObservableList);
        cbAccessRequirements.getItems().addAll(accessRequirementsObservableList);
        cbGroup.getItems().addAll(groupObservableList);
    }

    private void initializeTableView() {

        for (EducationalComponentTemplate educationalComponentTemplate :
                educationalComponentTemplateObservableList) {
            educationalComponentObservableList.add(new EducationalComponent(educationalComponentTemplate.getId(),
                    0, educationalComponentTemplate.getCredits(),
                    educationalComponentTemplate.getCourseTitle(), educationalComponentTemplate
                    .getEducationalComponentType().getName(), educationalComponentTemplate,
                    null, null, null));
        }

        tcNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("educationalComponentType"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        tcCredit.setCellValueFactory(new PropertyValueFactory<>("credits"));
        tcGrade.setCellValueFactory(new PropertyValueFactory<>("nationalScore"));

        tvGrades.setItems(educationalComponentObservableList);

        tvGrades.setEditable(true);
        tcCredit.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        tcGrade.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    private void clearObservableLists() {
        modeOfStudyObservableList.clear();
        durationOfStudyObservableList.clear();
        mainFieldObservableList.clear();
        fieldOfStudyObservableList.clear();
        protocolObservableList.clear();
        accessRequirementsObservableList.clear();
        educationalComponentObservableList.clear();
        educationalComponentTemplateObservableList.clear();
        educationalComponentTypeObservableList.clear();
        groupObservableList.clear();
    }

    private void initializeObservableLists() {
        try {
            modeOfStudyObservableList.addAll(modeOfStudyMapper.map(modeOfStudyService.getAll()));
            durationOfStudyObservableList.addAll(durationOfStudyMapper.map(durationOfStudyService.getAll()));
            mainFieldObservableList.addAll(mainFieldMapper.map(mainFieldService.getAll()));
            fieldOfStudyObservableList.addAll(fieldOfStudyMapper.map(fieldOfStudyService.getAll()));
            protocolObservableList.addAll(protocolMapper.map(protocolService.getAll()));
            accessRequirementsObservableList.addAll(accessRequirementsMapper.map(accessRequirementsService.getAll()));
            educationalComponentTypeObservableList.addAll(educationalComponentTypeMapper
                    .map(educationalComponentTypeService.getAll()));
            educationalComponentTemplateObservableList.addAll(educationalComponentTemplateMapper
                    .map(educationalComponentTemplateService.getAll()));
            groupObservableList.addAll(groupMapper.map(groupService.getAll()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListenersOnButtons() {
        btnCancel.setOnMouseClicked(e -> closeWindow());
        btnSave.setOnMouseClicked(e -> {
            if (validateInputs()) {
                try {
                    addStudent();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                closeWindow();
            }
        });
    }

    private void setListenersOnInputs() {

        // listeners for Duration of Programme & Official Duration of Programme
        cbModeOfStudy.valueProperty().addListener(observable -> setDurationOfTraining(cbModeOfStudy.getSelectionModel()
                .getSelectedItem(), cbDurationOfStudy.getSelectionModel().getSelectedItem()));

        cbDurationOfStudy.valueProperty().addListener(observable -> {
            setDurationOfTraining(cbModeOfStudy.
                    getSelectionModel().getSelectedItem(), cbDurationOfStudy.getSelectionModel().getSelectedItem());

            try {
                AccessRequirements accessRequirements = accessRequirementsMapper.map(arDosService
                        .getAccessRequirementsByDurationOfStudyId(cbDurationOfStudy.getSelectionModel()
                                .getSelectedItem().getId()));
                for (AccessRequirements accessRequirements1 :
                        accessRequirementsObservableList) {
                    if (accessRequirements.getId() == accessRequirements1.getId()) {
                        cbAccessRequirements.getSelectionModel().select(accessRequirements1);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // listeners for Information on Certification
        tfDiplomaSubjectUk.textProperty().addListener(observable -> {
            if (tfDiplomaSubjectEn.getText().trim().length() != 0 && cbProtocol.getSelectionModel()
                    .getSelectedItem() != null) {
                setInformationOnCertification();
            }
        });

        tfDiplomaSubjectEn.textProperty().addListener(observable -> {
            if (tfDiplomaSubjectUk.getText().trim().length() != 0 && cbProtocol.getSelectionModel()
                    .getSelectedItem() != null) {
                setInformationOnCertification();
            }
        });

        cbProtocol.valueProperty().addListener(observable -> {
            if (tfDiplomaSubjectUk.getText().trim().length() != 0 && tfDiplomaSubjectEn.getText().trim().length() != 0) {
                setInformationOnCertification();
            }
        });
    }

    private void setDurationOfTraining(ModeOfStudy modeOfStudy, DurationOfStudy durationOfStudy) {
        if (modeOfStudy != null && durationOfStudy != null) {
            try {
                taDurationOfTraining.setText(durationOfTrainingMapper.map(durationOfTrainingService
                        .getByModeAndDurationOfStudy(modeOfStudy.getId(), durationOfStudy.getId())).getName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void setInformationOnCertification() {
        taInformationOnCertification.setText("Бакалаврська робота - \"" +
                tfDiplomaSubjectUk.getText().trim() + "\" " + cbProtocol.getSelectionModel()
                .getSelectedItem().getNameUK() + " / " + "Bachelor's Thesis - \"" +
                tfDiplomaSubjectEn.getText().trim() + "\" " + cbProtocol.getSelectionModel()
                .getSelectedItem().getNameEN());

        taInformationOnCertification.setWrapText(true);
    }

    private void addStudent() throws SQLException {

        // add student
        LocalDate lc = dpDateOfBirth.getValue();
        Calendar c = Calendar.getInstance();
        c.set(lc.getYear(), lc.getMonthValue(), lc.getDayOfMonth());
        final int studentId = studentService.getAll().size() + 1;
        final String familyName = tfFamilyName.getText().trim();
        final String givenName = tfGivenName.getText().trim();
        final String familyNameTr = tfFamilyNameTr.getText().trim();
        final String givenNameTr = tfGivenNameTr.getText().trim();
        final Date dateOfBirth = c.getTime();
        final Protocol protocol = cbProtocol.getSelectionModel().getSelectedItem();
        final PreviousDocument previousDocument = new PreviousDocument(previousDocumentService.getAll().size() + 1,
                tfPreviousDocument.getText().trim());
        final ModeOfStudy modeOfStudy = cbModeOfStudy.getSelectionModel().getSelectedItem();
        final DurationOfStudy durationOfStudy = cbDurationOfStudy.getSelectionModel().getSelectedItem();
        final Group group = cbGroup.getSelectionModel().getSelectedItem();

        previousDocumentService.create(previousDocumentMapper.reverseMap(previousDocument));

        Student student = new Student(studentId, familyName, givenName, familyNameTr, givenNameTr, dateOfBirth,
                protocol, previousDocumentMapper.map(previousDocumentService.getByName(previousDocument.getName())),
                modeOfStudy, durationOfStudy, group);

        studentService.create(studentMapper.reverseMap(student));

        // add diploma
        LocalDate locDate = dpDate.getValue();
        Calendar cal = Calendar.getInstance();
        cal.set(locDate.getYear(), locDate.getMonthValue(), locDate.getDayOfMonth());
        final int diplomaId = diplomaService.getAll().size() + 1;
        final String number = tfNumber.getText().trim();
        final String registrationNumber = tfRegistrationNumber.getText().trim();
        final String additionRegistrationNumber = tfAdditionRegistrationNumber.getText().trim();
        final Date dateOfIssue = cal.getTime();
        final MainField mainField = cbMainField.getSelectionModel().getSelectedItem();
        final FieldOfStudy fieldOfStudy = cbFieldOfStudy.getSelectionModel().getSelectedItem();
        final OfficialDurationOfProgramme officialDurationOfProgramme = officialDurationOfProgrammeMapper
                .map(officialDurationOfProgrammeService.getByModeAndDurationOfStudy(cbModeOfStudy.getSelectionModel()
                        .getSelectedItem().getId(), cbDurationOfStudy.getSelectionModel().getSelectedItem()
                        .getId()));
        final AccessRequirements accessRequirements = cbAccessRequirements.getSelectionModel().getSelectedItem();
        final EctsCredits ectsCredits = ectsCreditsMapper.map(ectsCreditsService.getByDurationOfStudy(cbDurationOfStudy
                .getSelectionModel().getSelectedItem().getId()));
        final ClassificationSystem classificationSystem = getClassificationSystem();
        final DurationOfTraining durationOfTraining = durationOfTrainingMapper.map(durationOfTrainingService
                .getByModeAndDurationOfStudy(modeOfStudy.getId(), durationOfStudy.getId()));
        final DiplomaSubject diplomaSubject = new DiplomaSubject(diplomaSubjectService.getAll().size() + 1,
                tfDiplomaSubjectUk.getText().trim(), tfDiplomaSubjectEn.getText().trim());

        diplomaSubjectService.create(diplomaSubjectMapper.reverseMap(diplomaSubject));

        final Diploma diploma = new Diploma(diplomaId, number, registrationNumber, additionRegistrationNumber,
                dateOfIssue, studentMapper.map(studentService.getByFullName(student.getFamilyName(),
                student.getGivenName())), mainField, fieldOfStudy, officialDurationOfProgramme, accessRequirements,
                ectsCredits, classificationSystem, durationOfTraining, diplomaSubjectMapper.map(diplomaSubjectService
                .getByName(diplomaSubject.getSubjectUK(), diplomaSubject.getSubjectEN())));

        diplomaService.create(diplomaMapper.reverseMap(diploma));


        // add educational component
        for (int i = 0; i < educationalComponentObservableList.size(); i++) {
            EducationalComponent educationalComponent = educationalComponentObservableList.get(i);
            educationalComponent.setDiploma(diplomaMapper.map(diplomaService
                    .getByNumber(diploma.getNumber())));
            educationalComponentService.create(educationalComponentMapper.reverseMap(educationalComponent));
        }
    }

    private ClassificationSystem getClassificationSystem() {
        try {
            return chkboxClassificationSystem.isSelected() ? classificationSystemMapper.map(classificationSystemService
                    .getByName(ClassificationSystemConst.DIPLOMA_WITH_HONORS)) :
                    classificationSystemMapper.map(classificationSystemService.getByName(ClassificationSystemConst.DIPLOMA));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean validateInputs() {
        boolean result = true;

        if (!Validation.validateTextField(tfFamilyName)) {
            tfFamilyName.setStyle(Validation.getTextFieldErrorStyle());
            tfFamilyName.textProperty().addListener(e -> tfFamilyName.setStyle(null));
            result = false;
        }

        if (!Validation.validateTextField(tfFamilyNameTr)) {
            tfFamilyNameTr.setStyle(Validation.getTextFieldErrorStyle());
            tfFamilyNameTr.textProperty().addListener(e -> tfFamilyNameTr.setStyle(null));
            result = false;
        }

        if (!Validation.validateTextField(tfGivenName)) {
            tfGivenName.setStyle(Validation.getTextFieldErrorStyle());
            tfGivenName.textProperty().addListener(e -> tfGivenName.setStyle(null));
            result = false;
        }

        if (!Validation.validateTextField(tfGivenNameTr)) {
            tfGivenNameTr.setStyle(Validation.getTextFieldErrorStyle());
            tfGivenNameTr.textProperty().addListener(e -> tfGivenNameTr.setStyle(null));
            result = false;
        }

        if (!Validation.validateTextField(tfPreviousDocument)) {
            tfPreviousDocument.setStyle(Validation.getTextFieldErrorStyle());
            tfPreviousDocument.textProperty().addListener(e -> tfPreviousDocument.setStyle(null));
            result = false;
        }

        if (!Validation.validateTextField(tfDiplomaSubjectUk)) {
            tfDiplomaSubjectUk.setStyle(Validation.getTextFieldErrorStyle());
            tfDiplomaSubjectUk.textProperty().addListener(e -> tfDiplomaSubjectUk.setStyle(null));
            result = false;
        }

        if (!Validation.validateTextField(tfDiplomaSubjectEn)) {
            tfDiplomaSubjectEn.setStyle(Validation.getTextFieldErrorStyle());
            tfDiplomaSubjectEn.textProperty().addListener(e -> tfDiplomaSubjectEn.setStyle(null));
            result = false;
        }

        if (!Validation.validateTextField(tfNumber)) {
            tfNumber.setStyle(Validation.getTextFieldErrorStyle());
            tfNumber.textProperty().addListener(e -> tfNumber.setStyle(null));
            result = false;
        }

        if (!Validation.validateTextField(tfRegistrationNumber)) {
            tfRegistrationNumber.setStyle(Validation.getTextFieldErrorStyle());
            tfRegistrationNumber.textProperty().addListener(e -> tfRegistrationNumber.setStyle(null));
            result = false;
        }

        if (!Validation.validateTextField(tfAdditionRegistrationNumber)) {
            tfAdditionRegistrationNumber.setStyle(Validation.getTextFieldErrorStyle());
            tfAdditionRegistrationNumber.textProperty().addListener(e -> tfAdditionRegistrationNumber.setStyle(null));
            result = false;
        }

        if (!Validation.validateComboBox(cbModeOfStudy)) {
            cbModeOfStudy.setStyle(Validation.getComboBoxErrorStyle());
            cbModeOfStudy.valueProperty().addListener(e -> cbModeOfStudy.setStyle(null));
            result = false;
        }

        if (!Validation.validateComboBox(cbDurationOfStudy)) {
            cbDurationOfStudy.setStyle(Validation.getComboBoxErrorStyle());
            cbDurationOfStudy.valueProperty().addListener(e -> cbDurationOfStudy.setStyle(null));
            result = false;
        }

        if (!Validation.validateComboBox(cbMainField)) {
            cbMainField.setStyle(Validation.getComboBoxErrorStyle());
            cbMainField.valueProperty().addListener(e -> cbMainField.setStyle(null));
            result = false;
        }

        if (!Validation.validateComboBox(cbFieldOfStudy)) {
            cbFieldOfStudy.setStyle(Validation.getComboBoxErrorStyle());
            cbFieldOfStudy.valueProperty().addListener(e -> cbFieldOfStudy.setStyle(null));
            result = false;
        }

        if (!Validation.validateComboBox(cbProtocol)) {
            cbProtocol.setStyle(Validation.getComboBoxErrorStyle());
            cbProtocol.valueProperty().addListener(e -> cbProtocol.setStyle(null));
            result = false;
        }

        if (!Validation.validateComboBox(cbAccessRequirements)) {
            cbAccessRequirements.setStyle(Validation.getComboBoxErrorStyle());
            cbAccessRequirements.valueProperty().addListener(e -> cbAccessRequirements.setStyle(null));
            result = false;
        }

        if (!Validation.validateComboBox(cbGroup)) {
            cbGroup.setStyle(Validation.getComboBoxErrorStyle());
            cbGroup.valueProperty().addListener(e -> cbGroup.setStyle(null));
            result = false;
        }

        if (!Validation.validateDatePicker(dpDateOfBirth)) {
            dpDateOfBirth.setStyle(Validation.getDatePickerErrorStyle());
            dpDateOfBirth.valueProperty().addListener(e -> dpDateOfBirth.setStyle(null));
            return false;
        }

        if (!Validation.validateDatePicker(dpDate)) {
            dpDate.setStyle(Validation.getDatePickerErrorStyle());
            dpDate.valueProperty().addListener(e -> dpDate.setStyle(null));
            return false;
        }

        return result;
    }

    private void closeWindow() {
        stage.close();
    }

    void display() throws Exception {
        Parent root = SpringFXMLLoader.create()
                .applicationContext(Main.getContext())
                .location(FXMLStudentController.class
                        .getResource("../../fxml/student.fxml"))
                .load();

        Scene scene = new Scene(root);

        stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Додати студента");

        //setting up min width & height parameters for window
        stage.setMinWidth(600);
        stage.setMinHeight(450);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void setStudentId(Integer id) {
        this.studentId = id;
    }
}
