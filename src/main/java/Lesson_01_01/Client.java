package Lesson_01_01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        Camera camera = context.getBean("camera", Camera.class);
        camera.doPhoto();

        Camera camera1 = context.getBean("cameraAnotherCameraRoll", Camera.class);
        camera1.doPhoto();
    }
}
