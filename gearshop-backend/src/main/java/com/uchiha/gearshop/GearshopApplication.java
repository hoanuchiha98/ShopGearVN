package com.uchiha.gearshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GearshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(GearshopApplication.class, args);
    }
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Khi chương trình chạy
//        // Insert vào csdl một user.
//        UserEntity user = new UserEntity();
//        user.setUsername("hoanuchiha");
//        user.setPassword(passwordEncoder.encode("123"));
//        userRepository.save(user);
//        System.out.println(user);
//    }

}
