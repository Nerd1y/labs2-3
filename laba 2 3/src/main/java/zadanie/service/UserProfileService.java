package zadanie.service;

import zadanie.entity.UserProfile;
import zadanie.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;


    public List<UserProfile> findAll() {
        return userProfileRepository.findAll();
    }

    public UserProfile save(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public void deleteById(Long id) {
        userProfileRepository.deleteById(id);
    }

    public Optional<UserProfile> findById(Long id) {
        return userProfileRepository.findById(id);
    }

    public UserProfile update(UserProfile newUserProfile, Long id) {
        return userProfileRepository.findById(id)
                .map(userProfile -> {
                    userProfile.setAddress(newUserProfile.getAddress());
                    userProfile.setPhoneNumber(newUserProfile.getPhoneNumber());
                    return userProfileRepository.save(userProfile);
                })
                .orElseThrow(() -> new EntityNotFoundException("UserProfile not found with id " + id));
    }
}