package com.example.classroomapi2.Service;

import com.example.classroomapi2.Entity.UserEntity;
import com.example.classroomapi2.Exeptions.UserAlreadyExistException;
import com.example.classroomapi2.Exeptions.UserNotFoundExeption;
import com.example.classroomapi2.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserEntity userAdd(UserEntity user) throws UserAlreadyExistException {
        if(userRepo.findByUsername(user.getUsername()) != null)
        {
            throw new UserAlreadyExistException("Пользователь уже существует");
        }
        return userRepo.save(user);
    }
    public UserEntity userGetByUsername(UserEntity user) throws UserNotFoundExeption {
        if (userRepo.findByUsername(user.getUsername()) == null)
        {
            throw new UserNotFoundExeption("Пользователь не найден");
        }
        return userRepo.findByUsername(user.getUsername());
    }
    public UserEntity userDeleteById(UserEntity user)
    {
        userRepo.deleteById(user.getId());
        return user;
    }
}
