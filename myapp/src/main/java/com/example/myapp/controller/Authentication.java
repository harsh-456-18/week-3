package com.example.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.dto.SignupRequest;
import com.example.myapp.model.User;
import com.example.myapp.repo.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class Authentication {

    @Autowired
    UserRepository db;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest obj) {

        User u = new User();
        u.setName(obj.getName());
        u.setEmail(obj.getEmail());
        u.setPassword(obj.getPassword());

        db.save(u);
        return "signup success";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return db.findAll();
    }

    @PutMapping("/user/{id}")
    public Map<Object, Object> updateUser(
            @PathVariable Long id,
            @RequestBody SignupRequest obj) {

        Map<Object, Object> res = new HashMap<>();

        Optional<User> op = db.findById(id);
        if (op.isEmpty()) {
            res.put("success", false);
            res.put("msg", "User not found");
            return res;
        }

        User user = op.get();
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
        user.setPassword(obj.getPassword());

        db.save(user);

        res.put("success", true);
        res.put("msg", "User updated successfully");
        res.put("name", user.getName());
        res.put("email", user.getEmail());

        return res;
    }

    @DeleteMapping("/user/{id}")
    public Map<Object, Object> deleteUser(@PathVariable Long id) {

        Map<Object, Object> res = new HashMap<>();

        Optional<User> op = db.findById(id);
        if (op.isEmpty()) {
            res.put("success", false);
            res.put("msg", "User not found");
            return res;
        }

        db.deleteById(id);
        res.put("success", true);
        res.put("msg", "User deleted successfully");

        return res;
    }
}
