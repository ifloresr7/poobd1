module ObjectData_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    exports ObjectData_app.ObjectData_controller;
    requires javafx.graphics;
    opens ObjectData_app.ObjectData_model.ObjectData_Hibernate to jakarta.persistence;
    exports ObjectData_app.ObjectData_model.ObjectData_Hibernate;
    opens ObjectData_app.ObjectData_model to org.hibernate.orm.core;
    exports ObjectData_app.ObjectData_model;
    opens ObjectData_app.ObjectData_view to javafx.fxml;
    exports ObjectData_app.ObjectData_view;
}