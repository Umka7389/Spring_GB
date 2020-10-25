package Lesson_01_03;


import Lesson_01_01.Camera;
import Lesson_01_01.CameraImpl;
import Lesson_01_01.CameraRoll;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan ("Lesson_01_02")
public class ConfigApp {


    @Bean (name = "cameraJava")
    public Camera cameraJava (CameraRoll cameraRoll){
        CameraImpl cameraImpl = new CameraImpl();
        cameraImpl.setCameraRoll(cameraRoll);
        return cameraImpl;
    }

}
