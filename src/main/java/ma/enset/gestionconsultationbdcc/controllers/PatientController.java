package ma.enset.gestionconsultationbdcc.controllers;

import ma.enset.gestionconsultationbdcc.entities.Patient;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.gestionconsultationbdcc.dao.ConsultationDao;
import ma.enset.gestionconsultationbdcc.dao.PatientDao;
import ma.enset.gestionconsultationbdcc.entities.Patient;
import ma.enset.gestionconsultationbdcc.service.CabinetService;
import ma.enset.gestionconsultationbdcc.service.ICabinetService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
   @FXML private TextField textFieldNom;
    @FXML private  TextField textFieldPrenom;
    @FXML private TextField textFieldTel;
    @FXML private TextField textFieldSearch;
    @FXML private TableView<Patient> tablePatients;
    @FXML private TableColumn<Patient, Long> columnId;
    @FXML private TableColumn<Patient, String> columnNom;
    @FXML private TableColumn<Patient, String> columnPrenom;
    @FXML private TableColumn<Patient, String> columnTel;
    private Patient selectedPatient;
    @FXML private Label labelSuccess;
    private ICabinetService CabinetService;
    private ObservableList<Patient> patients = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CabinetService=new CabinetService(new PatientDao(), new ConsultationDao());
        columnId.setCellValueFactory(new PropertyValueFactory<>("id") );
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        columnTel.setCellValueFactory(new PropertyValueFactory<>("tel"));

        tablePatients.setItems(patients);
        loadPatients();
        textFieldSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                patients.setAll(CabinetService.searchPatientsByQuery(newValue));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        tablePatients.getSelectionModel().selectedItemProperty().addListener((observableValue, oldPatient, newPatient) -> {
            if(newPatient!=null){
                textFieldNom.setText(newPatient.getNom());
                textFieldPrenom.setText(newPatient.getPrenom());
                textFieldTel.setText(newPatient.getTel());
                selectedPatient=newPatient;
            }
        });

    }
    public void addPatient() {
         Patient patient = new Patient();
         patient.setNom(textFieldNom.getText());
         patient.setPrenom(textFieldPrenom.getText());
         patient.setTel(textFieldTel.getText());
         CabinetService.addPatient(patient);
         loadPatients();
    }
    public void delPatient() {
        Patient patient = tablePatients.getSelectionModel().getSelectedItem();
        CabinetService.deletePatient(patient);
        loadPatients();
        labelSuccess.setText("Le patient a été bien supprimé");
    }
    public void updatePatient(){
        selectedPatient.setNom(textFieldNom.getText());
        selectedPatient.setPrenom(textFieldPrenom.getText());
        selectedPatient.setTel(textFieldTel.getText());
        CabinetService.updatePatient(selectedPatient);
        loadPatients();
    }
    private void loadPatients() {
        patients.setAll(CabinetService.getALLPatients());
    }
}
