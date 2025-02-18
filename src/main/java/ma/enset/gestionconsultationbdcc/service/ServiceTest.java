package ma.enset.gestionconsultationbdcc.service;

import ma.enset.gestionconsultationbdcc.dao.ConsultationDao;
import ma.enset.gestionconsultationbdcc.dao.PatientDao;
import ma.enset.gestionconsultationbdcc.entities.Patient;

import java.util.List;

public class ServiceTest {
    public static void main(String[] args) {
        ICabinetService service=new CabinetService(new PatientDao(),new ConsultationDao());
        /*Patient patient=new Patient();
        patient.setNom("TAZI");
        patient.setPrenom("AHmed");
        patient.setTel("0698988009");
        service.addPatient(patient);*/
        /*List<Patient> patients=service.getALLPatients();
        patients.forEach(patient->System.out.println(patient));*/
        Patient patient=service.getPatientById(1L);
        patient.setTel("07008889933");
        service.updatePatient(patient);

    }
}
