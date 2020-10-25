package Lesson_01_03;

import Lesson_01_01.Camera;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigApp.class);
        Camera camera = context.getBean("cameraJava", Camera.class);
        camera.doPhoto();
    }
}
