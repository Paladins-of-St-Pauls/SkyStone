package org.baconeers.Tasks;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.baconeers.common.BaconOpMode;

import java.util.ArrayDeque;
import java.util.Deque;

import SkystoneDrive.SkystoneConfiguration;
import SkystoneDrive.SkystoneDrive;

@TeleOp(name = "TaskTest")
public class TaskTestOpMode extends BaconOpMode {

    private ArrayDeque<Task> tasks = new ArrayDeque<>();

    @Override
    protected void onInit() {
        tasks.add(new MessageTask(this, "one", 2.3));
        tasks.add(new MessageTask(this, "two", 1.95));
        tasks.add(new MessageTask(this, "three", 5.71));

    }


    @Override
    protected void activeLoop() throws InterruptedException {
        Task currentTask = tasks.peekFirst();
        if (currentTask == null) {
            return;
        }
        currentTask.run();
        if (currentTask.isFinished()){
            tasks.removeFirst();
        }
    }
}
