package com.LFS.EnzoKevin;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class EnzoKevinApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnzoKevinApplication.class, args);
    }

    @PostConstruct
    public void initializeFirebase() {
        try {
            // ClassPathResource is more reliable in Spring environments
            ClassPathResource resource = new ClassPathResource("ServiceAccountKey.json");
            InputStream serviceAccount = resource.getInputStream();

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) { // Prevents duplicate initialization
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase has been initialized successfully.");
            }
        } catch (IOException e) {
            System.err.println("Error initializing Firebase: " + e.getMessage());
        }
    }
}