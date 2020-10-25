package Lesson_01_01;

public class CameraImpl implements Camera {



    private CameraRoll cameraRoll;

    public CameraRoll getCameraRoll() {
        return cameraRoll;
    }

    public void setCameraRoll(CameraRoll cameraRoll) {
        this.cameraRoll = cameraRoll;
    }

    public void doPhoto (){
        System.out.println("Make Photo");
        cameraRoll.processing();
    }

}
