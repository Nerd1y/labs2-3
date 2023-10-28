package zadanie.loader;

import zadanie.entity.User;
import zadanie.entity.UserProfile;
import zadanie.service.UserProfileService;
import zadanie.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final UserProfileService userProfileService;

    public DataLoader(UserService userService, UserProfileService userProfileService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Fetch all users
        List<User> users = userService.findAll();
        // Fetch all user profiles
        List<UserProfile> userProfiles = userProfileService.findAll();

        // Print user profiles
        userProfiles.forEach(System.out::println);

        // Print users
        users.forEach(user -> {
            System.out.println(user);
            userProfiles.stream()
                    .filter(profile -> profile.getId().equals(user.getProfile().getId()))
                    .findFirst().ifPresent(System.out::println);
        });
    }
}