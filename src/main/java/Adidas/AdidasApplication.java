package Adidas;

import Adidas.Entities.Category;
import Adidas.Entities.Role;
import Adidas.Entities.Size;
import Adidas.Enums.Gender;
import Adidas.Repositories.CategoryRepository;
import Adidas.Repositories.RoleRepository;
import Adidas.Repositories.SizeRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Date;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class AdidasApplication {

    String[] sizes = {"XS", "S", "M", "L", "XL", "2XL"};
    String[] WMSizes = {"M 5 / W 6", "M 6 / W 7", "M 6.5 / W 7.5", "M 7 / W 8", "M 7.5 / W 8.5", "M 8 / W 9",
        "M 8.5 / W 9.5",
        "M 9 / W 10",
        "M 9.5 / W 10.5",
        "M 10 / W 11",
        "M 10.5 / W 11.5",
        "M 11 / W 12",
        "M 11.5 / W 12.5",
        "M 12 / W 13",
        "M 12.5 / W 13.5",
        "M 13 / W 14",
        "M 14 / W 15"};

    String[] categories = {
        "Bags",
        "Hoodies & Sweatshirts",
        "Jackets",
        "Jerseys",
        "Long Sleeve Shirts",
        "Pants",
        "Short Sleeve Shirts",
        "Shorts",
        "Sports Bras",
        "Swimwear",
        "T Shirts",
        "Tank Tops",
        "Tights",
        "Track Suits",
        "Underwear"};

    @Bean
    CommandLineRunner commandLineRunner(
            SizeRepository sizeRepository,
            CategoryRepository categoryRepository,
            RoleRepository roleRepository
    ) {
        return args -> {
            if (sizeRepository.count() <= 0) {
                Arrays.asList(sizes).stream().forEach(s -> {
                    sizeRepository.save(new Size(null, s, Gender.ALL, new Date(), new Date()));
                });
                Arrays.asList(WMSizes).stream().forEach(s -> {
                    sizeRepository.save(new Size(null, s, Gender.ALL, new Date(), new Date()));
                });
            }

            if (categoryRepository.count() <= 0) {
                Arrays.asList(categories).stream().forEach(s -> {
                    categoryRepository.save(new Category(null, s, new Date(), new Date()));
                });

            }
            if (roleRepository.count() <= 0) {
                Arrays.asList(new String[]{"ADMIN", "USER"}).stream().forEach(s -> {
                    roleRepository.save(new Role(null, s, new Date(), new Date()));
                });

            }

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(AdidasApplication.class, args);
    }

    @Bean
    public ObjectMapper getJacksonObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(
                com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}
