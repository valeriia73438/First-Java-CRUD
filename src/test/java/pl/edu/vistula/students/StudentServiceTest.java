package pl.edu.vistula.students;

import jakarta.validation.ConstraintViolationException;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = {
    "spring.datasource.url=jdbc:h2:mem:students-test;DB_CLOSE_DELAY=-1",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
@Transactional
class StudentServiceTest {
    @Autowired StudentService service;
    @Autowired StudentRepository repository;
    @Autowired EntityManager entityManager;

    @Test
    void createsReadsUpdatesAndDeletesStudent() {
        Student saved = service.save(new Student("Alex", "Test", "alex@example.com", "Java"));
        Long id = saved.getId();
        repository.flush();
        entityManager.clear();
        assertThat(id).isNotNull();
        assertThat(service.findAll()).extracting(Student::getId).contains(id);

        saved.setCourse("Advanced Java");
        service.save(saved);
        repository.flush();
        entityManager.clear();
        assertThat(repository.findById(id).orElseThrow().getCourse()).isEqualTo("Advanced Java");

        service.delete(saved);
        repository.flush();
        entityManager.clear();
        assertThat(repository.findById(id)).isEmpty();
    }

    @Test
    void rejectsBlankFields() {
        assertThatThrownBy(() -> service.save(new Student(" ", "", "", "")))
            .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void rejectsInvalidEmail() {
        assertThatThrownBy(() -> service.save(new Student("Alex", "Test", "not-an-email", "Java")))
            .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void rejectsOverlongFields() {
        assertThatThrownBy(() -> service.save(new Student("A".repeat(101), "Test", "alex@example.com", "Java")))
            .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void seedsExamplesAndDoesNotDuplicateThem() throws Exception {
        long count = repository.count();
        assertThat(count).isGreaterThanOrEqualTo(3);
        new StudentApplication().sampleStudents(repository).run();
        assertThat(repository.count()).isEqualTo(count);
    }
}
