package kz.trankwilizator.tafl.service.auth.reg.temp;

import kz.trankwilizator.tafl.dao.user.UserRepository;
import kz.trankwilizator.tafl.dto.TemporaryUserDto;
import kz.trankwilizator.tafl.entity.user.TemporaryUser;
import kz.trankwilizator.tafl.mapper.TemporaryUserMapper;
import kz.trankwilizator.tafl.service.util.UniqueStringGenerator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TempUserRegistrationService implements TemporaryUserRegistrationService {

    private final UserRepository<TemporaryUser> tempUserRepository;
    private final TemporaryUserMapper temporaryUserMapper;
    public TempUserRegistrationService(UserRepository<TemporaryUser> tempUserRepository, TemporaryUserMapper temporaryUserMapper) {
        this.tempUserRepository = tempUserRepository;
        this.temporaryUserMapper = temporaryUserMapper;
    }

    @Override
    public TemporaryUserDto create() {
        TemporaryUser temporaryUser = new TemporaryUser();
        temporaryUser.setBalance(BigDecimal.valueOf(0.0));
        temporaryUser.setEnabled(true);
        temporaryUser.setUsername(UniqueStringGenerator.generateUniqueString());
        temporaryUser = tempUserRepository.save(temporaryUser);
        return temporaryUserMapper.toDto(temporaryUser);
    }
}
