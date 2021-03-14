package Adidas.DTO;

import Adidas.Entities.Role;
import Adidas.Entities.User;
import Adidas.Repositories.RoleRepository;
import Adidas.Utilities.SignUpRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public abstract class SignUpRequestMapper {

    @Autowired
    public RoleRepository roleRepository;

    @Mapping(target = "password", ignore = true)
    abstract SignUpRequest toDTO(User user);

    @Mapping(target = "roles", expression = "java(getRoles())")
    @Mapping(target = "password", expression = "java(encode().encode(signUpRequest.getPassword()))")
    public abstract User toModel(SignUpRequest signUpRequest);

    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    public List<Role> getRoles() {
        return List.of(roleRepository.findByRole("USER"));
    }
}
