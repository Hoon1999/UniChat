package webchat.knung.springConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String basePath = "/images/**";
    private String resourcePath = "file:/Users/kjhoon44/Desktop/knung_images/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // http://서버주소/images/(생략) 으로 요청이 들어오면,
        // desktop 의 knung_images 폴더에서 파일을 가져가도록 설정.
        registry.addResourceHandler(basePath)
                .addResourceLocations(resourcePath);
    }
}
