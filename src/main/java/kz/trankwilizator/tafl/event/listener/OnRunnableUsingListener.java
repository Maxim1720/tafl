package kz.trankwilizator.tafl.event.listener;

import kz.trankwilizator.tafl.entity.launchable.Launch;
import kz.trankwilizator.tafl.event.RunnableUsingEvent;
import kz.trankwilizator.tafl.service.crud.launch.LaunchCrudService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OnRunnableUsingListener implements ApplicationListener<RunnableUsingEvent> {

    private final LaunchCrudService launchCrudService;

    public OnRunnableUsingListener(LaunchCrudService launchCrudService) {
        this.launchCrudService = launchCrudService;
    }

    @Override
    public void onApplicationEvent(RunnableUsingEvent event) {
        Launch launch = new Launch();
        launch.setRunnableEntity(event.getRunnableEntity());
//        launch.setRunnableEntityType(event.getRunnableEntityType());
        launchCrudService.save(launch);
    }
}
