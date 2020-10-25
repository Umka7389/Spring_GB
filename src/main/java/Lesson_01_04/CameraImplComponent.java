package Lesson_01_04;

import Lesson_01_01.CameraRoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope ("prototype")
@Component("cameraScope")
public class CameraImplComponent implements CameraNew {

    private boolean broken = false;

    @Override
    public void doPhoto() {
        if (!isBroken()) {
            System.out.println("Make photo component");
            cameraRoll.processing();
        } else {
            System.out.println("Camera is broken");
        }
    }

    @Override
    public void breaking() {
        broken = true;
    }

    @Override
    public boolean isBroken() {
        return broken;
    }

    private CameraRoll cameraRoll;

    public CameraRoll getCameraRoll() {
        return cameraRoll;
    }

    @Autowired
    @Qualifier("ColorCameraRollComponentNew")
    public void setCameraRoll(CameraRoll cameraRoll) {
        this.cameraRoll = cameraRoll;
    }

}
