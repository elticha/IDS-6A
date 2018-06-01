package interprete;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class FXMLEntradaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextArea txtEntrada;
    @FXML
    private TextArea txtSalida;
    @FXML
    private Button btnAnalizar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAnalizar.setDisable(true);
        txtSalida.setDisable(true);
        txtEntrada.textProperty().addListener((observable, oldvalue, newvalue) -> {
            if (txtEntrada.getText().length() > 0) {
                btnAnalizar.setDisable(false);
                txtSalida.setDisable(false);
            } else {
                btnAnalizar.setDisable(true);
                txtSalida.setDisable(true);
            }
        });

        btnAnalizar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                txtSalida.setEditable(false);
                txtSalida.setDisable(false);
                Analizador analizador = new Analizador(txtEntrada.getText(),txtSalida);
            }
        });
    }
}
