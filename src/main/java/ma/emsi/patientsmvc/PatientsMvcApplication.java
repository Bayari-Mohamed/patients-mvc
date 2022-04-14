package ma.emsi.patientsmvc;

import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {

        SpringApplication.run(PatientsMvcApplication.class, args);
    }
    //@Bean
CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return  args ->{
           patientRepository.save(new Patient(null , "Hassan",new Date(),false ,132));
           patientRepository.save(new Patient(null , "Mohamed",new Date(),false ,133));
           patientRepository.save(new Patient(null , "Yassine",new Date(),false ,145));
           patientRepository.save(new Patient(null , "Hanae",new Date(),false ,134));
      patientRepository.findAll().forEach(p->{
          System.out.println(p.getNom());
          System.out.println(p.getDateNaissance());
          System.out.println(p.getScore());
          System.out.println(p.getId());
      });
            System.out.println("p.getNom()");
        };
}
}
