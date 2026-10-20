package pl.edu.vistula.students;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
@Theme("students")
public class StudentApplication implements AppShellConfigurator {
    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    @Bean
    CommandLineRunner sampleStudents(StudentRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                    new Student("Anna", "Kowalska", "anna@example.com", "Computer Engineering"),
                    new Student("Jan", "Nowak", "jan@example.com", "Computer Science"),
                    new Student("Maria", "Wisniewska", "maria@example.com", "Data Science")
                ));
            }
        };
    }
}
