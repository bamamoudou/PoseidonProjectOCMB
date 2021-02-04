package com.nnk.springboot.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.servicesImpl.MyUserDetailsService;
import com.nnk.springboot.servicesImpl.UserServiceImpl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(MockitoJUnitRunner.class)
public class MyUserDetailsServiceTest {
	@Mock
   private UserServiceImpl userServiceMock;

   @InjectMocks
   private MyUserDetailsService myUserDetailsService;

   @Test
   public void loadUserByUsername_userExists_myUserDetailsReturned() {
       // arrange
       User testUser = new User();
       testUser.setId(100);
       testUser.setUsername("testUserName");
       testUser.setPassword("Test123@password");
       testUser.setFullname("TestFullName");
       testUser.setRole("USER");

       when(userServiceMock.findUserByUserName("testUserName")).thenReturn(testUser);

       // act
       UserDetails result = myUserDetailsService.loadUserByUsername("testUserName");

       // assert
       assertEquals(testUser.getUsername(), result.getUsername());
   }

   @Test(expected = UsernameNotFoundException.class)
   public void loadUserByUsername_userDoesNotExist_throwsUsernameNotFoundException() {
       // arrange
       String username = "test@test.com";

       when(userServiceMock.findUserByUserName(username)).thenReturn(null);

       // act
       UserDetails result = myUserDetailsService.loadUserByUsername(username);

       // assert
       assertNull(result);
   }
}