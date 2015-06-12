package hello;


import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WebApplicationConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor());
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        HttpMessageConverter<Map<String, Greeting>> other =new HttpMessageConverter<Map<String, Greeting>>() {
            @Override
            public boolean canRead(Class<?> clazz, MediaType mediaType) {
                return true;
            }

            @Override
            public boolean canWrite(Class<?> clazz, MediaType mediaType) {
                return true;
            }

            @Override
            public List<MediaType> getSupportedMediaTypes() {
                List<MediaType> list = new ArrayList<>();
                list.add(MediaType.ALL);
                return list;
            }

            @Override
            public Map<String, Greeting> read(Class<? extends Map<String, Greeting>> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
                return null;
            }

            @Override
            public void write(Map<String, Greeting> stringGreetingMap, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
                outputMessage.getBody().write("Hello World".getBytes());

            }
        };
        converters.add(other);
        super.configureMessageConverters(converters);
    }
}
