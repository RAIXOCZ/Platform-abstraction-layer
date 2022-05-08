package net.playeranalytics.plugin.scheduling;

public class MinestomTask implements Task {

    private final net.minestom.server.timer.Task task;

    public MinestomTask(net.minestom.server.timer.Task task) {this.task = task;}

    @Override
    public boolean isGameThread() {
        return true;
    }

    @Override
    public void cancel() {
        task.cancel();
    }
}
