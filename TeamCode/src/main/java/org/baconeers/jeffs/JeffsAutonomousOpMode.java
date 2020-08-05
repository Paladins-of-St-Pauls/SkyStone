package org.baconeers.jeffs;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.baconeers.Tasks.DriveXYRTask;
import org.baconeers.Tasks.TankDriveTask;
import org.baconeers.Tasks.Task;
import org.baconeers.common.BaconOpMode;
import org.baconeers.common.GamePadSteerDrive;
import org.baconeers.common.TankDrive;
import org.baconeers.testbot.JeffsBotConfiguration;

import java.util.ArrayDeque;

@Autonomous(name = "JeffsAutonomousOpMode")
public class JeffsAutonomousOpMode extends BaconOpMode {
    private JeffsBotConfiguration config;
    private TankDrive drive;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();

    @Override
    protected void onInit() {
        config = JeffsBotConfiguration.newConfig(hardwareMap, telemetry);

        drive = new TankDrive(this, gamepad1, config.leftMotor, config.rightMotor);
        tasks.add(new TankDriveTask(this, 1.82, drive, 0.3, 0));
        tasks.add(new TankDriveTask(this, 1.82, drive, 0, 0.3));
        tasks.add(new TankDriveTask(this, 3, drive, 1, 1));
        tasks.add(new TankDriveTask(this, 2  , drive, 1, -1));
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
        if (tasks.isEmpty()) {
            drive.setPower(0, 0);
            drive.update();
        }
    }
}