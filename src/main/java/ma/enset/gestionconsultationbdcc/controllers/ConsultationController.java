package ma.enset.gestionconsultationbdcc.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.gestionconsultationbdcc.dao.ConsultationDao;
import ma.enset.gestionconsultationbdcc.dao.PatientDao;
import ma.enset.gestionconsultationbdcc.entities.Consultation;
import ma.enset.gestionconsultationbdcc.entities.Patient;
import ma.enset.gestionconsultationbdcc.service.CabinetService;
import ma.enset.gestionconsultationbdcc.service.ICabinetService;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {
    @FXML private DatePicker dateConsultation;
    @FXML private ComboBox<Patient> comboPatient;
    @FXML private TextArea textFieldDescripition;
    @FXML private TableView<Consultation> tableConsultation;
    @FXML private TableColumn<Consultation,Long> columnId;
    @FXML private TableColumn<Consultation,Date> columnDateConsultation;
    @FXML private TableColumn<Consultation,String> columnDescripition;
    @FXML private TableColumn<Consultation,Patient> comumnPatient;
    private ICabinetService CabinetService;
    private ObservableList<Patient> patients = FXCollections.observableArrayList();
    private ObservableList<Consultation> consultations = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CabinetService=new CabinetService(new PatientDao(), new ConsultationDao());
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDateConsultation.setCellValueFactory(new PropertyValueFactory<>("dateConsultation"));
        columnDescripition.setCellValueFactory(new PropertyValueFactory<>("description"));
        comumnPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        consultations.setAll(CabinetService.getAllConsultations());
        tableConsultation.setItems(consultations);
        comboPatient.setItems(patients);
        patients.setAll(CabinetService.getALLPatients());
    }
    public void addConsultation(){
        Consultation consultation=new Consultation();
        consultation.setDescription(textFieldDescripition.getText());

        consultation.setDateConsultation(Date.valueOf(dateConsultation.getValue()));
        consultation.setPatient(comboPatient.getSelectionModel().getSelectedItem());
        CabinetService.addConsultation(consultation);
    }
    public void delConsultation() {
        Consultation consultation = tableConsultation.getSelectionModel().getSelectedItem();
        if (consultation != null) {
            CabinetService.deleteConsultation(consultation);
            consultations.remove(consultation);
            loadConsultation();
        } else {
            System.out.println("No consultation selected!");
        }
        
    }

    private void loadConsultation() {
        consultations.setAll(CabinetService.getAllConsultations());


    }
}