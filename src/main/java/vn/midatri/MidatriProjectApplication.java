package vn.midatri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class MidatriProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MidatriProjectApplication.class, args);
    }

//    @Bean
//    public ResourceBundleMessageSource messageSource() {
//        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
//        source.setBasenames(
//                "static/text/message/messages_vi");
////                "static/texts/views/view_vi");
//        source.setDefaultEncoding("UTF-8");
//        source.setUseCodeAsDefaultMessage(true);
//        return source;
//    }
}
