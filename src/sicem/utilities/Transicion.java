/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicem.utilities;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 *
 * @author hespinoza
 */
public final class Transicion {
    private Transicion(){}
    
    
    public static void width(Button node, int initial, int last, int duration){
        Timeline task = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(node.prefWidthProperty(), initial)),
            new KeyFrame(Duration.millis(duration), new KeyValue(node.prefWidthProperty(), last))
        );

        task.setAutoReverse(true);
        task.setCycleCount(Timeline.INDEFINITE);
        task.playFromStart();
    }
}
