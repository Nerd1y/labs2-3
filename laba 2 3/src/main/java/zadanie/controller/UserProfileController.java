package zadanie.controller;

import zadanie.entity.UserProfile;
import zadanie.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userProfiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public List<UserProfile> getUserProfiles() {
        return userProfileService.findAll();
    }

    @PostMapping
    public UserProfile createUserProfile(@RequestBody UserProfile newUserProfile) {
        return userProfileService.save(newUserProfile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserProfileById(@PathVariable Long id) {
        UserProfile userProfile = userProfileService.findById(id)
                .orElseThrow(() -> new RuntimeException("User Profile not found"));
        return ResponseEntity.ok().body(userProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> updateUserProfile(@PathVariable Long id, @RequestBody UserProfile newUserProfile) {
        UserProfile updatedUserProfile = userProfileService.update(newUserProfile, id);
        return ResponseEntity.ok().body(updatedUserProfile);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}