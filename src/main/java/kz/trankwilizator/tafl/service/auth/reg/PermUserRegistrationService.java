package kz.trankwilizator.tafl.service.auth.reg;

import jakarta.persistence.EntityExistsException;
import kz.trankwilizator.tafl.dao.role.RoleRepository;
import kz.trankwilizator.tafl.dao.user.UserRepository;
import kz.trankwilizator.tafl.dto.PermanentUserDto;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import kz.trankwilizator.tafl.exception.ExistsException;
import kz.trankwilizator.tafl.mapper.PermanentUserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PermUserRegistrationService implements PermanentUserRegistrationService{
    private final UserRepository<PermanentUser> userRepository;
    private final PermanentUserMapper userMapper;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    public PermUserRegistrationService(UserRepository<PermanentUser> userRepository,
                                       PermanentUserMapper userMapper, RoleRepository roleRepository,
                                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PermanentUserDto createUser(PermanentUserDto user) {
        checkExisting(user);
        user.setPassword(passwordEncoder.encode(String.valueOf(user.getPassword())).toCharArray());
        PermanentUser permanent = userMapper.toEntity(user);
        permanent.setBalance(0.0);
        permanent.setRole(roleRepository.findByNameIgnoreCase("user").orElseThrow(()->new EntityExistsException("Role doesn't exists")));
        permanent.setDiscount(0.0);
        permanent.setBalance(0.0);
        permanent.setEnabled(true);
        return userMapper.toDto(userRepository.save(permanent));
    }



    private void checkExisting(PermanentUserDto user){
        userRepository.findByUsernameIgnoreCase(user.getUsername()).ifPresent((u)-> {
            throw new ExistsException(String.format("%s already exists",u.getUsername()), user);
        });
    }
}
