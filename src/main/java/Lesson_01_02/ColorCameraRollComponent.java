package Lesson_01_02;

import Lesson_01_01.CameraRoll;
import org.springframework.stereotype.Component;

@Component ("ColorCameraRollComponent")
public class ColorCameraRollComponent implements CameraRoll {
    @Override
    public void processing() {
        System.out.println("1 color photo component");
    }
}
