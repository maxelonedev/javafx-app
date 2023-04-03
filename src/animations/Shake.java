package animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/* Неправильно ввел = будет трясти. */
public class Shake {
     TranslateTransition tt;
    /* Конструктор Shake, принимающий параметр Node. */
    public Shake(Node node){
        /* выделяем память */
        tt = new TranslateTransition(Duration.millis(70), node);
        /* отступ от икса */
        tt.setFromX(0f);
        /* насколько передвинется от начальной позиции */
        tt.setByX(15f);
        /* сколько раз проиграет анимацию */
        tt.setCycleCount(3);
        /* вернуть в начальную позицию. */
        tt.setAutoReverse(true);
    }
    /* ничего не возвращает-return, поэтому void*/
    public void playAnim(){
        tt.playFromStart();
    }
}
