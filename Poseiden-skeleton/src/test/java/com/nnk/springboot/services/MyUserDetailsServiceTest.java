package com.nnk.springboot.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nnk.springboot.datatest.DataLoader;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.security.MyUserDetailsService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(MockitoJUnitRunner.class)
public class MyUserDetailsServiceTest {
	@Mock
   private UserRepository userRepository;

   @InjectMocks
   private MyUserDetailsService myUserDetailsService;
   
   private User user;
   
   @Before
  public void setUp() {
      DataLoader dataLoader = new DataLoader();
      user = dataLoader.setUser();
  }

   @Test
   public void loadUserByUsername_userExists_myUserDetailsReturned() {
       // arrange
//       User testUser = new User();
//       testUser.setId(100);
//       testUser.setUsername("testUserName");
//       testUser.setPassword("Test123@password");
//       testUser.setFullname("TestFullName");
//       testUser.setRole("USER");
       
       when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));



       // act
       UserDetails result = myUserDetailsService.loadUserByUsername("testUserName");

       // assert
       assertNotNull(result);
       assertEquals(user.getUsername(), result.getUsername());
       assertEquals(user.getPassword(), result.getPassword());
       assertEquals(Arrays.stream(user.getRole().split(","))
               .map(SimpleGrantedAuthority::new)
               .collect(Collectors.toList()), result.getAuthorities());
       assertTrue(result.isAccountNonExpired());
       assertTrue(result.isAccountNonLocked());
       assertTrue(result.isCredentialsNonExpired());
       assertTrue(result.isEnabled());
   }

   @Test(expected = UsernameNotFoundException.class)
   public void loadUserByUsername_userDoesNotExist_throwsUsernameNotFoundException() {
       // arrange
      // String username = "test@test.com";

       when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

       // act
       UserDetails result = myUserDetailsService.loadUserByUsername("wrong");

       // assert
       assertNull(result);
   }
}