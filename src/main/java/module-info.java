module com.example.swapper {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires postgresql;
    requires dotenv.java;
    requires org.apache.commons.codec;

    opens com.example.swapper to javafx.fxml;
    exports com.example.swapper;
}