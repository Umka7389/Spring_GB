package Lesson_01_02;

import Lesson_01_01.Camera;
import Lesson_01_01.CameraRoll;


public class CameraImplComponent implements Camera {
    @Override
    public void doPhoto() {
        System.out.println("Make photo component");
        cameraRoll.processing();
    }

    private CameraRoll cameraRoll;

    public CameraRoll getCameraRoll() {
        return cameraRoll;
    }

    public void setCameraRoll(CameraRoll cameraRoll) {
        this.cameraRoll = cameraRoll;
    }

}
