module com.arthsetu {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires de.jensd.fx.glyphs.fontawesome;
    requires org.xerial.sqlitejdbc;
    requires spring.boot.autoconfigure;

    opens com.arthsetu to javafx.fxml;
    opens com.arthsetu.Controllers to javafx.fxml;
    opens com.arthsetu.Controllers.Admin to javafx.fxml;
    opens com.arthsetu.Controllers.Client to javafx.fxml;
    exports com.arthsetu;
    exports com.arthsetu.Controllers;
    exports com.arthsetu.Controllers.Admin;
    exports com.arthsetu.Controllers.Client;
    exports com.arthsetu.Models;
    exports com.arthsetu.Viwes;
}