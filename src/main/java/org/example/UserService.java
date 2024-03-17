package org.example;

public class UserService {
    UserRepository userRepository;

    public boolean isUserActive(String username) {
        User user = userRepository.findByUsername(username);
        return user != null && user.isActive();
    }

    public void deleteUser(int userId) throws Exception {
        User user = userRepository.findUserId(userId);
        if (user == null) {
            throw new Exception();
        }
        userRepository.deleteUser(user);
    }

    public User getUser(int userId) throws Exception {
        User user = userRepository.findUserId(userId);
        if (user == null) {
            throw new Exception();
        }
        return user;
    }

}
