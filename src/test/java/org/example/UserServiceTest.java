package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testIsUserActive_UserExistsAndIsActive() {
        User user = new User();
        user.setActive(true);
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);

        boolean isActive = userService.isUserActive("anyString");

        assertTrue(isActive);
    }

    @Test
    public void testIsUserActive_UserDoesNotExist() {
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(null);

        boolean isActive = userService.isUserActive("anyString");

        assertFalse(isActive);
    }

    @Test
    public void testIsUserActive_UserExistsButIsNotActive() {
        User user = new User();
        user.setActive(false);
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);

        boolean isActive = userService.isUserActive("anyString");

        assertFalse(isActive);
    }

    @Test
    public void testDeleteUser_UserExists() throws Exception {
        User user = new User();
        Mockito.when(userRepository.findUserId(Mockito.anyInt())).thenReturn(user);

        userService.deleteUser(1);

        Mockito.verify(userRepository).deleteUser(user);
    }

    @Test
    public void testDeleteUser_UserDoesNotExist() {
        Mockito.when(userRepository.findUserId(Mockito.anyInt())).thenReturn(null);

        assertThrows(Exception.class, () -> {
            userService.deleteUser(1);
        });
    }

    @Test
    public void testGetUser_UserExists() throws Exception {
        User user = new User();
        Mockito.when(userRepository.findUserId(Mockito.anyInt())).thenReturn(user);

        User actualUser = userService.getUser(1);

        assertEquals(user, actualUser);
    }

    @Test
    public void testGetUser_UserDoesNotExist() {
        Mockito.when(userRepository.findUserId(Mockito.anyInt())).thenReturn(null);

        assertThrows(Exception.class, () -> {
            userService.getUser(1);
        });
    }
}
