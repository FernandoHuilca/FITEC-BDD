module ModuloFITEC {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;
    requires javafx.base;


    // Exporta el paquete de controladores para que FXML pueda acceder a él
    opens ModuloFITEC.Controllers to javafx.fxml;
    opens ModuloFITEC.logic.Models to javafx.base;

    exports ModuloFITEC.application;
    exports ModuloFITEC.logic;
    exports ModuloFITEC.Controllers;

} 