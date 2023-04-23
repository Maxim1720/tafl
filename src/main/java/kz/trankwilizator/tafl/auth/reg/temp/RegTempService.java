package kz.trankwilizator.tafl.auth.reg.temp;

import kz.trankwilizator.tafl.dao.user.temp.TempUserRepository;
import kz.trankwilizator.tafl.dto.TemporaryUserDto;
import kz.trankwilizator.tafl.entity.user.temp.TemporaryUser;
import kz.trankwilizator.tafl.mapper.TemporaryUserMapper;
import org.springframework.stereotype.Service;

@Service
public class RegTempService implements RegistrationTemporaryUserService{

    private final TempUserRepository tempUserRepository;
    private final TemporaryUserMapper temporaryUserMapper;
    public RegTempService(TempUserRepository tempUserRepository, TemporaryUserMapper temporaryUserMapper) {
        this.tempUserRepository = tempUserRepository;
        this.temporaryUserMapper = temporaryUserMapper;
    }

    @Override
    public TemporaryUserDto create() {
        TemporaryUser temporaryUser = new TemporaryUser();
        temporaryUser.setBalance(0.0);
        temporaryUser.setEnabled(true);
        temporaryUser.setUsername(UniqueStringGenerator.generateUniqueString());
        temporaryUser = tempUserRepository.save(temporaryUser);
        return temporaryUserMapper.toDto(temporaryUser);
    }
}
