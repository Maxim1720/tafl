package kz.trankwilizator.tafl.event;

import kz.trankwilizator.tafl.entity.launchable.runnable.RunnableEntity;
import kz.trankwilizator.tafl.entity.launchable.runnable.RunnableEntityType;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RunnableStopUsingEvent extends ApplicationEvent {

    private final RunnableEntity runnableEntity;
    private final RunnableEntityType runnableEntityType;

    public RunnableStopUsingEvent(RunnableEntityType runnableEntityType, RunnableEntity runnableEntity) {
        super(runnableEntity);
        this.runnableEntity = runnableEntity;
        this.runnableEntityType = runnableEntityType;
    }
}
