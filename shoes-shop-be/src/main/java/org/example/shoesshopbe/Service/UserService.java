package org.example.shoesshopbe.Service;

import org.example.shoesshopbe.Exception.AuthenticationException;
import org.example.shoesshopbe.Exception.MessageError;
import org.example.shoesshopbe.Model.Users;
import org.example.shoesshopbe.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

  public Users login(String username,String password){
      Optional<Users> usersOptional = userRepo.findByUsername(username);

      if(usersOptional.isPresent()){
          Users user = usersOptional.get();
          if (passwordEncoder.matches(password,user.getPassword())){
              return user;
          }
      }
      return null;
  }

  public Users register(Users user){
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setCreatedAt(LocalDateTime.now());
      return userRepo.save(user);
  }
}
