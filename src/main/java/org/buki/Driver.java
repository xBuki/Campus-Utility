package org.buki;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.buki.DTO.*;
import org.buki.Utilities.GenericListNode;

import java.io.*;

public class Driver extends Application {

    static GenericListNode<Department> departments = new GenericListNode<Department>();
    static GenericListNode<Course> courses = new GenericListNode<Course>();
    static GenericListNode<Instructor> instrcutors = new GenericListNode<Instructor>();
    static GenericListNode<Student> students = new GenericListNode<Student>();

    static GenericListNode<Enrollment> enrollment = new GenericListNode<Enrollment>();

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        initializeLists();
        Application.launch(args);

    }

    public static void initializeLists() throws IOException, ClassNotFoundException {
        // Student
        boolean endOfFile = false; // EOF flag
        FileInputStream fstream = new FileInputStream("student.dat");
        ObjectInputStream objectInputFile = new ObjectInputStream(fstream);
        System.out.println("Reading students from the file:");

        // Read the contents of the file.
        while (!endOfFile) {
            try {
                students.addNode((Student) objectInputFile.readObject());
            } catch (EOFException e) {
                endOfFile = true;
            }
        }

        // Department
        fstream = new FileInputStream("department.dat");
        objectInputFile = new ObjectInputStream(fstream);
        System.out.println("Reading departments from the file:");
        endOfFile = false;
        // Read the contents of the file.
        while (!endOfFile) {
            try {
                departments.addNode((Department) objectInputFile.readObject());
            } catch (EOFException e) {
                endOfFile = true;
            }
        }

        fstream = new FileInputStream("instructor.dat");
        objectInputFile = new ObjectInputStream(fstream);
        System.out.println("Reading instructor from the file:");
        endOfFile = false;
        // Read the contents of the file.
        while (!endOfFile) {
            try {
                instrcutors.addNode((Instructor) objectInputFile.readObject());
            } catch (EOFException e) {
                endOfFile = true;
            }
        }

        fstream = new FileInputStream("course.dat");
        objectInputFile = new ObjectInputStream(fstream);
        System.out.println("Reading course from the file:");
        endOfFile = false;
        // Read the contents of the file.
        while (!endOfFile) {
            try {
                courses.addNode((Course) objectInputFile.readObject());
            } catch (EOFException e) {
                endOfFile = true;
            }
        }

        fstream = new FileInputStream("enrollment.dat");
        objectInputFile = new ObjectInputStream(fstream);
        System.out.println("Reading instructor from the file:");
        endOfFile = false;
        // Read the contents of the file.
        while (!endOfFile) {
            try {
                enrollment.addNode((Enrollment) objectInputFile.readObject());
            } catch (EOFException e) {
                endOfFile = true;
            }
        }

        System.out.println("\nDone.");

        // Close the file.
        objectInputFile.close();

    }

    public static void datWriter(String object) throws IOException {
        FileOutputStream outStream;
        ObjectOutputStream objectOutputFile = null;
        if (object == "student") {
            outStream = new FileOutputStream("student.dat");
            objectOutputFile = new ObjectOutputStream(outStream);
            for (int i = 1; i < students.size(); i++) {
                objectOutputFile.writeObject(students.getNode(i));
            }
        } else if (object == "department") {
            outStream = new FileOutputStream("department.dat");
            objectOutputFile = new ObjectOutputStream(outStream);
            for (int i = 1; i < departments.size(); i++) {
                objectOutputFile.writeObject(departments.getNode(i));
            }
        } else if (object == "instructor") {
            outStream = new FileOutputStream("instructor.dat");
            objectOutputFile = new ObjectOutputStream(outStream);
            for (int i = 1; i < instrcutors.size(); i++) {
                objectOutputFile.writeObject(instrcutors.getNode(i));
            }
        } else if (object == "course") {
            outStream = new FileOutputStream("course.dat");
            objectOutputFile = new ObjectOutputStream(outStream);
            for (int i = 1; i < courses.size(); i++) {
                objectOutputFile.writeObject(courses.getNode(i));
            }
        } else if (object == "enrollment") {
            outStream = new FileOutputStream("enrollment.dat");
            objectOutputFile = new ObjectOutputStream(outStream);
            for (int i = 1; i < enrollment.size(); i++) {
                objectOutputFile.writeObject(enrollment.getNode(i));
            }
        }
        objectOutputFile.close();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scence = new Scene(root);
        primaryStage.setTitle("Campus Utilities");
        Text text2 = new Text();
        MenuItem item1 = new MenuItem("Search");
        MenuItem item2 = new MenuItem("Add Students");
        MenuItem item3 = new MenuItem("Edit Students");
        MenuItem item00 = new MenuItem("Add Department");
        MenuItem editDep = new MenuItem("Edit Department");
        MenuItem delDep = new MenuItem("Delete Department");
        MenuItem item001 = new MenuItem("Add Instructor");
        MenuItem editInst = new MenuItem("Edit Instructor");
        MenuItem delInst = new MenuItem("Delete Instructor");
        MenuItem item01 = new MenuItem("Add Course");
        MenuItem item02 = new MenuItem("Edit Course");
        MenuItem item03 = new MenuItem("Search Course");
        MenuItem addEnroll = new MenuItem("Add Enrollment");
        MenuItem editEnroll = new MenuItem("Edit Enrollment");
        MenuItem addGrade = new MenuItem("Add Grade by Course");
        MenuItem addGradeS = new MenuItem("Add by Grade by Student");
        MenuItem viewReport = new MenuItem("View Report");
        MenuButton reports = new MenuButton("Reports", null, viewReport);
        MenuButton grades = new MenuButton("Grades", null, addGrade, addGradeS);
        MenuButton enrollmentMenu = new MenuButton("Enrollment", null, addEnroll, editEnroll);
        MenuButton studentMenu = new MenuButton("Students", null, item1, item2, item3);
        MenuButton profMenu = new MenuButton("College", null, item00, editDep, delDep, item001, editInst, delInst,
                item01, item03, item02);
        text2.setText("WELCOME TO CAMPUS INTERFACE, PLEASE SELECT");
        text2.setX(50);
        text2.setY(100);
        profMenu.setLayoutX(100);
        profMenu.setLayoutY(200);
        studentMenu.setLayoutX(200);
        studentMenu.setLayoutY(200);
        enrollmentMenu.setLayoutX(100);
        enrollmentMenu.setLayoutY(250);
        grades.setLayoutX(200);
        grades.setLayoutY(250);
        reports.setLayoutX(150);
        reports.setLayoutY(300);
        root.getChildren().add(studentMenu);
        root.getChildren().add(profMenu);
        root.getChildren().add(text2);
        root.getChildren().add(enrollmentMenu);
        root.getChildren().add(grades);
        root.getChildren().add(reports);
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);
        primaryStage.setResizable(false);

        primaryStage.setScene(scence);
        primaryStage.show();

        /**
         * COURSES MENU
         */
        // COURSES MENU
        item01.setOnAction(e -> {
            Stage stage = new Stage();
            Group r1 = new Group();
            Scene s1 = new Scene(r1);
            stage.setTitle("Campus Utility: Adding Course");
            Label l1 = new Label("Course ID");
            Label l2 = new Label("Course Name");
            Label dept = new Label("Department");
            Label inst = new Label("Instructor");
            TextField f1 = new TextField();
            TextField f2 = new TextField();
            ChoiceBox<String> cBox = new ChoiceBox<>();
            ChoiceBox<String> iBox = new ChoiceBox<>();
            Button addBt = new Button("Add");
            f1.setLayoutX(20);
            f1.setLayoutY(40);
            l1.setLayoutX(20);
            l1.setLayoutY(20);
            l2.setLayoutX(20);
            l2.setLayoutY(70);
            f2.setLayoutX(20);
            f2.setLayoutY(90);
            addBt.setLayoutX(150);
            addBt.setLayoutY(240);
            for (int i = 1; i < departments.size(); i++) {
                Department depNode = departments.getNode(i);
                if (depNode != null) {
                    cBox.getItems().add(depNode.getdName());
                }
                cBox.setOnAction(a -> {
                    iBox.getItems().clear();
                    for (int j = 1; j < instrcutors.size(); j++) {
                        Instructor instNode = instrcutors.getNode(j);
                        if (depNode != null && instNode != null && cBox.getValue() != null
                                && cBox.getValue().equals(instNode.getDep().toString())) {
                            iBox.getItems().add(instNode.getFname());
                        }
                    }
                });
            }
            addBt.setOnAction(x -> {
                try {
                    Course c = new Course();
                    int int1 = Integer.parseInt(f1.getText());
                    String dep = cBox.getValue();
                    String profName = iBox.getValue();
                    c.setcID(int1);
                    c.setcName(f2.getText());
                    c.setDepartment(dep);
                    c.setInstructor(profName);
                    for (int i = 1; i < departments.size(); i++) {
                        Department dp = departments.getNode(i);
                        if (dp != null) {
                            if (cBox.getValue().equals(dp.getdName())) {
                                c.setDepID(dp.getdID());
                            }
                        }
                        for (int j = 1; j < instrcutors.size(); j++) {
                            Instructor in = instrcutors.getNode(j);
                            if (in != null) {
                                if (iBox.getValue().equals(in.getFname())) {
                                    c.setInstID(in.getInstID());
                                }
                            }
                        }
                    }
                    f1.clear();
                    f2.clear();
                    cBox.setValue("");
                    iBox.setValue("");
                    courses.addNode(c);
                    datWriter("course");
                    Label label4 = new Label("Added");
                    r1.getChildren().add(label4);
                    System.out.println(c);
                } catch (NumberFormatException f) {
                    Stage stage2 = new Stage();
                    Group g2 = new Group();
                    Scene s2 = new Scene(g2);
                    Label errorLabel = new Label("Error:\n Please enter a Number for \na Course ID.");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    g2.getChildren().add(okButton);
                    g2.getChildren().add(errorLabel);
                    okButton.setOnAction(a -> {
                        stage2.close();
                    });
                    stage2.setWidth(200);
                    stage2.setHeight(150);
                    stage2.setScene(s2);
                    stage2.setResizable(false);
                    stage2.show();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
            dept.setLayoutX(20);
            dept.setLayoutY(130);
            inst.setLayoutX(160);
            inst.setLayoutY(130);
            cBox.setLayoutX(20);
            cBox.setLayoutY(160);
            iBox.setLayoutX(160);
            iBox.setLayoutY(160);
            stage.setWidth(420);
            stage.setHeight(420);
            r1.getChildren().add(f1);
            r1.getChildren().add(l1);
            r1.getChildren().add(l2);
            r1.getChildren().add(f2);
            r1.getChildren().add(dept);
            r1.getChildren().add(cBox);
            r1.getChildren().add(iBox);
            r1.getChildren().add(inst);
            r1.getChildren().add(addBt);
            stage.setScene(s1);
            stage.setResizable(false);
            stage.show();
        });
        // Edit
        item02.setOnAction(e -> {
            Stage stage = new Stage();
            Group r2 = new Group();
            Scene s2 = new Scene(r2);
            stage.setTitle("Capmus Utility: Edit Course");
            Label label = new Label("Course ID");
            TextField tf = new TextField();
            Button bt = new Button("Search");
            Label label2 = new Label("Course Name");
            TextField tf2 = new TextField();
            ChoiceBox<String> deptBox = new ChoiceBox<>();
            ChoiceBox<String> instBox = new ChoiceBox<>();

            Button bt2 = new Button("Update");
            label2.setLayoutX(20);
            label2.setLayoutY(140);
            tf2.setLayoutX(20);
            tf2.setLayoutY(160);
            bt.setLayoutX(20);
            bt.setLayoutY(85);
            deptBox.setLayoutX(20);
            deptBox.setLayoutY(200);
            instBox.setLayoutX(20);
            instBox.setLayoutY(240);
            bt2.setLayoutX(150);
            bt2.setLayoutY(270);
            label.setLayoutX(20);
            label.setLayoutY(20);
            tf.setLayoutX(20);
            tf.setLayoutY(50);
            for (int i = 1; i < departments.size(); i++) {
                Department depNode = departments.getNode(i);
                if (depNode != null) {
                    deptBox.getItems().add(depNode.getdName());
                }
                deptBox.setOnAction(a -> {
                    for (int j = 1; j < instrcutors.size(); j++) {
                        Instructor instNode = instrcutors.getNode(j);
                        if (depNode != null && instNode != null && instBox.getValue() != null
                                && deptBox.getValue().contains(instNode.getDep())) {
                            instBox.getItems().add(instNode.getFname());

                        }
                    }
                });
            }
            bt.setOnAction(x -> {
                tf.setEditable(false);
                tf.setDisable(true);
                for (int j = 1; j < courses.size(); j++) {
                    Course course = courses.getNode(j);
                    int csID = Integer.parseInt(tf.getText());
                    if (course != null && deptBox != null) {
                        if (csID == course.getcID()) {
                            tf2.setText(course.getcName());
                            deptBox.setValue(course.getDepartment());
                            instBox.setValue(course.getInstructor());
                        }

                    }
                    bt2.setOnAction(z -> {
                        String dep = deptBox.getValue();
                        String inst = instBox.getValue();
                        course.setcName(tf2.getText());
                        course.setDepartment(dep);
                        course.setInstructor(inst);
                        for (int i = 0; i < departments.size(); i++) {
                            Department dp = departments.getNode(i);
                            if (dp != null) {
                                if (deptBox.getValue().equals(dp.getdName())) {
                                    course.setDepID(dp.getdID());
                                }
                            }
                        }
                        // This is the course edit
                        for (int i = 1; i < enrollment.size(); i++) {
                            Enrollment er = enrollment.getNode(i);
                            if (er.getCID() == course.getcID()) {
                                er.setCourseName(course.getcName());
                            }
                        }
                        tf.setEditable(true);
                        tf.setDisable(false);
                        tf.clear();
                        tf2.clear();
                        deptBox.setValue("");
                        instBox.setValue("");
                        try {
                            datWriter("course");
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    });
                }
            });

            r2.getChildren().add(tf);
            r2.getChildren().add(label);
            r2.getChildren().add(label2);
            r2.getChildren().add(tf2);
            r2.getChildren().add(deptBox);
            r2.getChildren().add(instBox);
            r2.getChildren().add(bt);
            r2.getChildren().add(bt2);
            stage.setWidth(420);
            stage.setHeight(420);
            stage.setScene(s2);
            stage.setResizable(false);
            stage.show();
        });
        // Course Search
        item03.setOnAction(e ->

        {
            Stage stage = new Stage();
            Group r1 = new Group();
            Scene s1 = new Scene(r1);
            stage.setTitle("Campus Utility: Search Course");
            Label courseID = new Label("Course ID");
            Label courseName = new Label("Course Name");
            Label courseProfessor = new Label("Instructor");
            Label courseDepartment = new Label("Department");
            TextField courseIDField = new TextField();
            TextField courseNameField = new TextField();
            TextField profNameField = new TextField();
            TextField departField = new TextField();
            Button search = new Button("Search");
            Button clear = new Button("Clear");
            courseID.setLayoutX(20);
            courseID.setLayoutY(20);
            courseIDField.setLayoutX(75);
            courseIDField.setLayoutY(15);
            courseIDField.setPrefWidth(60);
            search.setLayoutX(150);
            search.setLayoutY(15);
            courseName.setLayoutX(20);
            courseName.setLayoutY(100);
            courseNameField.setLayoutX(20);
            courseNameField.setLayoutY(120);
            courseProfessor.setLayoutX(20);
            courseProfessor.setLayoutY(150);
            profNameField.setLayoutX(20);
            profNameField.setLayoutY(170);
            courseDepartment.setLayoutX(20);
            courseDepartment.setLayoutY(200);
            departField.setLayoutX(20);
            departField.setLayoutY(220);
            clear.setLayoutX(200);
            clear.setLayoutY(250);
            search.setOnAction(x -> {
                courseNameField.setEditable(false);
                profNameField.setEditable(false);
                departField.setEditable(false);
                for (int i = 1; i < courses.size(); i++) {
                    Course course = courses.getNode(i);
                    int csID = Integer.parseInt(courseIDField.getText());
                    if (course != null) {
                        if (csID == course.getcID()) {
                            courseNameField.setText(course.getcName());
                            profNameField.setText(course.getInstructor());
                            departField.setText(course.getDepartment());
                        }
                    }
                }
            });
            clear.setOnAction(z -> {
                courseIDField.clear();
                courseNameField.clear();
                profNameField.clear();
                departField.clear();
            });
            r1.getChildren().add(courseID);
            r1.getChildren().add(courseIDField);
            r1.getChildren().add(search);
            r1.getChildren().add(courseName);
            r1.getChildren().add(courseNameField);
            r1.getChildren().add(courseProfessor);
            r1.getChildren().add(profNameField);
            r1.getChildren().add(courseDepartment);
            r1.getChildren().add(departField);
            r1.getChildren().add(clear);
            stage.setWidth(420);
            stage.setHeight(420);
            stage.setScene(s1);
            stage.setResizable(false);
            stage.show();
        });

        // ADD DEPARTMENT
        item00.setOnAction(e -> {
            Stage stage = new Stage();
            Group g = new Group();
            Scene s = new Scene(g);
            stage.setTitle("Campus Utility: Department");
            Label label = new Label("Department ID");
            TextField tf = new TextField();
            Label label1 = new Label("Department Name");
            TextField tf2 = new TextField();
            Button addBt = new Button("Create Department");
            label.setLayoutX(20);
            label.setLayoutY(40);
            tf.setLayoutX(20);
            tf.setLayoutY(60);
            label1.setLayoutX(20);
            label1.setLayoutY(90);
            tf2.setLayoutX(20);
            tf2.setLayoutY(120);
            addBt.setLayoutX(60);
            addBt.setLayoutY(160);
            addBt.setOnAction(x -> {
                if (tf.getText().isEmpty()) {
                    Stage stage1 = new Stage();
                    Group g1 = new Group();
                    Scene s1 = new Scene(g1);
                    Label errorLabel = new Label("Error:\n Please enter ID for Department.");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    g1.getChildren().add(okButton);
                    g1.getChildren().add(errorLabel);
                    okButton.setOnAction(a -> {
                        stage1.close();
                    });
                    stage1.setWidth(200);
                    stage1.setHeight(150);
                    stage1.setScene(s1);
                    stage.setResizable(false);
                    stage1.show();
                } else {
                    try {
                        Department dp = new Department();
                        boolean duplicated = false;
                        int int1 = Integer.parseInt(tf.getText());
                        dp.setcID(int1);
                        for (int b = 1; b < departments.size(); b++) {
                            Department currentDept = departments.getNode(b);
                            if (currentDept != null && int1 == currentDept.getdID()) {
                                duplicated = true;
                                Stage stage2 = new Stage();
                                Group g2 = new Group();
                                Scene s2 = new Scene(g2);
                                Label errorLabel = new Label("Error:\n A department with that \n ID exist already.");
                                Button okButton = new Button("Ok");
                                errorLabel.setLayoutX(10);
                                okButton.setLayoutX(70);
                                okButton.setLayoutY(60);
                                g2.getChildren().add(okButton);
                                g2.getChildren().add(errorLabel);
                                okButton.setOnAction(a -> {
                                    stage2.close();
                                });
                                stage2.setWidth(200);
                                stage2.setHeight(150);
                                stage2.setScene(s2);
                                stage2.setResizable(false);
                                stage2.show();

                            }
                        }
                        if (!duplicated) {
                            dp.setdName(tf2.getText());
                            departments.addNode(dp);
                            datWriter("department");
                            Label label4 = new Label("Added");
                            g.getChildren().add(label4);
                            tf.clear();
                            tf2.clear();
                        }
                    } catch (NumberFormatException f) {
                        Stage stage2 = new Stage();
                        Group g2 = new Group();
                        Scene s2 = new Scene(g2);
                        Label errorLabel = new Label("Error:\n Please enter a Number for \na Department ID.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        g2.getChildren().add(okButton);
                        g2.getChildren().add(errorLabel);
                        okButton.setOnAction(a -> {
                            stage2.close();
                        });
                        stage2.setWidth(200);
                        stage2.setHeight(150);
                        stage2.setScene(s2);
                        stage2.setResizable(false);
                        stage2.show();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            });
            stage.setWidth(420);
            stage.setHeight(420);
            g.getChildren().addAll(label, label1, tf, tf2, addBt);
            stage.setScene(s);
            stage.setResizable(false);
            stage.show();

        });

        // EDIT DEPARTMENT
        editDep.setOnAction(e -> {
            Stage stage = new Stage();
            Group g = new Group();
            Scene s = new Scene(g);
            stage.setTitle("Campus Utility: Editing Department");
            Label label = new Label("Department ID");
            TextField tf = new TextField();
            Button bt = new Button("Search");
            Label label2 = new Label("Department Name");
            TextField tf2 = new TextField();
            Button updateBt = new Button("Update");
            label.setLayoutX(20);
            label.setLayoutY(20);
            label2.setLayoutX(20);
            label2.setLayoutY(80);
            tf.setLayoutX(20);
            tf.setLayoutY(40);
            tf2.setLayoutX(20);
            tf2.setLayoutY(110);
            bt.setLayoutX(180);
            bt.setLayoutY(40);
            updateBt.setLayoutX(180);
            updateBt.setLayoutY(180);
            g.getChildren().add(label);
            g.getChildren().add(label2);
            g.getChildren().add(tf2);
            g.getChildren().add(tf);
            g.getChildren().add(bt);
            g.getChildren().add(updateBt);
            bt.setOnAction(x -> {
                for (int i = 1; i < departments.size(); i++) {
                    Department dp = departments.getNode(i);
                    int dID = Integer.parseInt(tf.getText());
                    if (dp != null) {
                        if (dID == dp.getdID()) {
                            tf2.setText(dp.getdName());
                        }
                        System.out.println("test");
                    }
                    updateBt.setOnAction(y -> {
                        dp.setcID(dID);
                        dp.setdName(tf2.getText());
                        // This is the edit for department / course
                        for (int j = 1; j < courses.size(); j++) {
                            Course c = courses.getNode(j);
                            if (c.getDepID() == dp.getdID()) {
                                c.setDepartment(dp.getdName());
                            }
                        }
                        // This is the edit for department / instuctor
                        for (int k = 1; k < instrcutors.size(); k++) {
                            Instructor in = instrcutors.getNode(k);
                            if (in.getDepID() == dp.getdID()) {
                                in.setDep(dp.getdName());
                            }
                        }
                        for (int a = 1; a < enrollment.size(); a++) {
                            Enrollment er = enrollment.getNode(a);
                            if (er.getDepID() == dp.getdID()) {
                                er.setDep(dp.getdName());
                            }
                        }
                        tf.clear();
                        tf2.clear();
                        try {
                            datWriter("department");
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    });
                }
            });
            stage.setHeight(420);
            stage.setWidth(420);
            stage.setScene(s);
            stage.setResizable(false);
            stage.show();

        });
        // Delete Department
        delDep.setOnAction(e -> {
            Stage stage = new Stage();
            Group r2 = new Group();
            Scene s2 = new Scene(r2);
            stage.setTitle("Capmus Utility: Delete Department");
            Label label = new Label("Department ID");
            TextField tf = new TextField();
            Button bt = new Button("Search");
            Label label2 = new Label("Department Name");
            TextField tf2 = new TextField();
            Button removeBT = new Button("Remove");
            label.setLayoutX(20);
            label.setLayoutY(20);
            label2.setLayoutX(20);
            label2.setLayoutY(80);
            tf.setLayoutX(20);
            tf.setLayoutY(40);
            tf2.setLayoutX(20);
            tf2.setLayoutY(110);
            bt.setLayoutX(180);
            bt.setLayoutY(40);
            removeBT.setLayoutX(180);
            removeBT.setLayoutY(180);
            bt.setOnAction(x -> {
                for (int i = 1; i < departments.size(); i++) {
                    Department dp = departments.getNode(i);
                    int dID = Integer.parseInt(tf.getText());
                    if (dp != null) {
                        if (dID == dp.getdID()) {
                            tf2.setText(dp.getdName());
                        }
                    }
                }
            });
            removeBT.setOnAction(z -> {
                int dID = Integer.parseInt(tf.getText());
                Department dp = null;
                for (int w = 1; w < departments.size(); w++) {
                    if (departments.getNode(w) != null && departments.getNode(w).getdID() == dID) {
                        dp = departments.getNode(w);

                    }

                }

                for (int i = 1; i < instrcutors.size(); i++) {
                    if (instrcutors.getNode(i).getDepID() == dID) {
                        int instID = instrcutors.getNode(i).getDepID();
                        for (int c = 1; c < courses.size(); c++) {
                            if (courses.getNode(c).getInstID() == instID) {
                                courses.deleteNode(courses.getNode(c));
                            }
                        }
                        instrcutors.deleteNode(instrcutors.getNode(i));
                    }
                }

                departments.deleteNode(dp);
                try {
                    datWriter("department");
                    datWriter("instructor");
                    datWriter("course");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                tf.clear();
                tf2.clear();

            });
            r2.getChildren().add(label);
            r2.getChildren().add(tf);
            r2.getChildren().add(bt);
            r2.getChildren().add(label2);
            r2.getChildren().add(tf2);
            r2.getChildren().add(removeBT);
            stage.setWidth(420);
            stage.setHeight(420);
            stage.setScene(s2);
            stage.setResizable(false);
            stage.show();
        });

        // ADDING PROFESSORS
        item001.setOnAction(e -> {
            Stage stage = new Stage();
            Group g = new Group();
            Scene s = new Scene(g);
            stage.setTitle("Campus Utlity: Professor");
            Label label = new Label("First Name");
            TextField tf = new TextField(); // First Name
            Label label2 = new Label("Professor ID");
            TextField tf2 = new TextField(); // Last Name
            Label label1 = new Label("Last Name");
            TextField tf3 = new TextField(); // Professor ID
            Button addBt = new Button("Add Professor");
            Department node;
            int count = 0;
            ChoiceBox<String> cBox = new ChoiceBox<>();

            for (int i = 1; i < departments.size(); i++) {
                node = departments.getNode(i);
                if (node != null) {
                    cBox.getItems().add(node.getdName());
                }
            }

            addBt.setOnAction(x -> {
                try {
                    Instructor ins = new Instructor();
                    boolean duplicated = false;
                    String dep = cBox.getValue();
                    int int1 = Integer.parseInt(tf3.getText());
                    ins.setInstID(int1);
                    for (int b = 1; b < instrcutors.size(); b++) {
                        Instructor currentinst = instrcutors.getNode(b);
                        if (currentinst != null && int1 == currentinst.getInstID()) {
                            duplicated = true;
                            Stage stage2 = new Stage();
                            Group g2 = new Group();
                            Scene s2 = new Scene(g2);
                            Label errorLabel = new Label("Error:\n A professor with that \n ID exist already.");
                            Button okButton = new Button("Ok");
                            errorLabel.setLayoutX(10);
                            okButton.setLayoutX(70);
                            okButton.setLayoutY(60);
                            g2.getChildren().add(okButton);
                            g2.getChildren().add(errorLabel);
                            okButton.setOnAction(a -> {
                                stage2.close();
                            });
                            stage2.setWidth(200);
                            stage2.setHeight(150);
                            stage2.setScene(s2);
                            stage2.setResizable(false);
                            stage2.show();
                        }
                    }
                    if (!duplicated) {
                        ins.setFname(tf.getText());
                        ins.setLname(tf2.getText());
                        ins.setDep(dep);
                        for (int i = 0; i < departments.size(); i++) {
                            Department dp = departments.getNode(i);
                            if (dp != null) {
                                if (cBox.getValue().equals(dp.getdName())) {
                                    ins.setDepID(dp.getdID());
                                }
                            }
                        }
                        instrcutors.addNode(ins);
                        datWriter("instructor");
                        System.out.println(ins);
                        tf.clear();
                        tf2.clear();
                        tf3.clear();
                        cBox.setValue("");
                    }
                } catch (NumberFormatException f) {
                    Stage stage2 = new Stage();
                    Group g2 = new Group();
                    Scene s2 = new Scene(g2);
                    Label errorLabel = new Label("Error:\n Please enter a Number for \na Professor ID.");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    g2.getChildren().add(okButton);
                    g2.getChildren().add(errorLabel);
                    okButton.setOnAction(a -> {
                        stage2.close();
                    });
                    stage2.setWidth(200);
                    stage2.setHeight(150);
                    stage2.setScene(s2);
                    stage2.setResizable(false);
                    stage2.show();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
            label.setLayoutX(20);
            label.setLayoutY(40);
            tf.setLayoutX(20);
            tf.setLayoutY(60);
            label1.setLayoutX(20);
            label1.setLayoutY(90);
            tf2.setLayoutX(20);
            tf2.setLayoutY(120);
            label2.setLayoutX(20);
            label2.setLayoutY(150);
            tf3.setLayoutX(20);
            tf3.setLayoutY(170);
            cBox.setLayoutX(20);
            cBox.setLayoutY(210);
            addBt.setLayoutX(180);
            addBt.setLayoutY(280);
            stage.setWidth(420);
            stage.setHeight(420);
            g.getChildren().addAll(label, label1, label2, tf, tf2, tf3, cBox, addBt);
            stage.setScene(s);
            stage.setResizable(false);
            stage.show();
        });
        // EDIT INSTRUCTOR
        editInst.setOnAction(e -> {
            Stage stage = new Stage();
            Group g = new Group();
            Scene s = new Scene(g);
            stage.setTitle("Campus Utility: Edit Professor");
            Label label = new Label("Instructor ID");
            Label label2 = new Label("First Name");
            Label label3 = new Label("Last Name");
            Label depName = new Label("Department");
            TextField instID = new TextField();
            TextField instFName = new TextField();
            TextField instLName = new TextField();
            Button bt = new Button("Search");
            ChoiceBox<String> cBox = new ChoiceBox<>();
            Button bt2 = new Button("Update");
            label.setLayoutX(20);
            label.setLayoutY(20);
            label2.setLayoutX(20);
            label2.setLayoutY(100);
            label3.setLayoutX(20);
            label3.setLayoutY(150);
            instID.setLayoutX(20);
            instID.setLayoutY(40);
            instFName.setLayoutX(20);
            instFName.setLayoutY(120);
            instLName.setLayoutX(20);
            instLName.setLayoutY(170);
            bt.setLayoutX(200);
            bt.setLayoutY(40);
            depName.setLayoutX(20);
            depName.setLayoutY(200);
            cBox.setLayoutX(20);
            cBox.setLayoutY(220);
            bt2.setLayoutX(180);
            bt2.setLayoutY(250);
            g.getChildren().add(label);
            g.getChildren().add(cBox);
            g.getChildren().add(instID);
            g.getChildren().add(instFName);
            g.getChildren().add(instLName);
            g.getChildren().add(bt);
            g.getChildren().add(label2);
            g.getChildren().add(label3);
            g.getChildren().add(bt2);
            g.getChildren().add(depName);
            Department node;
            for (int i = 1; i < departments.size(); i++) {
                node = departments.getNode(i);
                if (node != null) {
                    cBox.getItems().add(node.getdName());
                }
            }
            bt.setOnAction(x -> {
                instID.setEditable(false);
                instID.setDisable(true);
                for (int i = 1; i < instrcutors.size(); i++) {
                    Instructor inst = instrcutors.getNode(i);
                    int pID = Integer.parseInt(instID.getText());
                    if (inst != null) {
                        if (pID == inst.getInstID()) {
                            instFName.setText(inst.getFname());
                            instLName.setText(inst.getLname());
                            if (cBox.getItems().contains(inst.getDep())) {
                                cBox.setValue(inst.getDep());
                            }
                        }
                    }
                    bt2.setOnAction(y -> {
                        instID.setEditable(true);
                        instID.setDisable(false);
                        inst.setInstID(pID);
                        inst.setFname(instFName.getText());
                        inst.setLname(instLName.getText());
                        inst.setDep(cBox.getValue());
                        for (int j = 0; j < departments.size(); j++) {
                            Department dp = departments.getNode(j);
                            if (dp != null) {
                                if (cBox.getValue().equals(dp.getdName())) {
                                    inst.setDepID(dp.getdID());
                                }
                            }
                        }
                        for (int k = 1; k < enrollment.size(); k++) {
                            Enrollment er = enrollment.getNode(k);

                            // Edit for instructor / enrollment
                            if (er.getInstructorID() == inst.getInstID()) { // This needs to be looked at.
                                er.setProfName(inst.getFname() + " " + inst.getLname());
                            }
                        }
                        for (int b = 1; b < courses.size(); b++) {
                            Course c = courses.getNode(b);
                            if (c.getInstID() == inst.getInstID()) {
                                c.setInstructor(inst.getFname() + " " + inst.getLname());
                            }
                        }
                        System.out.println(inst);
                        try {
                            datWriter("instructor");
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    });
                }

            });

            stage.setHeight(420);
            stage.setWidth(420);
            stage.setScene(s);
            stage.setResizable(false);
            stage.show();

        });
        delInst.setOnAction(e -> {
            Stage stage = new Stage();
            Group group = new Group();
            Scene sceneOne = new Scene(group);
            stage.setTitle("Campus Utility: Delete Instructor");
            Label instID = new Label("Instructor ID");
            TextField instIDInput = new TextField();
            Button searchButton = new Button("Search");
            Label profName = new Label("First Name");
            Label prolName = new Label("Last Name");
            Label depName = new Label("Department");
            TextField firstName = new TextField();
            TextField lastName = new TextField();
            TextField deptName = new TextField();
            Button deleteButton = new Button("Delete");
            instID.setLayoutX(20);
            instID.setLayoutY(20);
            instIDInput.setLayoutX(20);
            instIDInput.setLayoutY(40);
            searchButton.setLayoutX(180);
            searchButton.setLayoutY(40);
            profName.setLayoutX(20);
            profName.setLayoutY(100);
            firstName.setLayoutX(20);
            firstName.setLayoutY(120);
            prolName.setLayoutX(20);
            prolName.setLayoutY(150);
            lastName.setLayoutX(20);
            lastName.setLayoutY(170);
            depName.setLayoutX(20);
            depName.setLayoutY(195);
            deptName.setLayoutX(20);
            deptName.setLayoutY(220);
            deleteButton.setLayoutX(180);
            deleteButton.setLayoutY(250);
            group.getChildren().add(instID);
            group.getChildren().add(instIDInput);
            group.getChildren().add(searchButton);
            group.getChildren().add(profName);
            group.getChildren().add(firstName);
            group.getChildren().add(prolName);
            group.getChildren().add(lastName);
            group.getChildren().add(depName);
            group.getChildren().add(deptName);
            group.getChildren().add(deleteButton);
            searchButton.setOnAction(y -> {
                firstName.clear();
                lastName.clear();
                deptName.clear();
                for (int i = 1; i < instrcutors.size(); i++) {
                    Instructor inst = instrcutors.getNode(i);
                    int pID = Integer.parseInt(instIDInput.getText());
                    if (inst != null) {
                        if (pID == inst.getInstID()) {
                            firstName.setText(inst.getFname());
                            lastName.setText(inst.getLname());
                            deptName.setText(inst.getDep());
                        }
                    }
                }
            });
            deleteButton.setOnAction(z -> {
                int pID = Integer.parseInt(instIDInput.getText());
                Instructor inst = null;
                for (int q = 1; q < instrcutors.size(); q++) {
                    if (instrcutors.getNode(q) != null && instrcutors.getNode(q).getInstID() == pID) {
                        inst = instrcutors.getNode(q);
                    }
                }

                for (int c = 1; c < courses.size(); c++) {
                    if (courses.getNode(c).getInstID() == inst.getInstID()) {
                        courses.deleteNode(courses.getNode(c));
                    }
                }
                instrcutors.deleteNode(inst);
                try {
                    datWriter("instructor");
                    datWriter("course");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                firstName.clear();
                lastName.clear();
                deptName.clear();
            });
            stage.setWidth(420);
            stage.setHeight(420);
            stage.setScene(sceneOne);
            stage.setResizable(false);
            stage.show();
        });
        /**
         * STUDENTS MENU
         */
        // Searching Student
        item1.setOnAction(e -> {
            Stage stage = new Stage();
            Group r1 = new Group();
            Scene s1 = new Scene(r1);
            stage.setTitle("Campus Utility: Searching Student");
            Label l1 = new Label("First Name");
            Label l2 = new Label("Last Name");
            Label l3 = new Label("Student ID");
            Label l4 = new Label("Address");
            TextField f1 = new TextField();
            TextField f2 = new TextField();
            TextField f3 = new TextField();
            TextField f4 = new TextField();
            Button addBt = new Button("Search");
            f1.setEditable(false);
            f2.setEditable(false);
            f4.setEditable(false);
            f1.setLayoutX(20);
            f1.setLayoutY(100);
            f2.setLayoutX(20);
            f2.setLayoutY(150);
            f3.setLayoutX(20);
            f3.setLayoutY(40);
            l1.setLayoutX(20);
            l1.setLayoutY(80);
            l2.setLayoutX(20);
            l2.setLayoutY(130);
            l3.setLayoutX(20);
            l3.setLayoutY(20);
            l4.setLayoutX(20);
            l4.setLayoutY(180);
            f4.setLayoutX(20);
            f4.setLayoutY(200);
            addBt.setLayoutX(200);
            addBt.setOnAction(x -> {
                for (int i = 1; i < students.size(); i++) {
                    Student st = students.getNode(i);
                    int int1 = Integer.parseInt(f3.getText());
                    if (int1 == st.getsID()) {
                        f1.setText(st.getFirstName());
                        f2.setText(st.getLastName());
                        f4.setText(st.getAddress());
                    }
                }
            });
            addBt.setLayoutY(40);
            stage.setWidth(420);
            stage.setHeight(420);
            r1.getChildren().add(f1);
            r1.getChildren().add(f2);
            r1.getChildren().add(f3);
            r1.getChildren().add(l1);
            r1.getChildren().add(l2);
            r1.getChildren().add(l3);
            r1.getChildren().add(l4);
            r1.getChildren().add(f4);
            r1.getChildren().add(addBt);
            stage.setScene(s1);
            stage.setResizable(false);
            stage.show();
        });
        // ADDING STUDENT
        item2.setOnAction(e -> {
            Stage stage = new Stage();
            Group r1 = new Group();
            Scene s1 = new Scene(r1);
            stage.setTitle("Campus Utility: Adding Student");
            Label studentID = new Label("Student ID");
            Label l2 = new Label("First Name");
            Label l3 = new Label("Last Name");
            Label l4 = new Label("Address");
            TextField f1 = new TextField();
            TextField f2 = new TextField();
            TextField f3 = new TextField();
            TextField f4 = new TextField();
            Button addBt = new Button("Add");
            f1.setLayoutX(20);
            f1.setLayoutY(40);
            f2.setLayoutX(20);
            f2.setLayoutY(90);
            f3.setLayoutX(20);
            f3.setLayoutY(140);
            f4.setLayoutX(20);
            f4.setLayoutY(200);
            studentID.setLayoutX(20);
            studentID.setLayoutY(20);
            l2.setLayoutX(20);
            l2.setLayoutY(70);
            l3.setLayoutX(20);
            l3.setLayoutY(120);
            l4.setLayoutX(20);
            l4.setLayoutY(170);
            addBt.setLayoutX(200);
            addBt.setLayoutY(250);
            addBt.setOnAction(x -> {
                if (f1.getText().isEmpty()) {
                    Stage stage1 = new Stage();
                    Group g = new Group();
                    Scene s = new Scene(g);
                    Label errorLabel = new Label("Error:\n Please enter ID for student.");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    g.getChildren().add(okButton);
                    g.getChildren().add(errorLabel);
                    okButton.setOnAction(a -> {
                        stage1.close();
                    });
                    stage1.setWidth(200);
                    stage1.setHeight(150);
                    stage1.setScene(s);
                    stage.setResizable(false);
                    stage1.show();
                } else {
                    try {
                        Student st1 = new Student();
                        boolean duplicated = false;
                        int int1 = Integer.parseInt(f1.getText());
                        st1.setsID(int1);
                        for (int b = 1; b < students.size(); b++) {
                            Student currentStudent = students.getNode(b);
                            if (currentStudent != null && int1 == currentStudent.getsID()) {
                                duplicated = true;
                                Stage stage2 = new Stage();
                                Group g2 = new Group();
                                Scene s2 = new Scene(g2);
                                Label errorLabel = new Label("Error:\n A student with that \n ID exist already.");
                                Button okButton = new Button("Ok");
                                errorLabel.setLayoutX(10);
                                okButton.setLayoutX(70);
                                okButton.setLayoutY(60);
                                g2.getChildren().add(okButton);
                                g2.getChildren().add(errorLabel);
                                okButton.setOnAction(a -> {
                                    stage2.close();
                                });
                                stage2.setWidth(200);
                                stage2.setHeight(150);
                                stage2.setScene(s2);
                                stage2.setResizable(false);
                                stage2.show();
                            }
                        }
                        if (!duplicated) {
                            st1.setFirstName(f2.getText());
                            st1.setLastName(f3.getText());
                            st1.setAddress(f4.getText());
                            students.addNode(st1);
                            datWriter("student");
                            System.out.println(st1);
                            f1.clear();
                            f2.clear();
                            f3.clear();
                            f4.clear();
                        }

                    } catch (NumberFormatException f) {
                        Stage stage1 = new Stage();
                        Group g = new Group();
                        Scene s = new Scene(g);
                        Label errorLabel = new Label("Error:\n Please enter a Number for \na student ID.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        g.getChildren().add(okButton);
                        g.getChildren().add(errorLabel);
                        okButton.setOnAction(a -> {
                            stage1.close();
                        });
                        stage1.setWidth(200);
                        stage1.setHeight(150);
                        stage1.setScene(s);
                        stage.setResizable(false);
                        stage1.show();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                }

            });

            stage.setWidth(420);
            stage.setHeight(420);
            r1.getChildren().add(f1);
            r1.getChildren().add(f2);
            r1.getChildren().add(f3);
            r1.getChildren().add(studentID);
            r1.getChildren().add(l2);
            r1.getChildren().add(l3);
            r1.getChildren().add(l4);
            r1.getChildren().add(f4);
            r1.getChildren().add(addBt);
            stage.setScene(s1);
            stage.setResizable(false);
            stage.show();
        });

        // EDITING STUDENT
        item3.setOnAction(e ->

        {
            Stage stage = new Stage();
            Group r1 = new Group();
            Scene s1 = new Scene(r1);
            stage.setTitle("Campus Utility: Editing Student)");
            Label l1 = new Label("First Name");
            Label l2 = new Label("Last Number");
            Label l3 = new Label("Address");
            Label l4 = new Label("Student ID");
            TextField firstName = new TextField();
            TextField lastName = new TextField();
            TextField address = new TextField();
            TextField studentID = new TextField();
            Button addBt = new Button("Update");
            Button sBt = new Button("Search");
            firstName.setLayoutX(20);
            firstName.setLayoutY(140);
            lastName.setLayoutX(20);
            lastName.setLayoutY(190);
            address.setLayoutX(20);
            address.setLayoutY(240);
            studentID.setLayoutX(20);
            studentID.setLayoutY(40);
            l1.setLayoutX(20);
            l1.setLayoutY(120);
            l2.setLayoutX(20);
            l2.setLayoutY(170);
            l3.setLayoutX(20);
            l3.setLayoutY(220);
            l4.setLayoutX(20);
            l4.setLayoutY(20);
            addBt.setLayoutX(200);
            addBt.setLayoutY(280);
            sBt.setLayoutX(20);
            sBt.setLayoutY(70);
            stage.setWidth(420);
            stage.setHeight(420);
            sBt.setOnAction(x -> {
                for (int i = 1; i < students.size(); i++) {
                    Student st = students.getNode(i);
                    int parser = Integer.parseInt(studentID.getText());
                    if (parser == st.getsID()) {
                        firstName.setText(st.getFirstName());
                        lastName.setText(st.getLastName());
                        address.setText(st.getAddress());
                    }
                    addBt.setOnAction(y -> {
                        st.setsID(parser);
                        st.setFirstName(firstName.getText());
                        st.setLastName(lastName.getText());
                        st.setAddress(address.getText());
                        // This is the edit for student
                        for (int j = 1; j < enrollment.size(); j++) {
                            Enrollment er = enrollment.getNode(j);
                            if (er.getSID() == st.getsID()) {
                                er.setStudentName(st.getFirstName() + " " + st.getLastName());
                            }
                        }
                        try {
                            datWriter("student");
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        System.out.println(st);

                    });
                }
            });

            r1.getChildren().add(firstName);
            r1.getChildren().add(lastName);
            r1.getChildren().add(address);
            r1.getChildren().add(l1);
            r1.getChildren().add(l2);
            r1.getChildren().add(l3);
            r1.getChildren().add(l4);
            r1.getChildren().add(studentID);
            r1.getChildren().add(addBt);
            r1.getChildren().add(sBt);
            stage.setScene(s1);
            stage.setResizable(false);
            stage.show();
        });

        /**
         * ENROLLMENT MENU
         */
        // ENROLLMENT
        addEnroll.setOnAction(e -> {
            Stage stage = new Stage();
            Group g = new Group();
            Scene s = new Scene(g);
            stage.setTitle("Campus Utility: Adding Enrollment");
            TextField tf1 = new TextField("Student ID");
            TextField tf2 = new TextField("Student Name");
            TextField tf3 = new TextField("Course ID");
            TextField tf4 = new TextField("Course Name");
            Button bt2 = new Button("Find Course");
            Button bt = new Button("Find Student");
            ChoiceBox<String> semBox = new ChoiceBox<>();
            ChoiceBox<String> Year = new ChoiceBox<>();
            semBox.getItems().add("Spring");
            semBox.getItems().add("Summer");
            semBox.getItems().add("Fall");
            semBox.getItems().add("Winter");
            Year.getItems().add("2015");
            Year.getItems().add("2016");
            Year.getItems().add("2017");
            Year.getItems().add("2018");
            Year.getItems().add("2019");
            Year.getItems().add("2020");
            Year.getItems().add("2021");
            Year.setLayoutX(120);
            Year.setLayoutY(220);
            semBox.setLayoutX(20);
            semBox.setLayoutY(220);
            Button bt3 = new Button("Enroll");
            tf1.setLayoutX(20);
            tf1.setLayoutY(20);
            tf2.setLayoutX(20);
            tf2.setLayoutY(70);
            tf3.setLayoutX(20);
            tf3.setLayoutY(120);
            tf4.setLayoutX(20);
            tf4.setLayoutY(170);
            bt.setLayoutX(200);
            bt.setLayoutY(20);
            bt2.setLayoutX(200);
            bt2.setLayoutY(120);
            bt3.setLayoutX(200);
            bt3.setLayoutY(250);
            bt.setOnAction(x -> {
                try {
                    for (int i = 1; i < students.size(); i++) {
                        Student st = students.getNode(i);
                        int int1 = Integer.parseInt(tf1.getText());
                        if (st != null) {
                            if (int1 == st.getsID()) {
                                tf2.setText(st.getFirstName());
                            }
                        }
                    }
                } catch (NumberFormatException stE) {
                    Stage stage5 = new Stage();
                    Group g5 = new Group();
                    Scene s5 = new Scene(g5);
                    Label errorLabel = new Label("Error:\n Please enter a Number for \n for studentID");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    g5.getChildren().add(okButton);
                    g5.getChildren().add(errorLabel);
                    okButton.setOnAction(a -> {
                        stage5.close();
                    });
                    stage5.setWidth(200);
                    stage5.setHeight(150);
                    stage5.setScene(s5);
                    stage5.setResizable(false);
                    stage5.show();
                }
            });
            bt2.setOnAction(x -> {
                try {
                    for (int i = 1; i < courses.size(); i++) {
                        Course course = courses.getNode(i);
                        int csID = Integer.parseInt(tf3.getText());
                        if (course != null) {
                            if (csID == course.getcID()) {
                                tf4.setText(course.getcName());
                            }
                        }
                    }
                } catch (NumberFormatException cE) {
                    Stage stage5 = new Stage();
                    Group g5 = new Group();
                    Scene s5 = new Scene(g5);
                    Label errorLabel = new Label("Error:\n Please enter a Number for \n for course ID");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    g5.getChildren().add(okButton);
                    g5.getChildren().add(errorLabel);
                    okButton.setOnAction(a -> {
                        stage5.close();
                    });
                    stage5.setWidth(200);
                    stage5.setHeight(150);
                    stage5.setScene(s5);
                    stage5.setResizable(false);
                    stage5.show();
                }
            });
            bt3.setOnAction(x -> {
                Enrollment enroll = new Enrollment();
                try {
                    boolean dup = false;
                    int sID = Integer.parseInt(tf1.getText());
                    int cID = Integer.parseInt(tf3.getText());
                    enroll.setSID(sID);
                    enroll.setCID(cID);
                    enroll.setSemseter(semBox.getValue());
                    enroll.setYear(Year.getValue());
                    for (int h = 1; h < enrollment.size(); h++) {
                        Enrollment enr = enrollment.getNode(h);
                        if (enr != null && sID == enr.getSID() && cID == enr.getCID()
                                && semBox.getValue().equals(enr.getSemseter()) && Year.getValue().equals(enr.getYear())) {
                            dup = true;
                            Stage stage2 = new Stage();
                            Group g2 = new Group();
                            Scene s2 = new Scene(g2);
                            Label errorLabel = new Label("Error:\n A data structure with those \n IDs exist already.");
                            Button okButton = new Button("Ok");
                            errorLabel.setLayoutX(10);
                            okButton.setLayoutX(70);
                            okButton.setLayoutY(60);
                            g2.getChildren().add(okButton);
                            g2.getChildren().add(errorLabel);
                            okButton.setOnAction(a -> {
                                stage2.close();
                            });
                            stage2.setWidth(200);
                            stage2.setHeight(150);
                            stage2.setScene(s2);
                            stage2.setResizable(false);
                            stage2.show();
                        }
                    }
                    if (!dup) {
                        for (int i = 1; i < students.size(); i++) {
                            Student st = students.getNode(i);
                            int int1 = Integer.parseInt(tf1.getText());
                            if (st != null) {
                                if (int1 == st.getsID()) {
                                    enroll.setStudentName(st.getFirstName() + " " + st.getLastName());
                                }
                            }
                        }
                        for (int i = 1; i < courses.size(); i++) {
                            Course c = courses.getNode(i);
                            int int1 = Integer.parseInt(tf3.getText());
                            if (c != null) {
                                if (int1 == c.getcID()) {
                                    enroll.setCourseName(c.getcName());
                                    enroll.setProfName(c.getInstructor());
                                    // MADE THIS INSERT TO TEST, WILL REMOVE IF WRONG OR NOT JUSTIFIABLE
                                    enroll.setInstructorID(c.getInstID());
                                    enroll.setDep(c.getDepartment());
                                    enroll.setDepID(c.getDepID());
                                }
                            }
                            System.out.println(enroll);

                        }
                        enrollment.addNode(enroll);
                        tf1.clear();
                        tf2.clear();
                        tf3.clear();
                        tf4.clear();
                        semBox.valueProperty().set(null);
                        Year.valueProperty().set(null);
                    }
                } catch (NumberFormatException f) {
                    Stage stage5 = new Stage();
                    Group g5 = new Group();
                    Scene s5 = new Scene(g5);
                    Label errorLabel = new Label("Error:\n Please enter a Number for \n for course ID and studentID");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    g5.getChildren().add(okButton);
                    g5.getChildren().add(errorLabel);
                    okButton.setOnAction(a -> {
                        stage5.close();
                    });
                    stage5.setWidth(200);
                    stage5.setHeight(150);
                    stage5.setScene(s5);
                    stage5.setResizable(false);
                    stage5.show();
                }
                ;
                try {
                    datWriter("enrollment");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

//				System.out.println(enroll);
            });
            g.getChildren().add(tf1);
            g.getChildren().add(tf2);
            g.getChildren().add(tf3);
            g.getChildren().add(tf4);
            g.getChildren().add(bt);
            g.getChildren().add(bt2);
            g.getChildren().add(bt3);
            g.getChildren().add(semBox);
            g.getChildren().add(Year);
            stage.setWidth(420);
            stage.setHeight(420);
            stage.setScene(s);
            stage.setResizable(false);
            stage.show();
        });
// ---------------------------------------------------------------------------------------------------------------------------- VV
        // EDIT ENROLLING
        editEnroll.setOnAction(e -> {
            Stage EditEStage = new Stage();
            Group editEGroup = new Group();
            Scene editEScene = new Scene(editEGroup);
            EditEStage.setTitle("Campus Utility: Edit / View Enrollments");
            Label studentIDLabel = new Label("Student ID");
            TextField stID = new TextField();
            Button viewCourses = new Button("Load");
            Label courseID = new Label("Course ID");
            TextField cID = new TextField("");
            Button dropCourse = new Button("Drop");
            TableView<Enrollment> table;
            TableColumn<Enrollment, String> studentName = new TableColumn<>("Student");
            studentName.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("studentName"));
            TableColumn<Enrollment, String> courseName = new TableColumn<>("Course Name");
            courseName.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("courseName"));
            TableColumn<Enrollment, Integer> studentsCourseID = new TableColumn<>("Course ID");
            studentsCourseID.setCellValueFactory(new PropertyValueFactory<Enrollment, Integer>("cID"));
            TableColumn<Enrollment, String> semester = new TableColumn<>("Semester");
            semester.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("semseter"));
            TableColumn<Enrollment, String> year = new TableColumn<>("Year");
            year.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("year"));
            TableColumn<Enrollment, String> grade = new TableColumn<>("Grade");
            grade.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("grade"));
            table = new TableView<>();
            table.getColumns().add(studentName);
            table.getColumns().add(courseName);
            table.getColumns().add(studentsCourseID);
            table.getColumns().add(semester);
            table.getColumns().add(year);
            table.getColumns().add(grade);
            studentIDLabel.setLayoutX(20);
            studentIDLabel.setLayoutY(20);
            stID.setPrefWidth(50);
            stID.setLayoutX(85);
            stID.setLayoutY(17);
            viewCourses.setLayoutX(140);
            viewCourses.setLayoutY(17);
            courseID.setLayoutX(220);
            courseID.setLayoutY(20);
            cID.setLayoutX(290);
            cID.setLayoutY(17);
            cID.setPrefWidth(50);
            dropCourse.setLayoutX(350);
            dropCourse.setLayoutY(17);
            table.setLayoutX(5);
            table.setLayoutY(100);
            viewCourses.setOnAction(y -> {
                table.getItems().clear();
                for (int i = 1; i < enrollment.size(); i++) {
                    Enrollment overview = enrollment.getNode(i);
                    int int1 = Integer.parseInt(stID.getText());
                    if (overview != null) {
                        if (int1 == overview.getSID()) {
                            table.getItems().add(overview);
                        }
                    }
                }
            });
            dropCourse.setOnAction(r -> {
                int coursesID = Integer.parseInt(cID.getText());
                Enrollment ov = null;
                for (int j = 1; j < enrollment.size(); j++) {
                    if (enrollment.getNode(j) != null && enrollment.getNode(j).getCID() == coursesID) {
                        ov = enrollment.getNode(j);
                    }
                }
                enrollment.deleteNode(ov);
                try {
                    datWriter("enrollment");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                table.refresh();
            });
            editEGroup.getChildren().add(studentIDLabel);
            editEGroup.getChildren().add(stID);
            editEGroup.getChildren().add(viewCourses);
            editEGroup.getChildren().add(courseID);
            editEGroup.getChildren().add(cID);
            editEGroup.getChildren().add(dropCourse);
            editEGroup.getChildren().add(table);
            EditEStage.setWidth(520);
            EditEStage.setHeight(520);
            EditEStage.setScene(editEScene);
            EditEStage.setResizable(false);
            EditEStage.show();
        });
//-------------------------------------------------------------------------------------------------------------------------- ^^
        /**
         * GRADES
         */
        // COURSE GRADES
        addGrade.setOnAction(e -> {
            Stage stage = new Stage();
            Group g = new Group();
            Scene s = new Scene(g);
            stage.setTitle("Campus Utility: View Grades");
            Label label = new Label("Course ID");
            TextField tf = new TextField();
            Button bt = new Button("View Grades");
            Label label2 = new Label("Student ID");
            TextField tf2 = new TextField();
            Label label3 = new Label("Add Grade");
            TextField tf3 = new TextField();
            Button bt2 = new Button("Add");
            TableView<Enrollment> table;
            TableColumn<Enrollment, String> courseName = new TableColumn<>("Course");
            courseName.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("courseName"));
            TableColumn<Enrollment, String> studentName = new TableColumn<>("Student");
            studentName.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("studentName"));
            TableColumn<Enrollment, Integer> studentID = new TableColumn<>("Student ID");
            studentID.setCellValueFactory(new PropertyValueFactory<Enrollment, Integer>("sID"));
            TableColumn<Enrollment, String> semester = new TableColumn<>("Semester");
            semester.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("semseter"));
            TableColumn<Enrollment, String> year = new TableColumn<>("Year");
            year.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("year"));
            TableColumn<Enrollment, String> grade = new TableColumn<>("Grade");
            grade.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("grade"));
            table = new TableView<>();
            table.getColumns().add(courseName);
            table.getColumns().add(studentName);
            table.getColumns().add(studentID);
            table.getColumns().add(semester);
            table.getColumns().add(year);
            table.getColumns().add(grade);
            label.setLayoutX(20);
            label.setLayoutY(20);
            label2.setLayoutX(20);
            label2.setLayoutY(60);
            label3.setLayoutX(235);
            label3.setLayoutY(60);
            tf.setLayoutX(80);
            tf.setLayoutY(18);
            tf2.setLayoutX(80);
            tf2.setLayoutY(55);
            tf3.setLayoutX(294);
            tf3.setLayoutY(55);
            tf3.setPrefWidth(30);
            bt.setLayoutX(250);
            bt.setLayoutY(18);
            bt2.setLayoutX(330);
            bt2.setLayoutY(55);
            table.setLayoutX(5);
            table.setLayoutY(100);
            stage.setWidth(530);
            stage.setHeight(550);
            bt.setOnAction(y -> {
                table.getItems().clear();
                for (int i = 1; i < enrollment.size(); i++) {
                    Enrollment overview = enrollment.getNode(i);
                    int int1 = Integer.parseInt(tf.getText());
                    if (overview != null) {
                        if (int1 == overview.getCID()) {
                            table.getItems().add(overview);
                        }
                    }
                }
            });
            bt2.setOnAction(z -> {
                for (int i = 1; i < enrollment.size(); i++) {
                    Enrollment overview = enrollment.getNode(i);
                    int cID = Integer.parseInt(tf.getText());
                    int sID = Integer.parseInt(tf2.getText());
                    if (overview != null) {
                        if (cID == overview.getCID() && sID == overview.getSID()) {
                            overview.setGrade(tf3.getText());
                            table.refresh();
                        }
                    }
                }
                try {
                    datWriter("enrollment");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            });
            g.getChildren().add(label);
            g.getChildren().add(label2);
            g.getChildren().add(tf);
            g.getChildren().add(tf2);
            g.getChildren().add(bt);
            g.getChildren().add(bt2);
            g.getChildren().add(label3);
            g.getChildren().add(tf3);
            g.getChildren().add(table);
            stage.setScene(s);
            stage.setResizable(false);
            stage.show();
        });
        // STUDENT GRADES
        addGradeS.setOnAction(e -> {
            Stage stage = new Stage();
            Group g = new Group();
            Scene s = new Scene(g);
            stage.setTitle("Campus Utility: View Grades");
            Label label = new Label("Student ID");
            TextField tf = new TextField();
            Button bt = new Button("View Grades");
            Label label2 = new Label("Course ID");
            TextField tf2 = new TextField();
            Label label3 = new Label("Add Grade");
            TextField tf3 = new TextField();
            Button bt2 = new Button("Add");
            TableView<Enrollment> table;
            TableColumn<Enrollment, String> studentName = new TableColumn<>("Name");
            studentName.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("studentName"));
            TableColumn<Enrollment, String> courseName = new TableColumn<>("Course");
            courseName.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("courseName"));
            TableColumn<Enrollment, Integer> courseID = new TableColumn<>("CID");
            courseID.setCellValueFactory(new PropertyValueFactory<Enrollment, Integer>("cID"));
            TableColumn<Enrollment, String> semester = new TableColumn<>("Semester");
            semester.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("semseter"));
            TableColumn<Enrollment, String> year = new TableColumn<>("Year");
            year.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("year"));
            TableColumn<Enrollment, String> grade = new TableColumn<>("Grade");
            grade.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("grade"));
            table = new TableView<>();
            table.getColumns().add(studentName);
            table.getColumns().add(courseName);
            table.getColumns().add(courseID);
            table.getColumns().add(semester);
            table.getColumns().add(year);
            table.getColumns().add(grade);
            label.setLayoutX(20);
            label.setLayoutY(20);
            label2.setLayoutX(20);
            label2.setLayoutY(60);
            label3.setLayoutX(235);
            label3.setLayoutY(60);
            tf.setLayoutX(80);
            tf.setLayoutY(18);
            tf2.setLayoutX(80);
            tf2.setLayoutY(55);
            tf3.setLayoutX(294);
            tf3.setLayoutY(55);
            tf3.setPrefWidth(30);
            bt.setLayoutX(250);
            bt.setLayoutY(18);
            bt2.setLayoutX(330);
            bt2.setLayoutY(55);
            table.setLayoutX(5);
            table.setLayoutY(100);
            stage.setWidth(530);
            stage.setHeight(550);
            bt.setOnAction(y -> {
                table.getItems().clear();
                for (int i = 1; i < enrollment.size(); i++) {
                    Enrollment overview = enrollment.getNode(i);
                    int int1 = Integer.parseInt(tf.getText());
                    if (overview != null) {
                        if (int1 == overview.getSID()) {
                            table.getItems().add(overview);
                        }

                    }
                }
            });
            bt2.setOnAction(z -> {
                for (int i = 1; i < enrollment.size(); i++) {
                    Enrollment overview = enrollment.getNode(i);
                    int cID = Integer.parseInt(tf2.getText());
                    int sID = Integer.parseInt(tf.getText());
                    if (overview != null) {
                        if (cID == overview.getCID() && sID == overview.getSID()) {
                            overview.setGrade(tf3.getText());
                            table.refresh();

                        }
                    }

                }
                try {
                    datWriter("enrollment");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
            g.getChildren().add(label);
            g.getChildren().add(label2);
            g.getChildren().add(tf);
            g.getChildren().add(tf2);
            g.getChildren().add(bt);
            g.getChildren().add(bt2);
            g.getChildren().add(label3);
            g.getChildren().add(tf3);
            g.getChildren().add(table);
            stage.setScene(s);
            stage.setResizable(false);
            stage.show();
        });

        /**
         * REPORTS
         */

        // REPORT
        viewReport.setOnAction(e -> {
            Stage reportStage = new Stage();
            Group reportGroup = new Group();
            Scene reportScene = new Scene(reportGroup);
            reportStage.setTitle("Campus Utility: View Report");
            Label courseLabel = new Label("Course ID");
            TextField courseField = new TextField();
            ChoiceBox<String> semester = new ChoiceBox<>();
            semester.getItems().add("Spring");
            semester.getItems().add("Summer");
            semester.getItems().add("Fall");
            semester.getItems().add("Winter");
            ChoiceBox<String> year = new ChoiceBox<>();
            year.getItems().add("2015");
            year.getItems().add("2016");
            year.getItems().add("2017");
            year.getItems().add("2018");
            year.getItems().add("2019");
            year.getItems().add("2020");
            year.getItems().add("2021");
            Button reportButton = new Button("Generate Report");
            TextArea viewText = new TextArea();
            courseLabel.setLayoutX(5);
            courseLabel.setLayoutY(20);
            courseField.setLayoutX(60);
            courseField.setLayoutY(18);
            courseField.setPrefWidth(50);
            semester.setLayoutX(120);
            semester.setLayoutY(18);
            year.setLayoutX(220);
            year.setLayoutY(18);
            viewText.setLayoutX(10);
            viewText.setLayoutY(80);
            viewText.setPrefWidth(380);
            viewText.setEditable(false);
            reportButton.setLayoutX(290);
            reportButton.setLayoutY(18);
            reportGroup.getChildren().add(courseLabel);
            reportGroup.getChildren().add(courseField);
            reportGroup.getChildren().add(semester);
            reportGroup.getChildren().add(year);
            reportGroup.getChildren().add(reportButton);
            reportGroup.getChildren().add(viewText);

            reportButton.setOnAction(x -> {
                viewText.clear();
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < enrollment.size(); i++) {
                    Enrollment er = enrollment.getNode(i);
                    if (er.getCID() == Integer.parseInt(courseField.getText())
                            && er.getSemseter().equals(semester.getValue()) && er.getYear().equals(year.getValue())) {
                        //if (sb.isEmpty()) {
                            sb.insert(0, er.getCourseName());
                            sb.append("\n-------------------------");
                       // }
                        sb.append("\n" + er.getStudentName() + " " + er.getGrade());
                    }
                }
                viewText.insertText(0, sb.toString());
            });

            reportStage.setWidth(420);
            reportStage.setHeight(340);
            reportStage.setScene(reportScene);
            reportStage.setResizable(false);
            reportStage.show();

        });

    }
}

