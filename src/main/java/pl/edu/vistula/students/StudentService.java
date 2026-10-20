package pl.edu.vistula.students;

import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;

@Service
@Validated
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> findAll() {
        return repository.findAll(Sort.by("lastName", "firstName", "id"));
    }

    public Student save(@Valid Student student) {
        return repository.save(student);
    }

    public void delete(Student student) {
        repository.deleteById(student.getId());
    }
}
