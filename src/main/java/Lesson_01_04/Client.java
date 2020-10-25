package Lesson_01_04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        CameraNew camera = context.getBean("cameraScope", CameraImplComponent.class);
        camera.doPhoto();

        camera.breaking();
        camera.doPhoto();

        CameraNew cameraNew = context.getBean("cameraScope", CameraImplComponent.class);
        cameraNew.doPhoto();


    }
}
