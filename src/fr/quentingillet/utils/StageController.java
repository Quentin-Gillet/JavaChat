package fr.quentingillet.utils;

import javafx.stage.Stage;

public class StageController {

    public static void changeStage(Stage stage, Stage actualStage){
        actualStage.close();
        stage.show();
    }

}
