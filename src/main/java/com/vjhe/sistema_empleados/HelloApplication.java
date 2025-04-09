package com.vjhe.sistema_empleados;

import com.vjhe.sistema_empleados.controladores.MainController;
import com.vjhe.sistema_empleados.modelo.ModelView;
import com.vjhe.sistema_empleados.modelo.Seccion;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {
    private ModelView model;
    private Map<Seccion, Node> vistaSecciones = new HashMap<>();
    private Parent vistaMain;
    private ObservableList<Node> anclajeSeccion;

    @Override
    public void init() throws IOException {
        model = new  ModelView();
        //cargar vista principal
        FXMLLoader mainLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        vistaMain =  mainLoader.load();
        MainController mainController = mainLoader.getController();
        mainController.setModel(model);

        anclajeSeccion = ((Pane) vistaMain.lookup("#seccion")).getChildren();

        Platform.runLater(() -> {
            try {
                cargarVistaSeccion("bienvenida.fxml", Seccion.BIENVENIDA);
                cargarVistaSeccion("nuevoEmpleado.fxml", Seccion.NUEVO_EMPLEADO);
                cargarVistaSeccion("horarios.fxml", Seccion.ADMINISTRAR_HORARIOS);
                cargarVistaSeccion("remuneraciones.fxml", Seccion.CAMBIAR_REMUNRACIONES);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            configurarSeccionDinamica();
            model.setSeccionActivaProperty(Seccion.BIENVENIDA);
        });
    }


    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(vistaMain);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void configurarSeccionDinamica(){
        Label tituloSeccion = (Label) vistaMain.lookup("#titulo_seccion");
        tituloSeccion.textProperty().bind(model.tituloSeccionProperty());


        model.seccionActivaProperty().addListener((observable, oldValue, newValue) -> {
            Node vistaAnterior = vistaSecciones.get(oldValue);
            Node nuevaVista = vistaSecciones.get(newValue);

            anclajeSeccion.remove(vistaAnterior);
            anclajeSeccion.add(nuevaVista);
        });

    }


    private void cargarVistaSeccion(String fxmlFile, Seccion seccion) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        Node root = loader.load();
        vistaSecciones.put(seccion, root);
    }

    public static void main(String[] args) {
        launch();
    }
}