package net.playeranalytics.plugin.scheduling;

import net.minestom.server.MinecraftServer;

import java.time.temporal.ChronoUnit;
import java.util.function.Consumer;

public class UnscheduledMinestomTask implements UnscheduledTask {

    private final Runnable runnable;
    private final Consumer<Task> cancellableConsumer;

    public UnscheduledMinestomTask(Runnable runnable, Consumer<Task> cancellableConsumer) {
        this.runnable = runnable;
        this.cancellableConsumer = cancellableConsumer;
    }

    @Override
    public Task runTaskAsynchronously() {
        MinestomTask task = new MinestomTask(MinecraftServer.getSchedulerManager().buildTask(runnable).schedule());
        cancellableConsumer.accept(task);
        return task;
    }

    @Override
    public Task runTaskLaterAsynchronously(long delayTicks) {
        MinestomTask task = new MinestomTask(MinecraftServer.getSchedulerManager()
                .buildTask(runnable)
                .delay(delayTicks * 50, ChronoUnit.MILLIS)
                .schedule()
        );
        cancellableConsumer.accept(task);
        return task;
    }

    @Override
    public Task runTaskTimerAsynchronously(long delayTicks, long periodTicks) {
        MinestomTask task = new MinestomTask(
                MinecraftServer.getSchedulerManager()
                        .buildTask(runnable)
                        .delay(delayTicks * 50, ChronoUnit.MILLIS)
                        .repeat(periodTicks * 50, ChronoUnit.MILLIS)
                        .schedule()
        );
        cancellableConsumer.accept(task);
        return task;
    }

    @Override
    public Task runTask() {
        return runTaskAsynchronously();
    }

    @Override
    public Task runTaskLater(long delayTicks) {
        return runTaskLaterAsynchronously(delayTicks);
    }

    @Override
    public Task runTaskTimer(long delayTicks, long periodTicks) {
        return runTaskTimerAsynchronously(delayTicks, periodTicks);
    }

}
