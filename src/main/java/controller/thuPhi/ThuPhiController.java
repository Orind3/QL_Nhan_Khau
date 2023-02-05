package controller.thuPhi;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ThuPhiController {

    @FXML
    private Pane phanThuongPane;

    public void phiVeSinhClicked(MouseEvent mouseEvent) throws IOException {
        Pane phiVeSinhPane = FXMLLoader.load(getClass().getResource("/view/thuPhi/thongtinphivesinhui.fxml"));
        phanThuongPane.getChildren().clear();
        phanThuongPane.getChildren().add(phiVeSinhPane);
    }

    public void phiUngHoClicked(MouseEvent mouseEvent) throws IOException {
        try {
            Pane phiUngHoPane = FXMLLoader.load(getClass().getResource("/view/thuPhi/thongtinunghoui.fxml"));
            phanThuongPane.getChildren().clear();
            phanThuongPane.getChildren().add(phiUngHoPane);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
