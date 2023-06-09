package com.example.proyectofinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación.
 */
public class App extends Application {
    private static Scene scene;

    /**
     * Método de inicio de la aplicación.
     * @param stage El objeto Stage (ventana principal) de JavaFX.
     * @throws IOException Si ocurre un error durante la carga del archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Crea una nueva escena con el contenido cargado desde el archivo FXML "mercadoLibre.fxml"
        scene = new Scene(loadFXML("mercadoLibre"), 1440, 900);
        stage.setScene(scene); // Establece la escena en el Stage (ventana principal)
        stage.show(); // Muestra la ventana
        stage.setResizable(false);
    }

    /**
     * Cambia la raíz de la escena actual por el contenido cargado desde el nuevo archivo FXML.
     * @param fxml El nombre del archivo FXML que se cargará.
     * @throws IOException Si ocurre un error durante la carga del archivo FXML.
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Carga un archivo FXML y devuelve su contenido como un objeto Parent.
     * @param fxml El nombre del archivo FXML que se cargará.
     * @return El contenido del archivo FXML como un objeto Parent.
     * @throws IOException Si ocurre un error durante la carga del archivo FXML.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Método principal de la aplicación.
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}
