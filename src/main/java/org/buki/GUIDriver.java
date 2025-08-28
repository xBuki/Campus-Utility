package org.buki;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

public class GUIDriver extends Application {
    //private final DatabaseConnection connectionFactory = new DatabaseConnection();
    private Connection connection;
    private ObservableList<ObservableList> data;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        Group mainGroup = new Group();
        Scene mainScene = new Scene(mainGroup);
        mainStage.setTitle("Campus Portal");

        Label frontPage = new Label("Welcome to the campus portal");
        frontPage.setLayoutX(200);
        frontPage.setLayoutY(180);

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(mainStage.widthProperty());
        mainGroup.getChildren().addAll(menuBar, frontPage);

        Menu homeMenu = new Menu("Home");

        Menu studentMenu = new Menu("Student");
        MenuItem searchStButton = new MenuItem("Search");
        MenuItem addStButton = new MenuItem("Add Student");
        MenuItem editStButton = new MenuItem("Edit Student");
        studentMenu.getItems().addAll(searchStButton, addStButton, editStButton);

        Menu departmentMenu = new Menu("Department");
        MenuItem searchDepartment = new MenuItem("Search");
        MenuItem addDepartment = new MenuItem("Add Department");
        MenuItem editDepartment = new MenuItem("Edit Department");
        MenuItem deleteDepartment = new MenuItem("Delete Department");
        MenuItem allDepartments = new MenuItem("All Departments");
        departmentMenu.getItems().addAll(searchDepartment, addDepartment, editDepartment, deleteDepartment,
                allDepartments);

        Menu profMenu = new Menu("Professor");
        MenuItem searchProfButton = new MenuItem("Search Professor");
        MenuItem addProfButton = new MenuItem("Add Professor");
        MenuItem editProfButton = new MenuItem("Edit Professor");
        MenuItem deleteProfButton = new MenuItem("Delete Professor");
        MenuItem allProfessors = new MenuItem("All Professors");
        profMenu.getItems().addAll(searchProfButton, addProfButton, editProfButton, deleteProfButton, allProfessors);

        Menu courseMenu = new Menu("Course");
        MenuItem searchCourseButton = new MenuItem("Search Course");
        MenuItem addCourseButton = new MenuItem("Add Course");
        MenuItem editCourseButton = new MenuItem("Edit Course");
        courseMenu.getItems().addAll(searchCourseButton, addCourseButton, editCourseButton);

        Menu enrollmentMenu = new Menu("Enrollment");
        MenuItem addEnrollmentButton = new MenuItem("Add Enrollment");
        MenuItem editEnrollmentButton = new MenuItem("Edit Enrollments");
        enrollmentMenu.getItems().addAll(addEnrollmentButton, editEnrollmentButton);

        Menu gradeMenu = new Menu("Grades");
        Menu addGrades = new Menu("Add Grades by");
        MenuItem studentGrades = new MenuItem("Students");
        MenuItem courseGrades = new MenuItem("Course");
        addGrades.getItems().addAll(studentGrades, courseGrades);
        gradeMenu.getItems().addAll(addGrades);

        Menu reportMenu = new Menu("Reports");
        MenuItem showGradeButton = new MenuItem("Show Report");
        reportMenu.getItems().addAll(showGradeButton);

        menuBar.getMenus().addAll(homeMenu, studentMenu, departmentMenu, profMenu, courseMenu, enrollmentMenu,
                gradeMenu, reportMenu);

        mainStage.setWidth(600);
        mainStage.setHeight(400);
        mainStage.setResizable(false);
        mainStage.setScene(mainScene);
        mainStage.show();

        homeMenu.setOnAction(a -> {
            mainStage.setScene(mainScene);
        });

        /**
         *
         *
         * THIS IS THE STUDENT SECTION!
         *
         */

        searchStButton.setOnAction(a -> {
            Group searchStGroup = new Group();
            Scene searcStScene = new Scene(searchStGroup);
            Label studentInformation = new Label("Student Information");
            Label studentID = new Label("Student ID");
            Label studentFirstName = new Label("First Name");
            Label studentLastName = new Label("Last Name");
            Label studentAddress = new Label("Address");
            Label studentCity = new Label("City");
            Label studentState = new Label("State");
            TextField studentIDTextField = new TextField();
            TextField studentFirstNameTextField = new TextField();
            TextField studentLastNameTextField = new TextField();
            TextField studentAddressTextField = new TextField();
            TextField studentCityTextField = new TextField();
            TextField studentStateTextField = new TextField();
            Button searchStudentButton = new Button("Search");
            studentID.setLayoutX(140);
            studentID.setLayoutY(105);
            studentFirstName.setLayoutX(140);
            studentFirstName.setLayoutY(135);
            studentLastName.setLayoutX(140);
            studentLastName.setLayoutY(165);
            studentAddress.setLayoutX(140);
            studentAddress.setLayoutY(195);
            studentCity.setLayoutX(140);
            studentCity.setLayoutY(225);
            studentState.setLayoutX(140);
            studentState.setLayoutY(255);
            studentIDTextField.setLayoutX(210);
            studentIDTextField.setLayoutY(100);
            studentFirstNameTextField.setLayoutX(210);
            studentFirstNameTextField.setLayoutY(130);
            studentLastNameTextField.setLayoutX(210);
            studentLastNameTextField.setLayoutY(160);
            studentAddressTextField.setLayoutX(210);
            studentAddressTextField.setLayoutY(190);
            studentCityTextField.setLayoutX(210);
            studentCityTextField.setLayoutY(220);
            studentStateTextField.setLayoutX(210);
            studentStateTextField.setLayoutY(250);
            studentInformation.setLayoutX(230);
            studentInformation.setLayoutY(50);
            searchStudentButton.setLayoutX(370);
            searchStudentButton.setLayoutY(98);
            searchStudentButton.setOnAction(b -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String Query = "CALL SelectStudentByID(?)";
                    PreparedStatement pst = connection.prepareStatement(Query);
                    pst.setString(1, studentIDTextField.getText());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        studentFirstNameTextField.setText(rs.getString("studentFirst"));
                        studentLastNameTextField.setText(rs.getString("studentLast"));
                        studentAddressTextField.setText(rs.getString("studentStreet"));
                        studentCityTextField.setText(rs.getString("studentCity"));
                        studentStateTextField.setText(rs.getString("studentState"));
                    } else {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n A student with that \n ID does not exist.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                    connection.close();
                } catch (SQLException e) {
                    if (studentIDTextField.getText().isEmpty() || studentIDTextField.getText().isEmpty()) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter a ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } else if (e.toString().contains("Incorrect integer value:")) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter \nONLY numbers for ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            searchStGroup.getChildren().add(studentInformation);
            searchStGroup.getChildren().add(menuBar);
            searchStGroup.getChildren().add(studentIDTextField);
            searchStGroup.getChildren().add(studentID);
            searchStGroup.getChildren().add(studentFirstName);
            searchStGroup.getChildren().add(studentFirstNameTextField);
            searchStGroup.getChildren().add(studentLastName);
            searchStGroup.getChildren().add(studentLastNameTextField);
            searchStGroup.getChildren().add(studentAddress);
            searchStGroup.getChildren().add(studentAddressTextField);
            searchStGroup.getChildren().add(studentCity);
            searchStGroup.getChildren().add(studentCityTextField);
            searchStGroup.getChildren().add(searchStudentButton);
            searchStGroup.getChildren().add(studentState);
            searchStGroup.getChildren().add(studentStateTextField);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(searcStScene);
        });

