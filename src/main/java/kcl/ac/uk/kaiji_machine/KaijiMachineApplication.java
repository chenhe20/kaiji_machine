package kcl.ac.uk.kaiji_machine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KaijiMachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaijiMachineApplication.class, args);
    }

}
