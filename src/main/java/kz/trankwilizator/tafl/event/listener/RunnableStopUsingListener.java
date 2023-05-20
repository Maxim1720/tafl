package kz.trankwilizator.tafl.event.listener;

import kz.trankwilizator.tafl.entity.launchable.Launch;
import kz.trankwilizator.tafl.event.RunnableStopUsingEvent;
import kz.trankwilizator.tafl.service.crud.launch.LaunchCrudService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RunnableStopUsingListener implements ApplicationListener<RunnableStopUsingEvent> {

    private final LaunchCrudService launchCrudService;

    public RunnableStopUsingListener(LaunchCrudService launchCrudService) {
        this.launchCrudService = launchCrudService;
    }

    @Override
    public void onApplicationEvent(RunnableStopUsingEvent event) {
        Launch launch = launchCrudService.getByRunnableEntity(event.getRunnableEntity());
        launch.setEndTime(new Date());
        launchCrudService.save(launch);
    }
}
