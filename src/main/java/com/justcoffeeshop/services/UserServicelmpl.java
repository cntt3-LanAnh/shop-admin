package com.justcoffeeshop.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.justcoffeeshop.models.RoleModel;
import com.justcoffeeshop.models.UserModel;
import com.justcoffeeshop.repositories.RoleRepository;
import com.justcoffeeshop.repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServicelmpl extends BaseService<UserModel> implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public UserServicelmpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, MailService mailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Override
    public List<UserModel> search(String term) {
        return baseRepository.findByAttributeContainsText("username", term);
    }

    @Override
    @Transactional
    public void register(UserModel userModel) throws Exception {
        if (userRepository.findByUsername(userModel.getUsername()).isPresent()) {
            throw new Exception("user_exist");
        }
//        if (userRepository.findByEmail(userModel.getEmail()).isPresent()) {
//            throw new Exception("user_exist");
//        }
        RoleModel roleModel = roleRepository.findByName("ROLE_USER");
        Set<RoleModel> roleModels = new HashSet<>();
        roleModels.add(roleModel);
        userModel.setRoleModels(roleModels);
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        baseRepository.save(userModel);
    }
}
