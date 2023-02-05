package controller.thuPhi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;



public class ThemQuyController implements Initializable {
    private ObservableList<String> list;
    @FXML
    private ChoiceBox<String> quyList;
    @FXML
    private TextField quyTxtField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList();
        quyList.setItems(list);
        quyList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
            quyTxtField.setText(quyList.getItems().get((Integer) arg2));
        }
        });
    }

    public void setQuyList(ObservableList<String> inputlist) {
        for(int i = 1; i < inputlist.size(); i++){
            list.add(inputlist.get(i));
        }
        quyList.setItems(list);
    }

    public String getChosen(){
        return quyTxtField.getText();
    }
}
