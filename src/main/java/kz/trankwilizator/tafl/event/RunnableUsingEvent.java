package kz.trankwilizator.tafl.event;

import kz.trankwilizator.tafl.entity.launchable.runnable.RunnableEntity;
import kz.trankwilizator.tafl.entity.launchable.runnable.RunnableEntityType;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RunnableUsingEvent extends ApplicationEvent {
    private final RunnableEntity runnableEntity;
    private final RunnableEntityType runnableEntityType;
    public RunnableUsingEvent(RunnableEntity runnableEntity, RunnableEntityType runnableEntityType) {
        super(runnableEntity);
        this.runnableEntity = runnableEntity;
        this.runnableEntityType = runnableEntityType;
    }
}
