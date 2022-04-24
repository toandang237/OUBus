module com.dvt.oubus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires org.apache.commons.codec;

    opens com.dvt.oubus to javafx.fxml;
    opens com.dvt.pojo to javafx.base;
    exports com.dvt.oubus;
}
