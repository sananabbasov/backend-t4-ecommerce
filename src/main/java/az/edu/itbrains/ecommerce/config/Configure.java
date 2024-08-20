package az.edu.itbrains.ecommerce.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Configure {


    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }


}
