module com.dvt.oubus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires org.apache.commons.codec;

    opens com.dvt.oubus to javafx.fxml;
    exports com.dvt.oubus;
    exports com.dvt.pojo;
    exports com.dvt.sevices;
    exports com.dvt.utils;
}
