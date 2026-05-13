package com.LFS.Main;

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
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public FirebaseApp firebaseApp() {
        try {
            ClassPathResource resource = new ClassPathResource("static/ServiceAccountKey.json");

            if (!resource.exists()) {
                System.err.println("Arquivo ServiceAccountKey.json não encontrado no classpath! Por favor, certifique-se de que o arquivo está na pasta 'resources'.");
                throw new IOException("ServiceAccountKey.json not found.");
            }

            InputStream serviceAccount = resource.getInputStream();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                     
                        .build();

                FirebaseApp initializedApp = FirebaseApp.initializeApp(options);
                System.out.println("✅ Firebase inicializado com sucesso!");
                return initializedApp;
            } else {
                System.out.println("Firebase já foi inicializado. Retornando a instância existente.");
                return FirebaseApp.getInstance();
            }

        } catch (IOException e) {
            System.err.println("Erro crítico ao inicializar Firebase: " + e.getMessage());
            throw new RuntimeException("CRITICAL: Falha ao carregar ServiceAccountKey.json ou erro de I/O na inicialização do Firebase!", e);
        } catch (Exception e) {
            System.err.println("Erro inesperado durante a inicialização do Firebase: " + e.getMessage());
            throw new RuntimeException("CRITICAL: Erro inesperado na inicialização do Firebase!", e);
        }
    }
}