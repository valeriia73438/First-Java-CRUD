package pl.edu.vistula.students;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("")
@PageTitle("Student Management System")
public class StudentView extends VerticalLayout {
    private static final Logger log = LoggerFactory.getLogger(StudentView.class);
    private final StudentService service;
    private final Grid<Student> grid = new Grid<>(Student.class, false);
    private final BeanValidationBinder<Student> binder = new BeanValidationBinder<>(Student.class);
    private final TextField firstName = new TextField("First name");
    private final TextField lastName = new TextField("Last name");
    private final TextField email = new TextField("Email");
    private final TextField course = new TextField("Course");
    private final Button delete = new Button("Delete", event -> confirmDelete());
    private final H2 formTitle = new H2("Add student");
    private Student selected;

    public StudentView(StudentService service) {
        this.service = service;
        addClassName("student-page");
        setWidthFull();
        add(new Paragraph("VISTULA · STUDENT DIRECTORY"), new H1("Student Management System"),
            new Paragraph("View student records, add new students, or select a row to edit."));

        Button add = new Button("Add Student", event -> edit(null));
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        grid.addColumn(Student::getId).setHeader("ID").setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(Student::getFirstName).setHeader("First name").setAutoWidth(true);
        grid.addColumn(Student::getLastName).setHeader("Last name").setAutoWidth(true);
        grid.addColumn(Student::getEmail).setHeader("Email").setAutoWidth(true);
        grid.addColumn(Student::getCourse).setHeader("Course").setAutoWidth(true);
        grid.setHeight("320px");
        grid.asSingleSelect().addValueChangeListener(event -> loadForm(event.getValue()));
        grid.getElement().setAttribute("aria-label", "Students");

        firstName.setMaxLength(100);
        lastName.setMaxLength(100);
        email.setMaxLength(254);
        course.setMaxLength(100);
        binder.bindInstanceFields(this);
        FormLayout form = new FormLayout(firstName, lastName, email, course);
        form.setWidthFull();
        Button save = new Button("Save", event -> save());
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        Button clear = new Button("Clear form", event -> edit(null));
        VerticalLayout editor = new VerticalLayout(formTitle, form, new HorizontalLayout(save, delete, clear));
        editor.addClassName("editor");
        add(add, grid, editor);
        refresh();
        edit(null);
    }

    private void edit(Student student) {
        grid.deselectAll();
        if (student != null) {
            grid.select(student);
        } else {
            loadForm(null);
        }
    }

    private void loadForm(Student student) {
        selected = student;
        // Buffered binding keeps cancelled edits out of the table and database.
        binder.readBean(student == null ? new Student() : student);
        formTitle.setText(student == null ? "Add student" : "Edit student #" + student.getId());
        delete.setEnabled(student != null);
    }

    private void save() {
        Student student = selected == null ? new Student() : selected;
        if (!binder.writeBeanIfValid(student)) {
            notify("Please check the highlighted fields.", true);
            return;
        }
        boolean creating = student.getId() == null;
        try {
            service.save(student);
            refresh();
            edit(null);
            notify(creating ? "Student added." : "Student updated.", false);
        } catch (RuntimeException exception) {
            log.error("Could not save student", exception);
            notify("Could not save the student. Please try again.", true);
        }
    }

    private void confirmDelete() {
        if (selected == null) return;
        Student student = selected;
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader("Delete student?");
        dialog.setText("Delete " + student.getFirstName() + " " + student.getLastName() + "? This cannot be undone.");
        dialog.setCancelable(true);
        dialog.setConfirmText("Delete");
        dialog.setConfirmButtonTheme("error primary");
        dialog.addConfirmListener(event -> {
            try {
                service.delete(student);
                refresh();
                edit(null);
                notify("Student deleted.", false);
            } catch (RuntimeException exception) {
                log.error("Could not delete student", exception);
                notify("Could not delete the student. Please try again.", true);
            }
        });
        dialog.open();
    }

    private void refresh() {
        grid.setItems(service.findAll());
    }

    private void notify(String message, boolean error) {
        Notification notification = Notification.show(message, 4000, Notification.Position.BOTTOM_CENTER);
        notification.addThemeVariants(error ? NotificationVariant.LUMO_ERROR : NotificationVariant.LUMO_SUCCESS);
    }
}
