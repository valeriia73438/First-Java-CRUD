package pl.edu.vistula.students;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.textfield.TextField;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class StudentViewTest {
    private final StudentService service = mock(StudentService.class);

    private StudentView viewWith(Student student) {
        when(service.findAll()).thenReturn(List.of(student));
        StudentView view = new StudentView(service);
        clearInvocations(service);
        return view;
    }

    private static Stream<Component> descendants(Component component) {
        return Stream.concat(Stream.of(component), component.getChildren().flatMap(StudentViewTest::descendants));
    }

    private static <T extends Component> T component(StudentView view, Class<T> type) {
        return descendants(view).filter(type::isInstance).map(type::cast).findFirst().orElseThrow();
    }

    private static Button button(StudentView view, String text) {
        return descendants(view).filter(Button.class::isInstance).map(Button.class::cast)
            .filter(button -> button.getText().equals(text)).findFirst().orElseThrow();
    }

    private static List<TextField> fields(StudentView view) {
        return descendants(view).filter(TextField.class::isInstance).map(TextField.class::cast).toList();
    }

    private static void assertAddMode(StudentView view) {
        assertThat(fields(view)).hasSize(4).allSatisfy(field -> assertThat(field.getValue()).isEmpty());
        assertThat(component(view, Grid.class).getSelectedItems()).isEmpty();
        assertThat(component(view, H2.class).getText()).isEqualTo("Add student");
        assertThat(button(view, "Delete").isEnabled()).isFalse();
    }

    @Test
    void clearsUnsavedNewStudentWithoutWritingRecords() {
        StudentView view = viewWith(new Student("Alex", "Test", "alex@example.com", "Java"));
        fields(view).forEach(field -> field.setValue("Unsaved input"));

        button(view, "Clear form").click();

        assertAddMode(view);
        verifyNoInteractions(service);
        // Repeated clearing is safe when the form is already empty.
        button(view, "Clear form").click();
        assertAddMode(view);
        verifyNoInteractions(service);
    }

    @Test
    @SuppressWarnings("unchecked")
    void discardsUnsavedEditsAndDeselectsStudentWithoutChangingRecord() {
        Student student = new Student("Alex", "Test", "alex@example.com", "Java");
        StudentView view = viewWith(student);
        Grid<Student> grid = component(view, Grid.class);
        grid.select(student);
        assertThat(button(view, "Delete").isEnabled()).isTrue();
        assertThat(component(view, H2.class).getText()).startsWith("Edit student #");
        fields(view).forEach(field -> field.setValue("Unsaved change"));

        button(view, "Clear form").click();

        assertAddMode(view);
        verifyNoInteractions(service);
        assertThat(student.getFirstName()).isEqualTo("Alex");
        assertThat(student.getLastName()).isEqualTo("Test");
        assertThat(student.getEmail()).isEqualTo("alex@example.com");
        assertThat(student.getCourse()).isEqualTo("Java");
        grid.select(student);
        assertThat(fields(view)).extracting(TextField::getValue)
            .containsExactly("Alex", "Test", "alex@example.com", "Java");
    }
}
