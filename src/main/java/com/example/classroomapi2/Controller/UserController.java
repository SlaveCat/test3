package com.example.classroomapi2.Controller;

import com.example.classroomapi2.Entity.UserEntity;
import com.example.classroomapi2.Exeptions.UserAlreadyExistException;
import com.example.classroomapi2.Exeptions.UserNotFoundExeption;
import com.example.classroomapi2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity userAdd(@RequestBody UserEntity user)
    {
        try{
            userService.userAdd(user);
            return ResponseEntity.ok("Saved");
        }
        catch (UserAlreadyExistException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity GetUsers(@RequestBody UserEntity user)
    {
        try{
            return ResponseEntity.ok(userService.userGetByUsername(user));
        }
        catch (UserNotFoundExeption e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
    @DeleteMapping
    public ResponseEntity DeleteUsers(@RequestBody UserEntity user)
    {
        try{
            return ResponseEntity.ok(userService.userDeleteById(user));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