        addStButton.setOnAction(a -> {
            Group addStGroup = new Group();
            Scene addStScene = new Scene(addStGroup);
            Label studentInformation = new Label("New Student Information");
            Label studentID = new Label("Student ID");
            Label studentFirstName = new Label("First Name");
            Label studentLastName = new Label("Last Name");
            Label studentAddress = new Label("Address");
            Label studentCity = new Label("City");
            Label studentState = new Label("State");
            TextField studentIDTextField = new TextField();
            TextField studentFirstNameTextField = new TextField();
            TextField studentLastNameTextField = new TextField();
            TextField studentAddressTextField = new TextField();
            TextField studentCityTextField = new TextField();
            ChoiceBox<String> states = new ChoiceBox<>();
            states.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "HI", "ID", "IL",
                    "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
                    "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
                    "VA", "WA", "WV", "WI", "WY");

            Button addStudentButton = new Button("Add");
            studentID.setLayoutX(140);
            studentID.setLayoutY(105);
            studentFirstName.setLayoutX(140);
            studentFirstName.setLayoutY(135);
            studentLastName.setLayoutX(140);
            studentLastName.setLayoutY(165);
            studentAddress.setLayoutX(140);
            studentAddress.setLayoutY(195);
            studentCity.setLayoutX(140);
            studentCity.setLayoutY(225);
            studentState.setLayoutX(140);
            studentState.setLayoutY(255);
            studentIDTextField.setLayoutX(210);
            studentIDTextField.setLayoutY(100);
            studentFirstNameTextField.setLayoutX(210);
            studentFirstNameTextField.setLayoutY(130);
            studentLastNameTextField.setLayoutX(210);
            studentLastNameTextField.setLayoutY(160);
            studentAddressTextField.setLayoutX(210);
            studentAddressTextField.setLayoutY(190);
            studentCityTextField.setLayoutX(210);
            studentCityTextField.setLayoutY(220);
            states.setLayoutX(210);
            states.setLayoutY(250);
            studentInformation.setLayoutX(230);
            studentInformation.setLayoutY(50);
            addStudentButton.setLayoutX(450);
            addStudentButton.setLayoutY(300);
            addStudentButton.setOnAction(b -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String sql = "CALL InsertStudentTable(?, ?, ?, ?, ?, ?)";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, studentIDTextField.getText());
                    pst.setString(2, studentFirstNameTextField.getText());
                    pst.setString(3, studentLastNameTextField.getText());
                    pst.setString(4, studentAddressTextField.getText());
                    pst.setString(5, studentCityTextField.getText());
                    if (states.getValue() == null) {
                        pst.setString(6, "null");
                    } else {
                        pst.setString(6, states.getValue().toString());
                    }
                    pst.execute();
                    connection.close();
                    Stage ErrorStage = new Stage();
                    Group ErrorGroup = new Group();
                    Scene ErrorScene = new Scene(ErrorGroup);
                    Label errorLabel = new Label("Success:\n Successfully Added");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    ErrorGroup.getChildren().addAll(okButton, errorLabel);
                    okButton.setOnAction(c -> {
                        ErrorStage.close();
                        studentIDTextField.clear();
                        studentFirstNameTextField.clear();
                        studentLastNameTextField.clear();
                        studentAddressTextField.clear();
                        studentCityTextField.clear();
                        states.setValue("");
                    });
                    ErrorStage.setWidth(200);
                    ErrorStage.setHeight(150);
                    ErrorStage.setScene(ErrorScene);
                    ErrorStage.setResizable(false);
                    ErrorStage.show();
                } catch (SQLException e) {
                    if (studentIDTextField.getText().isEmpty() || studentIDTextField.getText().isEmpty()) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter a ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } else if (e.toString().contains("Incorrect integer value:")) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter \nONLY numbers for ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }

                } catch (Exception e) {
                    System.out.println(e.toString());
                    if (e.toString().startsWith("java.sql.SQLIntegrityConstraintViolationException")) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n A student with that \n ID exist already.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                }
            });
            addStGroup.getChildren().addAll(menuBar, studentID, studentFirstName, studentLastName, studentAddress,
                    studentCity, studentIDTextField, studentFirstNameTextField, studentLastNameTextField,
                    studentAddressTextField, studentCityTextField, studentState, states, studentInformation,
                    addStudentButton);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(addStScene);
        });

        editStButton.setOnAction(a ->

        {
            Group editStGroup = new Group();
            Scene editStScene = new Scene(editStGroup);
            Label studentInformation = new Label("Edit Student Information");
            Label studentID = new Label("Student ID");
            Label studentFirstName = new Label("First Name");
            Label studentLastName = new Label("Last Name");
            Label studentAddress = new Label("Address");
            Label studentCity = new Label("City");
            Label studentState = new Label("State");
            TextField studentIDTextField = new TextField();
            TextField studentFirstNameTextField = new TextField();
            TextField studentLastNameTextField = new TextField();
            TextField studentAddressTextField = new TextField();
            TextField studentCityTextField = new TextField();
            ChoiceBox<String> states = new ChoiceBox<>();
            states.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "HI", "ID", "IL",
                    "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
                    "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
                    "VA", "WA", "WV", "WI", "WY");
            Button searchStudentButton = new Button("Search");
            Button updateStudentButton = new Button("Update");
            studentID.setLayoutX(140);
            studentID.setLayoutY(105);
            studentFirstName.setLayoutX(140);
            studentFirstName.setLayoutY(135);
            studentLastName.setLayoutX(140);
            studentLastName.setLayoutY(165);
            studentAddress.setLayoutX(140);
            studentAddress.setLayoutY(195);
            studentCity.setLayoutX(140);
            studentCity.setLayoutY(225);
            studentState.setLayoutX(140);
            studentState.setLayoutY(255);
            studentIDTextField.setLayoutX(210);
            studentIDTextField.setLayoutY(100);
            studentFirstNameTextField.setLayoutX(210);
            studentFirstNameTextField.setLayoutY(130);
            studentLastNameTextField.setLayoutX(210);
            studentLastNameTextField.setLayoutY(160);
            studentAddressTextField.setLayoutX(210);
            studentAddressTextField.setLayoutY(190);
            studentCityTextField.setLayoutX(210);
            studentCityTextField.setLayoutY(220);
            states.setLayoutX(210);
            states.setLayoutY(250);
            studentInformation.setLayoutX(230);
            studentInformation.setLayoutY(50);
            searchStudentButton.setLayoutX(370);
            searchStudentButton.setLayoutY(98);
            updateStudentButton.setLayoutX(400);
            updateStudentButton.setLayoutY(300);
            searchStudentButton.setOnAction(b -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String Query = "CALL SelectStudentByID(?)";
                    PreparedStatement pst = connection.prepareStatement(Query);
                    pst.setString(1, studentIDTextField.getText());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        studentFirstNameTextField.setText(rs.getString("studentFirst"));
                        studentLastNameTextField.setText(rs.getString("studentLast"));
                        studentAddressTextField.setText(rs.getString("studentStreet"));
                        studentCityTextField.setText(rs.getString("studentCity"));
                        states.setValue(rs.getString("studentState"));
                    } else {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n A student with that \n ID does not exist.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (SQLException e) {
                    if (studentIDTextField.getText().isEmpty() || studentIDTextField.getText().isEmpty()) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter a ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } else if (e.toString().contains("Incorrect integer value:")) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter \nONLY numbers for ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }

                } catch (Exception e) {

                }
            });
            updateStudentButton.setOnAction(c -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String Query = "CALL UpdateStudentTable(?, ?, ?, ?, ?, ?)";
                    PreparedStatement pst = connection.prepareStatement(Query);
                    pst.setString(1, studentFirstNameTextField.getText());
                    pst.setString(2, studentLastNameTextField.getText());
                    pst.setString(3, studentAddressTextField.getText());
                    pst.setString(4, studentCityTextField.getText());
                    pst.setString(5, states.getValue().toString());
                    pst.setString(6, studentIDTextField.getText());
                    pst.executeUpdate();
                    connection.close();
                    Stage SuccessStage = new Stage();
                    Group SuccessGroup = new Group();
                    Scene SuccessScene = new Scene(SuccessGroup);
                    Label errorLabel = new Label("\n Information successfully updated");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    SuccessGroup.getChildren().addAll(okButton, errorLabel);
                    okButton.setOnAction(d -> {
                        SuccessStage.close();
                        studentIDTextField.clear();
                        studentFirstNameTextField.clear();
                        studentLastNameTextField.clear();
                        studentAddressTextField.clear();
                        studentCityTextField.clear();
                        states.setValue("");
                    });
                    SuccessStage.setWidth(200);
                    SuccessStage.setHeight(150);
                    SuccessStage.setScene(SuccessScene);
                    SuccessStage.setResizable(false);
                    SuccessStage.show();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            editStGroup.getChildren().add(studentInformation);
            editStGroup.getChildren().add(menuBar);
            editStGroup.getChildren().add(studentIDTextField);
            editStGroup.getChildren().add(studentID);
            editStGroup.getChildren().add(studentFirstName);
            editStGroup.getChildren().add(studentFirstNameTextField);
            editStGroup.getChildren().add(studentLastName);
            editStGroup.getChildren().add(studentLastNameTextField);
            editStGroup.getChildren().add(studentAddress);
            editStGroup.getChildren().add(studentAddressTextField);
            editStGroup.getChildren().add(studentCity);
            editStGroup.getChildren().add(studentCityTextField);
            editStGroup.getChildren().add(searchStudentButton);
            editStGroup.getChildren().add(updateStudentButton);
            editStGroup.getChildren().add(states);
            editStGroup.getChildren().add(studentState);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(editStScene);
        });

        /**
         *
         * THIS IS THE DEPARTMENT SECTION
         *
         */

        searchDepartment.setOnAction(a -> {
            Group searchDepartmentGroup = new Group();
            Scene searchDepartmentScene = new Scene(searchDepartmentGroup);
            Label departmentInformation = new Label("Department Information");
            Label departmentID = new Label("Department ID");
            Label departmentName = new Label("Department Name");
            TextField departmentIDTextField = new TextField();
            TextField departmentNameTextField = new TextField();
            Button searchDepartmentButton = new Button("Search");
            departmentID.setLayoutX(120);
            departmentID.setLayoutY(105);
            departmentName.setLayoutX(100);
            departmentName.setLayoutY(135);
            departmentInformation.setLayoutX(230);
            departmentInformation.setLayoutY(50);
            departmentIDTextField.setLayoutX(210);
            departmentIDTextField.setLayoutY(100);
            departmentNameTextField.setLayoutX(210);
            departmentNameTextField.setLayoutY(130);
            searchDepartmentButton.setLayoutX(380);
            searchDepartmentButton.setLayoutY(100);
            searchDepartmentButton.setOnAction(b -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String Query = "CALL SelectDepartmentByID(?)";
                    PreparedStatement pst = connection.prepareStatement(Query);
                    pst.setString(1, departmentIDTextField.getText());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        departmentNameTextField.setText(rs.getString("departmentName"));
                    } else {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n A department with that \n ID does not exist.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (SQLException e) {
                    if (departmentIDTextField.getText().isEmpty() || departmentIDTextField.getText().isEmpty()) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter a ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } else if (e.toString().contains("Incorrect integer value:")) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter \nONLY numbers for ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }

                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            searchDepartmentGroup.getChildren().addAll(departmentInformation, departmentID, departmentIDTextField,
                    departmentNameTextField, departmentName, searchDepartmentButton, menuBar);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(searchDepartmentScene);
        });

        addDepartment.setOnAction(a -> {
            Group addDepartmentGroup = new Group();
            Scene addDepartmentScene = new Scene(addDepartmentGroup);
            Label departmentInformation = new Label("New Department Information");
            Label departmentID = new Label("Department ID");
            Label departmentName = new Label("Department Name");
            TextField departmentIDTextField = new TextField();
            TextField departmentNameTextField = new TextField();
            Button addDepartmentButton = new Button("Add");
            departmentID.setLayoutX(120);
            departmentID.setLayoutY(105);
            departmentName.setLayoutX(100);
            departmentName.setLayoutY(135);
            departmentInformation.setLayoutX(230);
            departmentInformation.setLayoutY(50);
            departmentIDTextField.setLayoutX(210);
            departmentIDTextField.setLayoutY(100);
            departmentNameTextField.setLayoutX(210);
            departmentNameTextField.setLayoutY(130);
            addDepartmentButton.setLayoutX(380);
            addDepartmentButton.setLayoutY(200);
            addDepartmentButton.setOnAction(b -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String sql = "CALL InsertDepartmentTable(?, ?)";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, departmentIDTextField.getText());
                    pst.setString(2, departmentNameTextField.getText());
                    pst.execute();
                    connection.close();
                    Stage ErrorStage = new Stage();
                    Group ErrorGroup = new Group();
                    Scene ErrorScene = new Scene(ErrorGroup);
                    Label errorLabel = new Label("\n Successfully Added");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    ErrorGroup.getChildren().addAll(okButton, errorLabel);
                    okButton.setOnAction(c -> {
                        ErrorStage.close();
                        departmentIDTextField.clear();
                        departmentNameTextField.clear();
                    });
                    ErrorStage.setWidth(200);
                    ErrorStage.setHeight(150);
                    ErrorStage.setScene(ErrorScene);
                    ErrorStage.setResizable(false);
                    ErrorStage.show();
                } catch (SQLException e) {
                    if (departmentIDTextField.getText().isEmpty() || departmentIDTextField.getText().isEmpty()) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter a ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } else if (e.toString().contains("Incorrect integer value:")) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter \nONLY numbers for ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }

                } catch (Exception e) {
                    System.out.println(e.toString());
                    if (e.toString().startsWith("java.sql.SQLIntegrityConstraintViolationException")) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n A department with that \n ID exist already.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                }
            });
            addDepartmentGroup.getChildren().addAll(departmentInformation, departmentID, departmentIDTextField,
                    departmentNameTextField, departmentName, addDepartmentButton, menuBar);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(addDepartmentScene);
        });

        editDepartment.setOnAction(a ->

        {
            Group editDepartmentGroup = new Group();
            Scene editDepartmentScene = new Scene(editDepartmentGroup);
            Label departmentInformation = new Label("Edit Department Information");
            Label departmentID = new Label("Department ID");
            Label departmentName = new Label("Department Name");
            TextField departmentIDTextField = new TextField();
            TextField departmentNameTextField = new TextField();
            Button searchDepartmentButton = new Button("Search");
            Button updateDepartmentButton = new Button("Update");
            departmentID.setLayoutX(120);
            departmentID.setLayoutY(105);
            departmentName.setLayoutX(100);
            departmentName.setLayoutY(135);
            departmentInformation.setLayoutX(230);
            departmentInformation.setLayoutY(50);
            departmentIDTextField.setLayoutX(210);
            departmentIDTextField.setLayoutY(100);
            departmentNameTextField.setLayoutX(210);
            departmentNameTextField.setLayoutY(130);
            searchDepartmentButton.setLayoutX(380);
            searchDepartmentButton.setLayoutY(100);
            updateDepartmentButton.setLayoutX(380);
            updateDepartmentButton.setLayoutY(200);
            searchDepartmentButton.setOnAction(b -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String Query = "CALL SelectDepartmentByID(?)";
                    PreparedStatement pst = connection.prepareStatement(Query);
                    pst.setString(1, departmentIDTextField.getText());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        departmentNameTextField.setText(rs.getString("departmentName"));
                    } else {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n A department with that \n ID does not exist.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (SQLException e) {
                    if (departmentIDTextField.getText().isEmpty() || departmentIDTextField.getText().isEmpty()) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter a ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } else if (e.toString().contains("Incorrect integer value:")) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter \nONLY numbers for ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (Exception e) {

                }
            });
            updateDepartmentButton.setOnAction(c -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String Query = "CALL UpdateDepartmentTable(?,?)";
                    PreparedStatement pst = connection.prepareStatement(Query);
                    pst.setString(1, departmentIDTextField.getText());
                    pst.setString(2, departmentNameTextField.getText());
                    pst.executeUpdate();
                    connection.close();
                    Stage SuccessStage = new Stage();
                    Group SuccessGroup = new Group();
                    Scene SuccessScene = new Scene(SuccessGroup);
                    Label errorLabel = new Label("\n Information successfully updated");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    SuccessGroup.getChildren().addAll(okButton, errorLabel);
                    okButton.setOnAction(d -> {
                        SuccessStage.close();
                        departmentIDTextField.clear();
                        departmentNameTextField.clear();
                    });
                    SuccessStage.setWidth(200);
                    SuccessStage.setHeight(150);
                    SuccessStage.setScene(SuccessScene);
                    SuccessStage.setResizable(false);
                    SuccessStage.show();
                } catch (SQLException e) {
                    if (departmentIDTextField.getText().isEmpty() || departmentIDTextField.getText().isEmpty()) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter a ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(d -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } else if (e.toString().contains("Incorrect integer value:")) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter \nONLY numbers for ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(f -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            editDepartmentGroup.getChildren().addAll(menuBar, departmentInformation, departmentID, departmentName,
                    departmentIDTextField, departmentNameTextField, searchDepartmentButton, updateDepartmentButton);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(editDepartmentScene);
        });

        deleteDepartment.setOnAction(a -> {
            Group deleteDepartmentGroup = new Group();
            Scene deleteDepartmentScene = new Scene(deleteDepartmentGroup);
            Label departmentInformation = new Label("Department Information");
            Label departmentID = new Label("Department ID");
            Label departmentName = new Label("Department Name");
            TextField departmentIDTextField = new TextField();
            TextField departmentNameTextField = new TextField();
            Button searchDepartmentButton = new Button("Search");
            Button deleteDepartmentButton = new Button("Delete");
            departmentID.setLayoutX(120);
            departmentID.setLayoutY(105);
            departmentName.setLayoutX(100);
            departmentName.setLayoutY(135);
            departmentInformation.setLayoutX(230);
            departmentInformation.setLayoutY(50);
            departmentIDTextField.setLayoutX(210);
            departmentIDTextField.setLayoutY(100);
            departmentNameTextField.setLayoutX(210);
            departmentNameTextField.setLayoutY(130);
            searchDepartmentButton.setLayoutX(380);
            searchDepartmentButton.setLayoutY(100);
            deleteDepartmentButton.setLayoutX(380);
            deleteDepartmentButton.setLayoutY(200);
            searchDepartmentButton.setOnAction(b -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String Query = "CALL SelectDepartmentByID(?)";
                    PreparedStatement pst = connection.prepareStatement(Query);
                    pst.setString(1, departmentIDTextField.getText());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        departmentNameTextField.setText(rs.getString("departmentName"));
                    } else {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n A department with that \n ID does not exist.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (SQLException e) {
                    if (departmentIDTextField.getText().isEmpty() || departmentIDTextField.getText().isEmpty()) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter a ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } else if (e.toString().contains("Incorrect integer value:")) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter \nONLY numbers for ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (Exception e) {

                }
            });
            deleteDepartmentButton.setOnAction(c -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String sql = "CALL DeleteDepartmentTable(?)";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, departmentIDTextField.getText());
                    pst.execute();
                    connection.close();
                    Stage SuccessStage = new Stage();
                    Group SuccessGroup = new Group();
                    Scene SuccessScene = new Scene(SuccessGroup);
                    Label errorLabel = new Label("\n Department successfully deleted");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    SuccessGroup.getChildren().addAll(okButton, errorLabel);
                    okButton.setOnAction(d -> {
                        SuccessStage.close();
                        departmentIDTextField.clear();
                        departmentNameTextField.clear();
                    });
                    SuccessStage.setWidth(200);
                    SuccessStage.setHeight(150);
                    SuccessStage.setScene(SuccessScene);
                    SuccessStage.setResizable(false);
                    SuccessStage.show();
                } catch (SQLException e) {
                    if (departmentIDTextField.getText().isEmpty() || departmentIDTextField.getText().isEmpty()) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter a ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(d -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } else if (e.toString().contains("Incorrect integer value:")) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter \nONLY numbers for ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(f -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            deleteDepartmentGroup.getChildren().addAll(menuBar, departmentInformation, departmentID, departmentName,
                    departmentIDTextField, departmentNameTextField, searchDepartmentButton, deleteDepartmentButton);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(deleteDepartmentScene);
        });

        allDepartments.setOnAction(a -> {
            data = FXCollections.observableArrayList();
            Group allDepartmentsGroup = new Group();
            Scene allDepartmentsScene = new Scene(allDepartmentsGroup);
            Label allDepartmentsLabel = new Label("Department Information");
            TableView table;
            table = new TableView<>();
            table.setLayoutX(120);
            table.setLayoutY(80);
            table.setPrefHeight(250);
            table.setPrefWidth(350);
            List<String> columnNames = new ArrayList<>();
            columnNames.add("Department ID");
            columnNames.add("Department Name");
            for (int i = 0; i < columnNames.size(); i++) {
                // We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(columnNames.get(i));
                col.setCellValueFactory(
                        new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                                return new SimpleStringProperty(param.getValue().get(j).toString());
                            }
                        });

                table.getColumns().addAll(col);
            }
            try {
                //connection = connectionFactory.connect();
                String SELECT_DEPARTMENTS = "SELECT * FROM departmentTable";
                ResultSet rs = connection.createStatement().executeQuery(SELECT_DEPARTMENTS);
                while (rs.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        row.add(rs.getString(i));
                    }
                    data.add(row);
                }
                table.setItems(data);
                table.refresh();

            } catch (Exception e) {
                System.out.println(e.toString());
            }
            allDepartmentsLabel.setLayoutX(230);
            allDepartmentsLabel.setLayoutY(50);
            allDepartmentsGroup.getChildren().addAll(menuBar, allDepartmentsLabel, table);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(allDepartmentsScene);

        });

        /**
         *
         * THIS IS THE PROFESSOR SECTION
         *
         */

        searchProfButton.setOnAction(a -> {
            Group searchProfGroup = new Group();
            Scene searchProfScene = new Scene(searchProfGroup);
            Label professorInformation = new Label("Search Professor Information");
            Label professorID = new Label("Professor ID");
            Label professorFirstName = new Label("First Name");
            Label professorLastName = new Label("Last Name");
            Label professorDepartment = new Label("Department");
            TextField professorIDTextField = new TextField();
            TextField professorFirstNameTextField = new TextField();
            TextField professorLastNameTextField = new TextField();
            Button searchProfessorButton = new Button("Search");
            TextField professorDepartmentTextField = new TextField();
            professorID.setLayoutX(120);
            professorID.setLayoutY(105);
            professorFirstName.setLayoutX(120);
            professorFirstName.setLayoutY(135);
            professorLastName.setLayoutX(120);
            professorLastName.setLayoutY(165);
            professorDepartment.setLayoutX(120);
            professorDepartment.setLayoutY(195);
            professorIDTextField.setLayoutX(210);
            professorIDTextField.setLayoutY(100);
            professorFirstNameTextField.setLayoutX(210);
            professorFirstNameTextField.setLayoutY(130);
            professorLastNameTextField.setLayoutX(210);
            professorLastNameTextField.setLayoutY(160);
            professorInformation.setLayoutX(230);
            professorInformation.setLayoutY(50);
            searchProfessorButton.setLayoutX(380);
            searchProfessorButton.setLayoutY(100);
            searchProfessorButton.setOnAction(b -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String Query = "SELECT i.instructorFirst,\r\n" + "i.instructorLast,\r\n" + "i.deptID,\r\n"
                            + "d.departmentName\r\n" + "FROM finalproject.instructortable i\r\n"
                            + "INNER JOIN finalproject.departmenttable d ON d.departmentID = i.deptID\r\n"
                            + "WHERE i.instructorID = " + professorIDTextField.getText();
                    String DEPT_CONVERSION = "SELECT departmentID FROM finaloproject.departmentTable WHERE departmentName = ?";
                    PreparedStatement pst = connection.prepareStatement(Query);
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        professorFirstNameTextField.setText(rs.getString("instructorFirst"));
                        professorLastNameTextField.setText(rs.getString("instructorLast"));
                        professorDepartmentTextField.setText(rs.getString("departmentName"));

                    } else {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n A instructor with that \n ID does not exist.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (SQLSyntaxErrorException sql) {
                    Stage ErrorStage = new Stage();
                    Group ErrorGroup = new Group();
                    Scene ErrorScene = new Scene(ErrorGroup);
                    Label errorLabel;
                    if (professorIDTextField.getText().isEmpty() || professorIDTextField.getText().isEmpty()) {
                        errorLabel = new Label(
                                "Error:\n You cannot leave the professor ID \nempty, please enter a number.");
                    } else {
                        errorLabel = new Label("Error:\n Not valid input\n, please enter a number.");
                    }
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    ErrorGroup.getChildren().addAll(okButton, errorLabel);
                    okButton.setOnAction(c -> {
                        ErrorStage.close();
                    });
                    ErrorStage.setWidth(200);
                    ErrorStage.setHeight(150);
                    ErrorStage.setScene(ErrorScene);
                    ErrorStage.setResizable(false);
                    ErrorStage.show();

                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            professorDepartmentTextField.setLayoutX(210);
            professorDepartmentTextField.setLayoutY(195);
            searchProfGroup.getChildren().addAll(menuBar, professorInformation, professorID, professorFirstName,
                    professorIDTextField, professorFirstNameTextField, professorLastName, professorLastNameTextField,
                    professorDepartment, searchProfessorButton, professorDepartmentTextField);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(searchProfScene);
        });

        addProfButton.setOnAction(a -> {
            Group addProfGroup = new Group();
            Scene addProfScene = new Scene(addProfGroup);
            Label professorInformation = new Label("New Professor Information");
            Label professorID = new Label("Professor ID");
            Label professorFirstName = new Label("First Name");
            Label professorLastName = new Label("Last Name");
            Label professorDepartment = new Label("Department");
            TextField professorIDTextField = new TextField();
            TextField professorFirstNameTextField = new TextField();
            TextField professorLastNameTextField = new TextField();
            Button addProfessorButton = new Button("Add");
            ComboBox<String> departmentBox = new ComboBox<String>();
            professorID.setLayoutX(120);
            professorID.setLayoutY(105);
            professorFirstName.setLayoutX(120);
            professorFirstName.setLayoutY(135);
            professorLastName.setLayoutX(120);
            professorLastName.setLayoutY(165);
            professorDepartment.setLayoutX(120);
            professorDepartment.setLayoutY(195);
            professorIDTextField.setLayoutX(210);
            professorIDTextField.setLayoutY(100);
            professorFirstNameTextField.setLayoutX(210);
            professorFirstNameTextField.setLayoutY(130);
            professorLastNameTextField.setLayoutX(210);
            professorLastNameTextField.setLayoutY(160);
            professorInformation.setLayoutX(230);
            professorInformation.setLayoutY(50);
            addProfessorButton.setLayoutX(380);
            addProfessorButton.setLayoutY(200);
            departmentBox.setLayoutX(220);
            departmentBox.setLayoutY(195);
            addProfessorButton.setOnAction(b -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String sql = "INSERT INTO instructorTable (instructorID, instructorFirst, instructorLast, deptID) VALUES (?, ?, ?, ?)";
                    String query_conversion = "SELECT departmentID FROM finalproject.departmentTable WHERE departmentName = ?";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    PreparedStatement pst2 = connection.prepareStatement(query_conversion);
                    pst2.setString(1, departmentBox.getValue());
                    pst2.execute();
                    ResultSet rs = pst2.executeQuery();
                    String dID = null;
                    if (rs.next()) {
                        dID = rs.getString("departmentID");
                    }
                    pst.setString(1, professorIDTextField.getText());
                    pst.setString(2, professorFirstNameTextField.getText());
                    pst.setString(3, professorLastNameTextField.getText());
                    pst.setString(4, dID);
                    pst.execute();
                    connection.close();
                    Stage ErrorStage = new Stage();
                    Group ErrorGroup = new Group();
                    Scene ErrorScene = new Scene(ErrorGroup);
                    Label errorLabel = new Label("\n Successfully Added");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    ErrorGroup.getChildren().addAll(okButton, errorLabel);
                    okButton.setOnAction(c -> {
                        ErrorStage.close();
                        professorIDTextField.clear();
                        professorFirstNameTextField.clear();
                        professorLastNameTextField.clear();
                    });
                    ErrorStage.setWidth(200);
                    ErrorStage.setHeight(150);
                    ErrorStage.setScene(ErrorScene);
                    ErrorStage.setResizable(false);
                    ErrorStage.show();
                } catch (SQLIntegrityConstraintViolationException e) {
                    Stage ErrorStage = new Stage();
                    Group ErrorGroup = new Group();
                    Scene ErrorScene = new Scene(ErrorGroup);
                    Label errorLabel = new Label("Error:\n You need to choose \na department.");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    ErrorGroup.getChildren().addAll(okButton, errorLabel);
                    okButton.setOnAction(c -> {
                        ErrorStage.close();
                    });
                    ErrorStage.setWidth(200);
                    ErrorStage.setHeight(150);
                    ErrorStage.setScene(ErrorScene);
                    ErrorStage.setResizable(false);
                    ErrorStage.show();
                } catch (SQLException e) {
                    if (professorIDTextField.getText().isEmpty() || professorIDTextField.getText().isEmpty()) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter a ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(d -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } else if (e.toString().contains("Incorrect integer value:")) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter \nONLY numbers for ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(f -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                    if (e.toString().startsWith("java.sql.SQLIntegrityConstraintViolationException")) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n A department with that \n ID exist already.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                }
            });
            departmentBox.setOnMouseClicked(d -> {
                Connection connection = null;
                try {
                    departmentBox.getItems().clear();
                    //connection = connectionFactory.connect();
                    String Query = "SELECT departmentName FROM departmentTable";
                    PreparedStatement pst = connection.prepareStatement(Query);
                    pst = connection.prepareStatement(Query);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        departmentBox.getItems().add(rs.getString("departmentName"));
                    }
                    pst.close();
                    rs.close();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            addProfGroup.getChildren().addAll(menuBar, professorInformation, professorID, professorFirstName,
                    professorIDTextField, professorFirstNameTextField, professorLastName, professorLastNameTextField,
                    professorDepartment, addProfessorButton, departmentBox);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(addProfScene);
        });

        editProfButton.setOnAction(a -> {
            Group editProfGroup = new Group();
            Scene editProfScene = new Scene(editProfGroup);
            Label professorInformation = new Label("Professor Information");
            Label professorID = new Label("Professor ID");
            Label professorFirstName = new Label("First Name");
            Label professorLastName = new Label("Last Name");
            Label professorDepartment = new Label("Department");
            TextField professorIDTextField = new TextField();
            TextField professorFirstNameTextField = new TextField();
            TextField professorLastNameTextField = new TextField();
            Button searchProfessorButton = new Button("Search");
            Button updateProfessorButton = new Button("Update");
            ComboBox<String> departmentBox = new ComboBox<>();
            professorID.setLayoutX(120);
            professorID.setLayoutY(105);
            professorFirstName.setLayoutX(120);
            professorFirstName.setLayoutY(135);
            professorLastName.setLayoutX(120);
            professorLastName.setLayoutY(165);
            professorDepartment.setLayoutX(120);
            professorDepartment.setLayoutY(195);
            professorIDTextField.setLayoutX(210);
            professorIDTextField.setLayoutY(100);
            professorFirstNameTextField.setLayoutX(210);
            professorFirstNameTextField.setLayoutY(130);
            professorLastNameTextField.setLayoutX(210);
            professorLastNameTextField.setLayoutY(160);
            professorInformation.setLayoutX(230);
            professorInformation.setLayoutY(50);
            updateProfessorButton.setLayoutX(380);
            updateProfessorButton.setLayoutY(200);
            searchProfessorButton.setLayoutX(400);
            searchProfessorButton.setLayoutY(100);
            searchProfessorButton.setOnAction(b -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String Query = "SELECT \r\n" + "instructorID, \r\n" + "instructorFirst,\r\n" + "instructorLast,\r\n"
                            + "(SELECT departmentName FROM finalproject.departmentTable d INNER JOIN finalproject.instructorTable i ON d.departmentID = i.deptID WHERE i.instructorID = ?) AS departmentName \r\n"
                            + " FROM finalproject.instructorTable where instructorID = ?";
                    PreparedStatement pst = connection.prepareStatement(Query);
                    pst.setString(1, professorIDTextField.getText());
                    pst.setString(2, professorIDTextField.getText());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        professorFirstNameTextField.setText(rs.getString("instructorFirst"));
                        professorLastNameTextField.setText(rs.getString("instructorLast"));
                        departmentBox.setValue(rs.getString("departmentName"));
                    } else {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n A instructor with that \n ID does not exist.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (SQLException e) {
                    if (professorIDTextField.getText().isEmpty() || professorIDTextField.getText().isEmpty()) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter a ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(d -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } else if (e.toString().contains("Incorrect integer value:")) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter \nONLY numbers for ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(f -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            departmentBox.setOnMouseClicked(d -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    departmentBox.getItems().clear();
                    String Query = "SELECT departmentName FROM departmentTable";
                    PreparedStatement pst = connection.prepareStatement(Query);
                    pst = connection.prepareStatement(Query);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        departmentBox.getItems().add(rs.getString("departmentName"));
                    }
                    pst.close();
                    rs.close();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            updateProfessorButton.setOnAction(e -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String Query = "UPDATE instructorTable SET instructorFirst=?, instructorLast=?, deptID=? where instructorID ="
                            + professorIDTextField.getText();
                    String query_conversion = "SELECT departmentID FROM finalproject.departmentTable WHERE departmentName = ?";
                    PreparedStatement pst = connection.prepareStatement(Query);
                    PreparedStatement pst2 = connection.prepareStatement(query_conversion);
                    pst2.setString(1, departmentBox.getValue());
                    ResultSet rs = pst2.executeQuery();
                    String dID = null;
                    if (rs.next()) {
                        dID = rs.getString("departmentID");
                    }
                    pst.setString(1, professorFirstNameTextField.getText());
                    pst.setString(2, professorLastNameTextField.getText());
                    pst.setString(3, dID);

                    pst.executeUpdate();
                    connection.close();
                    Stage SuccessStage = new Stage();
                    Group SuccessGroup = new Group();
                    Scene SuccessScene = new Scene(SuccessGroup);
                    Label errorLabel = new Label("\n Information successfully updated");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    SuccessGroup.getChildren().addAll(okButton, errorLabel);
                    okButton.setOnAction(d -> {
                        SuccessStage.close();
                        professorFirstNameTextField.clear();
                        professorLastNameTextField.clear();
                        departmentBox.getItems().clear();
                    });
                    SuccessStage.setWidth(200);
                    SuccessStage.setHeight(150);
                    SuccessStage.setScene(SuccessScene);
                    SuccessStage.setResizable(false);
                    SuccessStage.show();
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            });
            departmentBox.setLayoutX(220);
            departmentBox.setLayoutY(195);
            editProfGroup.getChildren().addAll(menuBar, professorInformation, professorID, professorFirstName,
                    professorIDTextField, professorFirstNameTextField, professorLastName, professorLastNameTextField,
                    professorDepartment, updateProfessorButton, searchProfessorButton, departmentBox);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(editProfScene);
        });

        deleteProfButton.setOnAction(a -> {
            Group deleteProfGroup = new Group();
            Scene deleteProfScene = new Scene(deleteProfGroup);
            Label professorInformation = new Label("Professor Information");
            Label professorID = new Label("Professor ID");
            Label professorFirstName = new Label("First Name");
            Label professorLastName = new Label("Last Name");
            Label professorDepartment = new Label("Department");
            TextField professorIDTextField = new TextField();
            TextField professorFirstNameTextField = new TextField();
            TextField professorLastNameTextField = new TextField();
            Button searchProfessorButton = new Button("Search");
            Button deleteProfessorButton = new Button("Delete");
            ComboBox<String> departmentBox = new ComboBox<>();
            professorID.setLayoutX(120);
            professorID.setLayoutY(105);
            professorFirstName.setLayoutX(120);
            professorFirstName.setLayoutY(135);
            professorLastName.setLayoutX(120);
            professorLastName.setLayoutY(165);
            professorDepartment.setLayoutX(120);
            professorDepartment.setLayoutY(195);
            professorIDTextField.setLayoutX(210);
            professorIDTextField.setLayoutY(100);
            professorFirstNameTextField.setLayoutX(210);
            professorFirstNameTextField.setLayoutY(130);
            professorLastNameTextField.setLayoutX(210);
            professorLastNameTextField.setLayoutY(160);
            professorInformation.setLayoutX(230);
            professorInformation.setLayoutY(50);
            deleteProfessorButton.setLayoutX(380);
            deleteProfessorButton.setLayoutY(200);
            searchProfessorButton.setLayoutX(400);
            searchProfessorButton.setLayoutY(100);
            departmentBox.setLayoutX(220);
            departmentBox.setLayoutY(195);
            searchProfessorButton.setOnAction(b -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String Query = "SELECT \r\n" + "instructorID, \r\n" + "instructorFirst,\r\n" + "instructorLast,\r\n"
                            + "(SELECT departmentName FROM finalproject.departmentTable d INNER JOIN finalproject.instructorTable i ON d.departmentID = i.deptID WHERE i.instructorID = ?) AS departmentName \r\n"
                            + " FROM finalproject.instructorTable where instructorID = ?";
                    PreparedStatement pst = connection.prepareStatement(Query);
                    pst.setString(1, professorIDTextField.getText());
                    pst.setString(2, professorIDTextField.getText());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        professorFirstNameTextField.setText(rs.getString("instructorFirst"));
                        professorLastNameTextField.setText(rs.getString("instructorLast"));
                        departmentBox.setValue(rs.getString("departmentName"));
                    } else {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n A instructor with that \n ID does not exist.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            deleteProfessorButton.setOnAction(c -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String sql = "DELETE FROM instructorTable WHERE instructorID = ?";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, professorIDTextField.getText());
                    pst.execute();
                    connection.close();
                    Stage SuccessStage = new Stage();
                    Group SuccessGroup = new Group();
                    Scene SuccessScene = new Scene(SuccessGroup);
                    Label errorLabel = new Label("\n Professor successfully deleted");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    SuccessGroup.getChildren().addAll(okButton, errorLabel);
                    okButton.setOnAction(d -> {
                        SuccessStage.close();
                        professorIDTextField.clear();
                        professorFirstNameTextField.clear();
                        professorLastNameTextField.clear();
                    });
                    SuccessStage.setWidth(200);
                    SuccessStage.setHeight(150);
                    SuccessStage.setScene(SuccessScene);
                    SuccessStage.setResizable(false);
                    SuccessStage.show();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            deleteProfGroup.getChildren().addAll(menuBar, professorInformation, professorID, professorFirstName,
                    professorIDTextField, professorFirstNameTextField, professorLastName, professorLastNameTextField,
                    professorDepartment, deleteProfessorButton, searchProfessorButton, departmentBox);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(deleteProfScene);
        });

        allProfessors.setOnAction(a -> {
            data = FXCollections.observableArrayList();
            Group allProfessorssGroup = new Group();
            Scene allProfessorssScene = new Scene(allProfessorssGroup);
            TableView table;
            table = new TableView<>();
            table.setLayoutX(40);
            table.setLayoutY(80);
            table.setPrefHeight(250);
            table.setPrefWidth(500);
            List<String> columnNames = new ArrayList<>();
            Label allProfessorLabel = new Label("Professor Information");
            columnNames.add("Professor ID");
            columnNames.add("Professor First");
            columnNames.add("Professor Last");
            columnNames.add("Department ID");
            columnNames.add("Department Name");
            for (int i = 0; i < columnNames.size(); i++) {
                // We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(columnNames.get(i));
                col.setCellValueFactory(
                        new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

                            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                                return new SimpleStringProperty(param.getValue().get(j).toString());
                            }
                        });

                table.getColumns().addAll(col);
            }
            try {
                //connection = connectionFactory.connect();

                String SELECT_DEPARTMENTS = "SELECT i.instructorID,\r\n" + "i.instructorFirst, \r\n"
                        + "i.instructorlast,\r\n" + "d.departmentID,\r\n" + "d.departmentName\r\n"
                        + "FROM finalproject.instructortable i \r\n"
                        + "INNER JOIN finalproject.departmenttable d ON d.departmentID = i.deptID";
                ResultSet rs = connection.createStatement().executeQuery(SELECT_DEPARTMENTS);
                while (rs.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        row.add(rs.getString(i));
                    }
                    data.add(row);
                }
                table.setItems(data);
                table.refresh();

            } catch (Exception e) {
                System.out.println(e.toString());
            }
            allProfessorLabel.setLayoutX(230);
            allProfessorLabel.setLayoutY(50);
            allProfessorssGroup.getChildren().addAll(menuBar, allProfessorLabel, table);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(allProfessorssScene);

        });
        /**
         *
         * THIS IS THE COURSE SECTION
         *
         */

        searchCourseButton.setOnAction(a -> {
            Group searchCourseGroup = new Group();
            Scene searchCourseScene = new Scene(searchCourseGroup);
            Label courseInformation = new Label("Course Information");
            Label courseIDLabel = new Label("Course ID");
            Label courseNameLabel = new Label("Course Name");
            Label courseProfessorLabel = new Label("Professor");
            Label courseDepartmentLabel = new Label("Department");
            TextField courseIDTextField = new TextField();
            TextField courseNameTextField = new TextField();
            TextField courseProfessorTextField = new TextField();
            TextField courseDepartmentTextField = new TextField();
            Button searchCoursesButton = new Button("Search");
            courseInformation.setLayoutX(230);
            courseInformation.setLayoutY(50);
            courseIDLabel.setLayoutX(120);
            courseIDLabel.setLayoutY(135);
            courseIDTextField.setLayoutX(200);
            courseIDTextField.setLayoutY(130);
            courseNameTextField.setLayoutX(200);
            courseNameTextField.setLayoutY(165);
            courseNameLabel.setLayoutX(120);
            courseNameLabel.setLayoutY(165);
            courseProfessorLabel.setLayoutX(120);
            courseProfessorLabel.setLayoutY(195);
            courseProfessorTextField.setLayoutX(200);
            courseProfessorTextField.setLayoutY(195);
            courseDepartmentLabel.setLayoutX(120);
            courseDepartmentLabel.setLayoutY(225);
            courseDepartmentTextField.setLayoutX(200);
            courseDepartmentTextField.setLayoutY(225);
            searchCoursesButton.setLayoutX(380);
            searchCoursesButton.setLayoutY(130);
            searchCoursesButton.setOnAction(b -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    if (courseIDTextField.getText().isEmpty() || courseIDTextField.getText().isEmpty()) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter a ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                    String SEARCH_COURSE = "SELECT c.courseName, \r\n" + "i.instructorFirst, \r\n"
                            + "d.departmentName\r\n" + "FROM finalproject.coursetable c\r\n"
                            + "INNER JOIN finalproject.instructortable i ON i.instructorID = c.profID\r\n"
                            + "INNER JOIN finalproject.departmenttable d ON d.departmentID = c.deptID\r\n"
                            + "WHERE c.courseID = ?";
                    PreparedStatement pst = connection.prepareStatement(SEARCH_COURSE);
                    pst.setString(1, courseIDTextField.getText());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameTextField.setText(rs.getString("courseName"));
                        courseProfessorTextField.setText(rs.getString("instructorFirst"));
                        courseDepartmentTextField.setText(rs.getString("departmentName"));
                    }
                } catch (SQLException e) {
                    if (e.toString().contains("Incorrect integer value:")) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Please enter \nONLY numbers for ID. ");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            searchCourseGroup.getChildren().addAll(menuBar, courseInformation, courseIDLabel, courseNameLabel,
                    courseProfessorLabel, courseDepartmentLabel, courseIDTextField, searchCoursesButton,
                    courseNameTextField, courseProfessorTextField, courseDepartmentTextField);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(searchCourseScene);
        });

        addCourseButton.setOnAction(a -> {
            Group addCourseGroup = new Group();
            Scene addCourseScene = new Scene(addCourseGroup);
            Label courseInformation = new Label("New Course Information");
            Label courseIDLabel = new Label("Course ID");
            Label courseNameLabel = new Label("Course Name");
            Label courseProfessorLabel = new Label("Professor");
            Label courseDepartmentLabel = new Label("Department");
            TextField courseIDTextField = new TextField();
            TextField courseNameTextField = new TextField();
            ComboBox<String> courseDepartmentTextField = new ComboBox<>();
            ComboBox<String> courseProfessorTextField = new ComboBox<>();
            Button addCoursesButton = new Button("Add");
            courseInformation.setLayoutX(230);
            courseInformation.setLayoutY(50);
            courseIDLabel.setLayoutX(120);
            courseIDLabel.setLayoutY(135);
            courseIDTextField.setLayoutX(200);
            courseIDTextField.setLayoutY(130);
            courseNameTextField.setLayoutX(200);
            courseNameTextField.setLayoutY(165);
            courseNameLabel.setLayoutX(120);
            courseNameLabel.setLayoutY(165);
            courseDepartmentLabel.setLayoutX(120);
            courseDepartmentLabel.setLayoutY(195);
            courseDepartmentTextField.setLayoutX(200);
            courseDepartmentTextField.setLayoutY(195);
            courseProfessorLabel.setLayoutX(120);
            courseProfessorLabel.setLayoutY(225);
            courseProfessorTextField.setLayoutX(200);
            courseProfessorTextField.setLayoutY(225);
            addCoursesButton.setLayoutX(380);
            addCoursesButton.setLayoutY(250);

            try {

                addCoursesButton.setOnAction(b -> {
                    String INSERT_COURSE_QUERY = "INSERT INTO courseTable (courseID, courseName, deptID, profID) VALUES (?, ?, ?, ?)";
                    String QUERY_CONVERSION = "SELECT departmentID FROM finalproject.departmentTable WHERE departmentName = ?";
                    String PROFESSOR_CONVERSION = "SELECT instructorID FROM finalproject.instructorTable where instructorFirst = ?";
                    PreparedStatement pst;
                    PreparedStatement pst2;
                    PreparedStatement pst3;

                    try {
                        //Connection connection = connectionFactory.connect();

                        pst2 = connection.prepareStatement(QUERY_CONVERSION);
                        pst2.setString(1, courseDepartmentTextField.getValue());
                        pst2.execute();
                        ResultSet rs = pst2.executeQuery();
                        String dID = null;
                        if (rs.next()) {
                            dID = rs.getString("departmentID");
                        }
                        pst3 = connection.prepareStatement(PROFESSOR_CONVERSION);
                        pst3.setString(1, courseProfessorTextField.getValue());
                        pst3.execute();
                        rs = pst3.executeQuery();
                        String pID = null;
                        if (rs.next()) {
                            pID = rs.getString("instructorID");
                        }
                        pst = connection.prepareStatement(INSERT_COURSE_QUERY);
                        pst.setString(1, courseIDTextField.getText());
                        pst.setString(2, courseNameTextField.getText());
                        pst.setString(3, dID);
                        pst.setString(4, pID);
                        pst.execute();
                        connection.close();
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("\n Successfully Added");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                            courseIDTextField.clear();
                            courseNameTextField.clear();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } catch (SQLIntegrityConstraintViolationException e) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Department nor \nProfessor cannot be empty.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } catch (SQLException e) {
                        if (courseIDTextField.getText().isEmpty() || courseIDTextField.getText().isEmpty()) {
                            Stage ErrorStage = new Stage();
                            Group ErrorGroup = new Group();
                            Scene ErrorScene = new Scene(ErrorGroup);
                            Label errorLabel = new Label("Error:\n Please enter a ID. ");
                            Button okButton = new Button("Ok");
                            errorLabel.setLayoutX(10);
                            okButton.setLayoutX(70);
                            okButton.setLayoutY(60);
                            ErrorGroup.getChildren().addAll(okButton, errorLabel);
                            okButton.setOnAction(c -> {
                                ErrorStage.close();
                            });
                            ErrorStage.setWidth(200);
                            ErrorStage.setHeight(150);
                            ErrorStage.setScene(ErrorScene);
                            ErrorStage.setResizable(false);
                            ErrorStage.show();
                        } else if (e.toString().contains("Incorrect integer value:")) {
                            Stage ErrorStage = new Stage();
                            Group ErrorGroup = new Group();
                            Scene ErrorScene = new Scene(ErrorGroup);
                            Label errorLabel = new Label("Error:\n Please enter \nONLY numbers for ID. ");
                            Button okButton = new Button("Ok");
                            errorLabel.setLayoutX(10);
                            okButton.setLayoutX(70);
                            okButton.setLayoutY(60);
                            ErrorGroup.getChildren().addAll(okButton, errorLabel);
                            okButton.setOnAction(c -> {
                                ErrorStage.close();
                            });
                            ErrorStage.setWidth(200);
                            ErrorStage.setHeight(150);
                            ErrorStage.setScene(ErrorScene);
                            ErrorStage.setResizable(false);
                            ErrorStage.show();
                        }
                        System.out.println(e.toString());
                    }
                });
            } catch (Exception e) {

            }

            try {
                //Connection connection = connectionFactory.connect();
                courseDepartmentTextField.getItems().clear();
                courseProfessorTextField.getItems().clear();
                String Query = "SELECT departmentName FROM departmentTable";
                PreparedStatement pst = connection.prepareStatement(Query);
                pst = connection.prepareStatement(Query);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    courseDepartmentTextField.getItems().add(rs.getString("departmentName"));
                }
                pst.close();
                rs.close();
                connection.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            courseDepartmentTextField.setOnAction(d -> {
                try {
                    //Connection connection = connectionFactory.connect();
                    courseProfessorTextField.getItems().clear();
                    String professorComboBox_Query = "SELECT i.instructorFirst\r\n"
                            + " FROM finalproject.departmentTable d\r\n"
                            + " INNER JOIN  finalproject.instructorTable i \r\n" + " ON d.departmentID = i.deptID\r\n"
                            + " where d.departmentName = ?";
                    PreparedStatement pst = connection.prepareStatement(professorComboBox_Query);
                    pst.setString(1, courseDepartmentTextField.getValue());
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        courseProfessorTextField.getItems().add(rs.getString("instructorFirst"));
                    }
                    if (courseProfessorTextField.getItems().isEmpty()) {
                        courseProfessorTextField.setDisable(true);
                    } else {
                        courseProfessorTextField.setDisable(false);
                    }
                    pst.close();
                    rs.close();
                    connection.close();
                } catch (Exception e) {

                }
            });

            addCourseGroup.getChildren().addAll(menuBar, courseInformation, courseIDLabel, courseNameLabel,
                    courseProfessorLabel, courseDepartmentLabel, courseIDTextField, addCoursesButton,
                    courseNameTextField, courseProfessorTextField, courseDepartmentTextField);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(addCourseScene);
        });

        editCourseButton.setOnAction(a -> {
            Group editCourseGroup = new Group();
            Scene editCourseScene = new Scene(editCourseGroup);
            Label courseInformation = new Label("Edit Course Information");
            Label courseIDLabel = new Label("Course ID");
            Label courseNameLabel = new Label("Course Name");
            Label courseProfessorLabel = new Label("Professor");
            Label courseDepartmentLabel = new Label("Department");
            TextField courseIDTextField = new TextField();
            TextField courseNameTextField = new TextField();
            ChoiceBox<String> courseDepartmentTextField = new ChoiceBox<>();
            ChoiceBox<String> courseProfessorTextField = new ChoiceBox<>();
            Button searchCoursesButton = new Button("Search");
            Button updateCoursesButton = new Button("Update");
            courseInformation.setLayoutX(230);
            courseInformation.setLayoutY(50);
            courseIDLabel.setLayoutX(120);
            courseIDLabel.setLayoutY(135);
            courseIDTextField.setLayoutX(200);
            courseIDTextField.setLayoutY(130);
            courseNameTextField.setLayoutX(200);
            courseNameTextField.setLayoutY(165);
            courseNameLabel.setLayoutX(120);
            courseNameLabel.setLayoutY(165);
            courseProfessorLabel.setLayoutX(120);
            courseProfessorLabel.setLayoutY(195);
            courseProfessorTextField.setLayoutX(200);
            courseProfessorTextField.setLayoutY(195);
            courseDepartmentLabel.setLayoutX(120);
            courseDepartmentLabel.setLayoutY(225);
            courseDepartmentTextField.setLayoutX(200);
            courseDepartmentTextField.setLayoutY(225);
            searchCoursesButton.setLayoutX(380);
            searchCoursesButton.setLayoutY(130);
            updateCoursesButton.setLayoutX(380);
            updateCoursesButton.setLayoutY(250);
            searchCoursesButton.setOnAction(b -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    courseDepartmentTextField.getItems().clear();
                    courseProfessorTextField.getItems().clear();
                    String SEARCH_COURSE = "SELECT c.courseName, \r\n" + "i.instructorFirst, \r\n"
                            + "d.departmentName\r\n" + "FROM finalproject.coursetable c\r\n"
                            + "INNER JOIN finalproject.instructortable i ON i.instructorID = c.profID\r\n"
                            + "INNER JOIN finalproject.departmenttable d ON d.departmentID = c.deptID\r\n"
                            + "WHERE c.courseID =" + courseIDTextField.getText();
                    PreparedStatement pst = connection.prepareStatement(SEARCH_COURSE);
                    ResultSet rs = pst.executeQuery();
                    String Query = "SELECT departmentName FROM departmentTable";
                    PreparedStatement pst2 = connection.prepareStatement(Query);
                    pst = connection.prepareStatement(Query);
                    ResultSet rs2 = pst2.executeQuery();
                    courseIDTextField.setDisable(true);
                    while (rs2.next()) {
                        courseDepartmentTextField.getItems().add(rs2.getString("departmentName"));
                    }
                    if (rs.next()) {
                        courseNameTextField.setText(rs.getString("courseName"));
                        courseDepartmentTextField.setValue(rs.getString("departmentName"));
                        courseProfessorTextField.setValue(rs.getString("instructorFirst"));
                    } else if (rs.next() == false) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Course ID\n does not exist.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                    pst.close();
                    rs.close();
                    pst2.close();
                    rs2.close();
                } catch (SQLSyntaxErrorException e) {
                    Stage ErrorStage = new Stage();
                    Group ErrorGroup = new Group();
                    Scene ErrorScene = new Scene(ErrorGroup);
                    Label errorLabel = new Label("Error:\n Please use numbers.");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    ErrorGroup.getChildren().addAll(okButton, errorLabel);
                    okButton.setOnAction(c -> {
                        ErrorStage.close();
                    });
                    ErrorStage.setWidth(200);
                    ErrorStage.setHeight(150);
                    ErrorStage.setScene(ErrorScene);
                    ErrorStage.setResizable(false);
                    ErrorStage.show();

                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            courseDepartmentTextField.setOnAction(d -> {
                try {
                    //Connection connection = connectionFactory.connect();
                    courseProfessorTextField.getItems().clear();
                    String professorComboBox_Query = "SELECT i.instructorFirst\r\n"
                            + " FROM finalproject.departmentTable d\r\n"
                            + " INNER JOIN  finalproject.instructorTable i \r\n" + " ON d.departmentID = i.deptID\r\n"
                            + " where d.departmentName = ?";
                    PreparedStatement pst = connection.prepareStatement(professorComboBox_Query);
                    pst.setString(1, courseDepartmentTextField.getValue());
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        courseProfessorTextField.getItems().add(rs.getString("instructorFirst"));
                    }
                    if (courseProfessorTextField.getItems().isEmpty()) {
                        courseProfessorTextField.setDisable(true);
                    } else {
                        courseProfessorTextField.setDisable(false);
                    }
                    pst.close();
                    rs.close();
                    connection.close();
                } catch (Exception e) {

                }
            });
            updateCoursesButton.setOnAction(e -> {
                try {
                    //Connection connection = connectionFactory.connect();
                    String UPDATE_COURSE = "UPDATE finalproject.coursetable SET courseName = ?, deptID = ?, profID = ? WHERE courseID ="
                            + courseIDTextField.getText();
                    String QUERY_CONVERSION = "SELECT departmentID FROM finalproject.departmentTable WHERE departmentName = ?";
                    String PROFESSOR_CONVERSION = "SELECT instructorID FROM finalproject.instructorTable where instructorFirst = ?";
                    PreparedStatement pst;
                    PreparedStatement pst2;
                    PreparedStatement pst3;
                    pst2 = connection.prepareStatement(QUERY_CONVERSION);
                    pst2.setString(1, courseDepartmentTextField.getValue());
                    ResultSet rs = pst2.executeQuery();
                    String dID = null;
                    if (rs.next()) {
                        dID = rs.getString("departmentID");
                    }
                    pst2.close();
                    pst3 = connection.prepareStatement(PROFESSOR_CONVERSION);
                    pst3.setString(1, courseProfessorTextField.getValue());
                    rs = pst3.executeQuery();
                    String pID = null;
                    if (rs.next()) {
                        pID = rs.getString("instructorID");
                    }
                    pst3.close();
                    pst = connection.prepareStatement(UPDATE_COURSE);
                    pst.setString(1, courseNameTextField.getText());
                    pst.setString(2, dID);
                    pst.setString(3, pID);
                    pst.executeUpdate();
                    pst.close();
                    connection.close();
                    Stage SuccessStage = new Stage();
                    Group SuccessGroup = new Group();
                    Scene SuccessScene = new Scene(SuccessGroup);
                    Label errorLabel = new Label("\n Information successfully updated");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    SuccessGroup.getChildren().addAll(okButton, errorLabel);

                    okButton.setOnAction(f -> {
                        SuccessStage.close();
                        courseIDTextField.clear();
                        courseNameTextField.clear();
                        courseProfessorTextField.getItems().clear();
                        courseDepartmentTextField.getItems().clear();
                        courseIDTextField.setDisable(false);
                    });
                    SuccessStage.setWidth(200);
                    SuccessStage.setHeight(150);
                    SuccessStage.setScene(SuccessScene);
                    SuccessStage.setResizable(false);
                    SuccessStage.show();
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            });
            editCourseGroup.getChildren().addAll(menuBar, courseInformation, courseIDLabel, courseNameLabel,
                    courseProfessorLabel, courseDepartmentLabel, courseIDTextField, searchCoursesButton,
                    courseNameTextField, courseProfessorTextField, courseDepartmentTextField, updateCoursesButton);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(editCourseScene);
        });

        addEnrollmentButton.setOnAction(a -> {
            Group addEnrollmentGroup = new Group();
            Scene addEnrollmentScene = new Scene(addEnrollmentGroup);
            Label enrollmentInformation = new Label("New Enrollment Information");
            Label studentIDLabel = new Label("Student ID");
            Label studentNameLabel = new Label("Student Name");
            Label courseIDLabel = new Label("Course ID");
            Label courseNameLabel = new Label("Course Name");
            Label enrollmentSemester = new Label("Semester");
            Label enrollmentYear = new Label("Year");
            TextField studentIDTextField = new TextField();
            TextField studentNameTextField = new TextField();
            TextField courseIDTextField = new TextField();
            TextField courseNameTextField = new TextField();
            Button searchStudentIDButton = new Button("Search Student");
            Button searchCourseIDButton = new Button("Search Course");
            ChoiceBox<String> semesterChoiceBox = new ChoiceBox<>();
            semesterChoiceBox.getItems().add("Spring");
            semesterChoiceBox.getItems().add("Summer");
            semesterChoiceBox.getItems().add("Fall");
            semesterChoiceBox.getItems().add("Winter");
            ChoiceBox<String> yearChoiceBox = new ChoiceBox<>();
            yearChoiceBox.getItems().add("2015");
            yearChoiceBox.getItems().add("2016");
            yearChoiceBox.getItems().add("2017");
            yearChoiceBox.getItems().add("2018");
            yearChoiceBox.getItems().add("2019");
            yearChoiceBox.getItems().add("2020");
            yearChoiceBox.getItems().add("2021");
            Button addEnrollmentsButton = new Button("Add Enrollment");
            enrollmentInformation.setLayoutX(230);
            enrollmentInformation.setLayoutY(50);
            studentIDTextField.setLayoutX(190);
            studentIDTextField.setLayoutY(100);
            studentNameTextField.setLayoutX(190);
            studentNameTextField.setLayoutY(130);
            courseIDTextField.setLayoutX(190);
            courseIDTextField.setLayoutY(160);
            courseNameTextField.setLayoutX(190);
            courseNameTextField.setLayoutY(190);
            studentIDLabel.setLayoutX(120);
            studentIDLabel.setLayoutY(105);
            courseIDLabel.setLayoutX(120);
            courseIDLabel.setLayoutY(165);
            studentNameLabel.setLayoutX(110);
            studentNameLabel.setLayoutY(135);
            courseNameLabel.setLayoutX(110);
            courseNameLabel.setLayoutY(195);
            enrollmentSemester.setLayoutX(140);
            enrollmentSemester.setLayoutY(230);
            enrollmentYear.setLayoutX(300);
            enrollmentYear.setLayoutY(230);
            searchStudentIDButton.setLayoutX(350);
            searchStudentIDButton.setLayoutY(98);
            searchCourseIDButton.setLayoutX(350);
            searchCourseIDButton.setLayoutY(160);
            semesterChoiceBox.setLayoutX(140);
            semesterChoiceBox.setLayoutY(250);
            yearChoiceBox.setLayoutX(300);
            yearChoiceBox.setLayoutY(250);
            addEnrollmentsButton.setLayoutX(400);
            addEnrollmentsButton.setLayoutY(300);
            searchStudentIDButton.setOnAction(b -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String Query = "SELECT * FROM studentTable where studentID =" + studentIDTextField.getText();
                    PreparedStatement pst = connection.prepareStatement(Query);
                    ResultSet rs = pst.executeQuery(Query);
                    if (rs.next()) {
                        studentNameTextField.setText(rs.getString("studentFirst"));
                    } else {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n A student with that \n ID does not exist.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (SQLSyntaxErrorException e) {

                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            searchCourseIDButton.setOnAction(c -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String Query = "SELECT * FROM courseTable where courseID =" + courseIDTextField.getText();
                    PreparedStatement pst = connection.prepareStatement(Query);
                    ResultSet rs = pst.executeQuery(Query);
                    if (rs.next()) {
                        courseNameTextField.setText(rs.getString("courseName"));
                    } else {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n A course with that \n ID does not exist.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(d -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            addEnrollmentsButton.setOnAction(d -> {
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String INSERT_ENROLLMENT = "INSERT INTO enrollmentTable (studentID, courseID, semester, year, grade) VALUES(?, ?, ?, ?, ?)";
                    PreparedStatement pst = connection.prepareStatement(INSERT_ENROLLMENT);
                    pst.setString(1, studentIDTextField.getText());
                    pst.setString(2, courseIDTextField.getText());
                    pst.setString(3, semesterChoiceBox.getValue());
                    pst.setString(4, yearChoiceBox.getValue());
                    pst.setString(5, "X");
                    pst.execute();
                    connection.close();
                    Stage SuccessStage = new Stage();
                    Group SuccessGroup = new Group();
                    Scene SuccessScene = new Scene(SuccessGroup);
                    Label errorLabel = new Label("\n Information successfully updated");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    SuccessGroup.getChildren().addAll(okButton, errorLabel);

                    okButton.setOnAction(e -> {
                        SuccessStage.close();
                        studentIDTextField.clear();
                        studentNameTextField.clear();
                        courseIDTextField.clear();
                        courseNameTextField.clear();
                        semesterChoiceBox.getItems().clear();
                        yearChoiceBox.getItems().clear();
                    });
                    SuccessStage.setWidth(200);
                    SuccessStage.setHeight(150);
                    SuccessStage.setScene(SuccessScene);
                    SuccessStage.setResizable(false);
                    SuccessStage.show();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            addEnrollmentGroup.getChildren().addAll(menuBar, enrollmentInformation, studentIDLabel, studentIDTextField,
                    studentNameLabel, studentNameTextField, courseIDLabel, courseIDTextField, courseNameLabel,
                    courseNameTextField, searchStudentIDButton, searchCourseIDButton, enrollmentSemester,
                    semesterChoiceBox, enrollmentYear, yearChoiceBox, addEnrollmentsButton);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(addEnrollmentScene);
        });

        editEnrollmentButton.setOnAction(a -> {
            Group editnrollmentGroup = new Group();
            Scene editEnrollmentScene = new Scene(editnrollmentGroup);
            Label studentIDLabel = new Label("Student ID");
            Label courseIDLabel = new Label("Course ID");
            TextField studentIDTextField = new TextField();
            TextField courseIDTextField = new TextField();
            Button loadStudentEnrollment = new Button("Load");
            Button dropStudentEnrollment = new Button("Drop");
            TableView table;
            table = new TableView<>();
            studentIDLabel.setLayoutX(50);
            studentIDLabel.setLayoutY(50);
            courseIDLabel.setLayoutX(300);
            courseIDLabel.setLayoutY(50);
            studentIDTextField.setLayoutX(120);
            studentIDTextField.setLayoutY(45);
            studentIDTextField.setPrefWidth(40);
            courseIDTextField.setLayoutX(360);
            courseIDTextField.setLayoutY(45);
            courseIDTextField.setPrefWidth(40);
            loadStudentEnrollment.setLayoutX(180);
            loadStudentEnrollment.setLayoutY(45);
            dropStudentEnrollment.setLayoutX(410);
            dropStudentEnrollment.setLayoutY(45);
            table.setPrefHeight(280);
            table.setPrefWidth(570);
            table.setLayoutX(10);
            table.setLayoutY(80);
            List<String> columnNames = new ArrayList<>();
            columnNames.add("Student ID");
            columnNames.add("Student Name");
            columnNames.add("Course Name");
            columnNames.add("Course ID");
            columnNames.add("Semester");
            columnNames.add("Year");
            columnNames.add("Grade");
            for (int i = 0; i < columnNames.size(); i++) {
                // We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(columnNames.get(i));
                col.setCellValueFactory(
                        new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                                return new SimpleStringProperty(param.getValue().get(j).toString());
                            }
                        });

                table.getColumns().addAll(col);
            }
            loadStudentEnrollment.setOnAction(b -> {
                data = FXCollections.observableArrayList();
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String SELECT_STUDENT_ENROLLMENT = "SELECT e.studentID,\r\n" + "s.studentFirst, \r\n"
                            + "c.courseName, \r\n" + "e.courseID, \r\n" + "e.semester,\r\n" + "e.year,\r\n"
                            + "e.grade\r\n" + "FROM finalproject.enrollmenttable e\r\n"
                            + "INNER JOIN finalproject.studenttable s ON s.studentID = e.studentID\r\n"
                            + "INNER JOIN finalproject.coursetable c ON c.courseID = e.courseID\r\n"
                            + "WHERE e.studentID =" + studentIDTextField.getText();
                    ResultSet rs = connection.createStatement().executeQuery(SELECT_STUDENT_ENROLLMENT);
                    while (rs.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row.add(rs.getString(i));
                        }
                        data.add(row);
                    }
                    table.setItems(data);
                    table.refresh();

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            });
            dropStudentEnrollment.setOnAction(c -> {
                try {
                    //connection = connectionFactory.connect();
                    String DELETE_ENROLLMENT = "DELETE FROM finalproject.enrollmenttable\r\n" + "WHERE courseID ="
                            + courseIDTextField.getText() + " AND studentID =" + studentIDTextField.getText();
                    PreparedStatement pst = connection.prepareStatement(DELETE_ENROLLMENT);
                    pst.execute();
                    Stage SuccessStage = new Stage();
                    Group SuccessGroup = new Group();
                    Scene SuccessScene = new Scene(SuccessGroup);
                    Label errorLabel = new Label("\n Information successfully updated");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    SuccessGroup.getChildren().addAll(okButton, errorLabel);
                    okButton.setOnAction(d -> {
                        try {
                            table.getItems().clear();
                            String SELECT_STUDENT_ENROLLMENT = "SELECT e.studentID,\r\n" + "s.studentFirst, \r\n"
                                    + "c.courseName, \r\n" + "e.courseID, \r\n" + "e.semester,\r\n" + "e.year,\r\n"
                                    + "e.grade\r\n" + "FROM finalproject.enrollmenttable e\r\n"
                                    + "INNER JOIN finalproject.studenttable s ON s.studentID = e.studentID\r\n"
                                    + "INNER JOIN finalproject.coursetable c ON c.courseID = e.courseID\r\n"
                                    + "WHERE e.studentID =" + studentIDTextField.getText();
                            ResultSet rs = connection.createStatement().executeQuery(SELECT_STUDENT_ENROLLMENT);
                            while (rs.next()) {
                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                                    row.add(rs.getString(i));
                                }
                                data.add(row);
                            }
                            table.setItems(data);
                            table.refresh();
                        } catch (Exception e) {

                        }
                        SuccessStage.close();

                    });
                    SuccessStage.setWidth(200);
                    SuccessStage.setHeight(150);
                    SuccessStage.setScene(SuccessScene);
                    SuccessStage.setResizable(false);
                    SuccessStage.show();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            editnrollmentGroup.getChildren().addAll(menuBar, studentIDLabel, courseIDLabel, studentIDTextField,
                    courseIDTextField, loadStudentEnrollment, dropStudentEnrollment, table);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(editEnrollmentScene);
        });

        /**
         *
         * THIS IS FOR ADDING GRADES BY STUDENT AND COURSE
         *
         */

        studentGrades.setOnAction(a -> {
            Group addStudentGradesGroup = new Group();
            Scene addStudentGradesScene = new Scene(addStudentGradesGroup);
            Label studentIDLabel = new Label("Student ID");
            TextField studentIDTextField = new TextField();
            Label courseIDLabel = new Label("Course ID");
            TextField courseIDTextField = new TextField();
            Label studentGradeLabel = new Label("Add Grade");
            TextField studentGradeTextField = new TextField();
            Button searchStudentButton = new Button("Search");
            Button addStudentGradeButton = new Button("Add Grade");
            TableView table;
            table = new TableView();
            table.setPrefHeight(280);
            table.setPrefWidth(570);
            table.setLayoutX(10);
            table.setLayoutY(80);
            studentIDLabel.setLayoutX(10);
            studentIDLabel.setLayoutY(40);
            courseIDLabel.setLayoutX(170);
            courseIDLabel.setLayoutY(40);
            studentIDTextField.setLayoutX(70);
            studentIDTextField.setLayoutY(35);
            studentIDTextField.setPrefWidth(40);
            courseIDTextField.setLayoutX(225);
            courseIDTextField.setLayoutY(35);
            courseIDTextField.setPrefWidth(40);
            studentGradeLabel.setLayoutX(290);
            studentGradeLabel.setLayoutY(40);
            studentGradeTextField.setLayoutX(350);
            studentGradeTextField.setLayoutY(35);
            studentGradeTextField.setPrefWidth(40);
            searchStudentButton.setLayoutX(115);
            searchStudentButton.setLayoutY(35);
            addStudentGradeButton.setLayoutX(395);
            addStudentGradeButton.setLayoutY(35);
            List<String> columnNames = new ArrayList<>();
            columnNames.add("Student ID");
            columnNames.add("Student Name");
            columnNames.add("Course Name");
            columnNames.add("Course ID");
            columnNames.add("Semester");
            columnNames.add("Year");
            columnNames.add("Grade");
            for (int i = 0; i < columnNames.size(); i++) {
                // We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(columnNames.get(i));
                col.setCellValueFactory(
                        new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                                return new SimpleStringProperty(param.getValue().get(j).toString());
                            }
                        });

                table.getColumns().addAll(col);
            }
            searchStudentButton.setOnAction(b -> {
                data = FXCollections.observableArrayList();
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String SELECT_STUDENT_ENROLLMENT = "SELECT e.studentID, \r\n" + "s.studentFirst, \r\n"
                            + "c.courseName, \r\n" + "e.courseID, \r\n" + "e.semester, \r\n" + "e.year, \r\n"
                            + "e.grade\r\n" + "FROM finalproject.enrollmenttable e\r\n"
                            + "INNER JOIN finalproject.studenttable s ON s.studentID = e.studentID\r\n"
                            + "INNER JOIN finalproject.coursetable c ON c.courseID = e.courseID\r\n"
                            + "WHERE e.studentID =" + studentIDTextField.getText();
                    ResultSet rs = connection.createStatement().executeQuery(SELECT_STUDENT_ENROLLMENT);
                    while (rs.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row.add(rs.getString(i));
                        }
                        data.add(row);
                    }
                    if (data.isEmpty()) {
                        table.getItems().clear();
                        table.refresh();
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Student/enrollment does not exist.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } else {
                        table.setItems(data);
                        table.refresh();
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            });
            addStudentGradeButton.setOnAction(c -> {
                try {
                    //connection = connectionFactory.connect();
                    String UPDATE_GRADE_QUERY = "UPDATE finalproject.enrollmenttable SET grade = ? WHERE studentID ="
                            + studentIDTextField.getText() + " AND courseID =" + courseIDTextField.getText();
                    PreparedStatement pst = connection.prepareStatement(UPDATE_GRADE_QUERY);
                    pst.setString(1, studentGradeTextField.getText());
                    pst.execute();
                    Stage SuccessStage = new Stage();
                    Group SuccessGroup = new Group();
                    Scene SuccessScene = new Scene(SuccessGroup);
                    Label errorLabel = new Label("\n Information successfully updated");
                    Button okButton = new Button("Ok");
                    errorLabel.setLayoutX(10);
                    okButton.setLayoutX(70);
                    okButton.setLayoutY(60);
                    SuccessGroup.getChildren().addAll(okButton, errorLabel);

                    okButton.setOnAction(d -> {
                        try {
                            table.getItems().clear();
                            String SELECT_STUDENT_ENROLLMENT = "SELECT e.studentID, \r\n" + "s.studentFirst, \r\n"
                                    + "c.courseName, \r\n" + "e.courseID, \r\n" + "e.semester, \r\n" + "e.year, \r\n"
                                    + "e.grade\r\n" + "FROM finalproject.enrollmenttable e\r\n"
                                    + "INNER JOIN finalproject.studenttable s ON s.studentID = e.studentID\r\n"
                                    + "INNER JOIN finalproject.coursetable c ON c.courseID = e.courseID\r\n"
                                    + "WHERE e.studentID =" + studentIDTextField.getText();
                            ResultSet rs = connection.createStatement().executeQuery(SELECT_STUDENT_ENROLLMENT);
                            while (rs.next()) {
                                ObservableList<String> row = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                                    row.add(rs.getString(i));
                                }
                                data.add(row);
                            }
                            table.setItems(data);
                            table.refresh();
                            connection.close();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                        SuccessStage.close();
                        studentIDTextField.clear();
                        studentGradeTextField.clear();
                        courseIDTextField.clear();

                    });
                    SuccessStage.setWidth(200);
                    SuccessStage.setHeight(150);
                    SuccessStage.setScene(SuccessScene);
                    SuccessStage.setResizable(false);
                    SuccessStage.show();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            addStudentGradesGroup.getChildren().addAll(menuBar, table, studentIDLabel, studentIDTextField,
                    courseIDLabel, courseIDTextField, studentGradeLabel, studentGradeTextField, searchStudentButton,
                    addStudentGradeButton);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(addStudentGradesScene);
        });

        courseGrades.setOnAction(a ->

        {
            Group addCourseGradesGroup = new Group();
            Scene addCourseGradesScene = new Scene(addCourseGradesGroup);
            Label courseIDLabel = new Label("Course ID");
            TextField studentIDTextField = new TextField();
            Label studentIDLabel = new Label("Student ID");
            TextField courseIDTextField = new TextField();
            Label studentGradeLabel = new Label("Add Grade");
            TextField studentGradeTextField = new TextField();
            Button searchCourseIDButton = new Button("Search");
            Button addCourseGradeButton = new Button("Add Grade");
            TableView table;
            table = new TableView<>();
            table.setPrefHeight(280);
            table.setPrefWidth(570);
            table.setLayoutX(10);
            table.setLayoutY(80);
            studentIDLabel.setLayoutX(170);
            studentIDLabel.setLayoutY(40);
            courseIDLabel.setLayoutX(10);
            courseIDLabel.setLayoutY(40);
            studentIDTextField.setLayoutX(230);
            studentIDTextField.setLayoutY(35);
            studentIDTextField.setPrefWidth(40);
            courseIDTextField.setLayoutX(70);
            courseIDTextField.setLayoutY(35);
            courseIDTextField.setPrefWidth(40);
            studentGradeLabel.setLayoutX(290);
            studentGradeLabel.setLayoutY(40);
            studentGradeTextField.setLayoutX(350);
            studentGradeTextField.setLayoutY(35);
            studentGradeTextField.setPrefWidth(40);
            searchCourseIDButton.setLayoutX(115);
            searchCourseIDButton.setLayoutY(35);
            addCourseGradeButton.setLayoutX(395);
            addCourseGradeButton.setLayoutY(35);
            List<String> columnNames = new ArrayList<>();
            columnNames.add("Course ID");
            columnNames.add("Course Name");
            columnNames.add("Student ID");
            columnNames.add("Student Name");
            columnNames.add("Semester");
            columnNames.add("Year");
            columnNames.add("Grade");
            for (int i = 0; i < columnNames.size(); i++) {
                // We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(columnNames.get(i));
                col.setCellValueFactory(
                        new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                                return new SimpleStringProperty(param.getValue().get(j).toString());
                            }
                        });

                table.getColumns().addAll(col);
            }
            searchCourseIDButton.setOnAction(g -> {
                data = FXCollections.observableArrayList();
                Connection connection = null;
                try {
                    //connection = connectionFactory.connect();
                    String SELECT_COURSE_ENROLLMENT = "SELECT e.courseID,\r\n" + "c.courseName, \r\n"
                            + "e.studentID, \r\n" + "s.studentFirst, \r\n" + "e.semester, \r\n" + "e.year, \r\n"
                            + "e.grade\r\n" + "FROM finalproject.enrollmenttable e\r\n"
                            + "INNER JOIN finalproject.studenttable s ON s.studentID = e.studentID\r\n"
                            + "INNER JOIN finalproject.coursetable c ON c.courseID = e.courseID\r\n"
                            + "WHERE c.courseID =" + courseIDTextField.getText();
                    ResultSet rs = connection.createStatement().executeQuery(SELECT_COURSE_ENROLLMENT);
                    while (rs.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row.add(rs.getString(i));
                        }
                        data.add(row);
                    }
                    if (data.isEmpty()) {
                        table.getItems().clear();
                        table.refresh();
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error:\n Course does not exist.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } else {
                        table.setItems(data);
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            addCourseGradeButton.setOnAction(c -> {
                String[] Grades = { "A", "B", "C", "D", "F" };
                List<String> grades = Arrays.asList(Grades);
                try {
                    //connection = connectionFactory.connect();
                    String UPDATE_GRADE_QUERY = "UPDATE finalproject.enrollmenttable SET grade = ? WHERE studentID ="
                            + studentIDTextField.getText() + " AND courseID =" + courseIDTextField.getText();
                    PreparedStatement pst = connection.prepareStatement(UPDATE_GRADE_QUERY);
                    for (int i = 0; i < studentGradeTextField.getLength(); i++) {
                        if (grades.contains(studentGradeTextField.getText())) {
                            pst.setString(1, studentGradeTextField.getText());
                            pst.execute();
                            connection.close();
                            Stage SuccessStage = new Stage();
                            Group SuccessGroup = new Group();
                            Scene SuccessScene = new Scene(SuccessGroup);
                            Label errorLabel = new Label("\n Information successfully updated");
                            Button okButton = new Button("Ok");
                            errorLabel.setLayoutX(10);
                            okButton.setLayoutX(70);
                            okButton.setLayoutY(60);
                            SuccessGroup.getChildren().addAll(okButton, errorLabel);
                            okButton.setOnAction(d -> {
                                try {
                                    table.getItems().clear();
                                    //connection = connectionFactory.connect();
                                    String SELECT_COURSE_ENROLLMENT = "SELECT e.courseID,\r\n" + "c.courseName, \r\n"
                                            + "e.studentID, \r\n" + "s.studentFirst, \r\n" + "e.semester, \r\n"
                                            + "e.year, \r\n" + "e.grade\r\n" + "FROM finalproject.enrollmenttable e\r\n"
                                            + "INNER JOIN finalproject.studenttable s ON s.studentID = e.studentID\r\n"
                                            + "INNER JOIN finalproject.coursetable c ON c.courseID = e.courseID\r\n"
                                            + "WHERE c.courseID =" + courseIDTextField.getText();
                                    ResultSet rs = connection.createStatement().executeQuery(SELECT_COURSE_ENROLLMENT);
                                    while (rs.next()) {
                                        ObservableList<String> row = FXCollections.observableArrayList();
                                        for (int j = 1; j <= rs.getMetaData().getColumnCount(); j++) {
                                            row.add(rs.getString(j));
                                        }
                                        data.add(row);

                                    }

                                    table.setItems(data);
                                    table.refresh();
                                    connection.close();
                                    SuccessStage.close();
                                    studentIDTextField.clear();
                                    studentGradeTextField.clear();
                                    courseIDTextField.clear();

                                } catch (Exception e) {
                                    System.out.println(e.toString() + "1");
                                }

                                table.refresh();

                            });
                            SuccessStage.setWidth(200);
                            SuccessStage.setHeight(150);
                            SuccessStage.setScene(SuccessScene);
                            SuccessStage.setResizable(false);
                            SuccessStage.show();
                        } else {
                            Stage ErrorStage = new Stage();
                            Group ErrorGroup = new Group();
                            Scene ErrorScene = new Scene(ErrorGroup);
                            Label errorLabel = new Label("Error:\n Please put a\n A-D & F Grade");
                            Button okButton = new Button("Ok");
                            errorLabel.setLayoutX(10);
                            okButton.setLayoutX(70);
                            okButton.setLayoutY(60);
                            ErrorGroup.getChildren().addAll(okButton, errorLabel);
                            okButton.setOnAction(h -> {
                                ErrorStage.close();
                            });
                            ErrorStage.setWidth(200);
                            ErrorStage.setHeight(150);
                            ErrorStage.setScene(ErrorScene);
                            ErrorStage.setResizable(false);
                            ErrorStage.show();
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            });
            addCourseGradesGroup.getChildren().addAll(menuBar, table, studentIDLabel, studentIDTextField, courseIDLabel,
                    courseIDTextField, studentGradeLabel, studentGradeTextField, searchCourseIDButton,
                    addCourseGradeButton);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(addCourseGradesScene);

        });

        showGradeButton.setOnAction(a -> {
            Group showReportGroup = new Group();
            Scene showReportScene = new Scene(showReportGroup);
            TextArea gradeDisplay = new TextArea();
            Label courseIDLabel = new Label("Course ID");
            TextField courseIDTextField = new TextField();
            ChoiceBox<String> semesterChoiceBox = new ChoiceBox<>();
            semesterChoiceBox.getItems().add("Spring");
            semesterChoiceBox.getItems().add("Summer");
            semesterChoiceBox.getItems().add("Fall");
            semesterChoiceBox.getItems().add("Winter");
            ChoiceBox<String> yearChoiceBox = new ChoiceBox<>();
            yearChoiceBox.getItems().add("2015");
            yearChoiceBox.getItems().add("2016");
            yearChoiceBox.getItems().add("2017");
            yearChoiceBox.getItems().add("2018");
            yearChoiceBox.getItems().add("2019");
            yearChoiceBox.getItems().add("2020");
            yearChoiceBox.getItems().add("2021");
            Button generateReportButton = new Button("Generate Report");
            semesterChoiceBox.setLayoutX(130);
            semesterChoiceBox.setLayoutY(35);
            yearChoiceBox.setLayoutX(220);
            yearChoiceBox.setLayoutY(35);
            courseIDLabel.setLayoutX(10);
            courseIDLabel.setLayoutY(40);
            courseIDTextField.setLayoutX(70);
            courseIDTextField.setLayoutY(35);
            courseIDTextField.setPrefWidth(40);
            gradeDisplay.setPrefHeight(280);
            gradeDisplay.setPrefWidth(570);
            gradeDisplay.setLayoutX(10);
            gradeDisplay.setLayoutY(80);
            generateReportButton.setLayoutX(300);
            generateReportButton.setLayoutY(35);
            generateReportButton.setOnAction(b -> {
                Connection connection = null;
                try {
                    if (semesterChoiceBox.getValue() == null || yearChoiceBox.getValue() == null) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label(
                                "Error: \n You cannot leave \ncourse ID or semester or year \nblank.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    } else {
                        //connection = connectionFactory.connect();
                        String SHOW_REPORT_BY_COURSE = "SELECT c.courseName,\r\n" + "s.studentFirst,\r\n"
                                + "e.semester,\r\n" + "e.year,\r\n" + "e.grade\r\n"
                                + "FROM finalproject.enrollmenttable e\r\n"
                                + "INNER JOIN finalproject.coursetable c ON c.courseID = e.courseID\r\n"
                                + "INNER JOIN finalproject.studenttable s ON s.studentID = e.studentID\r\n"
                                + "WHERE e.courseID =" + courseIDTextField.getText() + " AND e.semester ='"
                                + semesterChoiceBox.getValue() + "' AND e.year =" + yearChoiceBox.getValue();
                        PreparedStatement ps = connection.prepareStatement(SHOW_REPORT_BY_COURSE);
                        ResultSet rs = ps.executeQuery();
                        gradeDisplay.clear();
                        StringBuilder sb = new StringBuilder();
                        while (rs.next()) {
                            //if (sb.is()) {
                                sb.insert(0, rs.getString("courseName"));
                                sb.append("\n--------------------------------");
                            //}
                            sb.append("\n" + rs.getString("studentFirst") + " " + rs.getString("grade"));
                        }
                        gradeDisplay.insertText(0, sb.toString());
                    }
                } catch (SQLSyntaxErrorException e) {
                    if (courseIDTextField.getText().isEmpty() || courseIDTextField.getText().isEmpty()) {
                        Stage ErrorStage = new Stage();
                        Group ErrorGroup = new Group();
                        Scene ErrorScene = new Scene(ErrorGroup);
                        Label errorLabel = new Label("Error: \n You cannot leave \ncourse ID blank.");
                        Button okButton = new Button("Ok");
                        errorLabel.setLayoutX(10);
                        okButton.setLayoutX(70);
                        okButton.setLayoutY(60);
                        ErrorGroup.getChildren().addAll(okButton, errorLabel);
                        okButton.setOnAction(c -> {
                            ErrorStage.close();
                        });
                        ErrorStage.setWidth(200);
                        ErrorStage.setHeight(150);
                        ErrorStage.setScene(ErrorScene);
                        ErrorStage.setResizable(false);
                        ErrorStage.show();
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            showReportGroup.getChildren().addAll(menuBar, courseIDLabel, courseIDTextField, semesterChoiceBox,
                    yearChoiceBox, generateReportButton, gradeDisplay);
            mainStage.setWidth(600);
            mainStage.setHeight(400);
            mainStage.setResizable(false);
            mainStage.setScene(showReportScene);
        });

    }
}
