package net.playeranalytics.plugin.scheduling;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MinestomRunnableFactory implements RunnableFactory {

    private final Set<Task> tasks = Collections.newSetFromMap(new ConcurrentHashMap<>());

    @Override
    public UnscheduledTask create(Runnable runnable) {
        return new UnscheduledMinestomTask(runnable, tasks::add);
    }

    @Override
    public UnscheduledTask create(PluginRunnable runnable) {
        return new UnscheduledMinestomTask(runnable, task -> {
            tasks.add(task);
            runnable.setCancellable(task);
        });
    }

    @Override
    public void cancelAllKnownTasks() {
        for (Task task : tasks) {
            task.cancel();
        }
        tasks.clear();
    }
}
