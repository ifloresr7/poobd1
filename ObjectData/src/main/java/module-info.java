module ObjectData_app {
    requires javafx.controls;
    requires javafx.fxml;

    opens ObjectData_app.ObjectData_view to javafx.fxml;
    exports ObjectData_app.ObjectData_view;
}
