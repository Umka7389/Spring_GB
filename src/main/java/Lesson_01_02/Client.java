package Lesson_01_02;

import Lesson_01_01.Camera;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        Camera camera = context.getBean("cameraImplComponent", Camera.class);
        camera.doPhoto();
    }
}
