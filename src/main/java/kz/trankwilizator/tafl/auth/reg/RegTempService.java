package kz.trankwilizator.tafl.auth.reg;

import kz.trankwilizator.tafl.dao.user.TempUserRepository;
import kz.trankwilizator.tafl.dto.TemporaryUserDto;
import kz.trankwilizator.tafl.entity.user.TemporaryUser;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class RegTempService implements RegistrationTemporaryUserService{

    private final TempUserRepository tempUserRepository;

    public RegTempService(TempUserRepository tempUserRepository) {
        this.tempUserRepository = tempUserRepository;
    }

    @Override
    public TemporaryUserDto create() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY,24);

        TemporaryUser temporaryUser = new TemporaryUser();
        temporaryUser.setBalance(0.0);
        temporaryUser.setEnabled(true);
        temporaryUser.setUsername(String.valueOf(new Date().getTime()));
        temporaryUser = tempUserRepository.save(temporaryUser);

        TemporaryUserDto temporaryUserDto = new TemporaryUserDto();
        temporaryUserDto.setBalance(temporaryUser.getBalance());
        temporaryUserDto.setId(temporaryUser.getId());
        temporaryUserDto.setUsername(temporaryUser.getUsername());
        temporaryUserDto.setExpireDate(calendar.getTime());
        temporaryUserDto.setCreatedAt(temporaryUser.getCreatedAt());

        return temporaryUserDto;
    }
}
