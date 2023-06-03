package kz.trankwilizator.tafl.service.crud.launch;

import kz.trankwilizator.tafl.dao.launchable.LaunchRepository;
import kz.trankwilizator.tafl.entity.launchable.Launch;
import kz.trankwilizator.tafl.entity.launchable.runnable.RunnableEntity;
import kz.trankwilizator.tafl.entity.launchable.runnable.RunnableEntityDetails;
import kz.trankwilizator.tafl.service.crud.CrudService;
import org.springframework.stereotype.Service;

@Service
public class LaunchCrudService extends CrudService<Launch> {
    private final LaunchRepository launchRepository;
    protected LaunchCrudService(LaunchRepository repository) {
        super(repository);
        launchRepository = repository;
    }
    public Launch getByRunnableEntity(RunnableEntity runnableEntity){
        return  getFromOptional(
          launchRepository.findByRunnableEntityDetails(new RunnableEntityDetails(runnableEntity.getId(), runnableEntity.getType().getName()))
        );
    }
}
