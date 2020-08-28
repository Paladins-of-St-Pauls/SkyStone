package org.baconeers.jeffs;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.baconeers.Tasks.TankDriveEncTask;
import org.baconeers.Tasks.TankDriveTask;
import org.baconeers.Tasks.Task;
import org.baconeers.common.BaconOpMode;
import org.baconeers.common.TankDrive;

import java.util.ArrayDeque;

@Autonomous(name = "JeffsAutonomous1")
public class JeffsAutonomous1 extends BaconOpMode {
    private JeffsBotConfiguration config;
    private TankDrive drive;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();

    @Override
    protected void onInit() {
        config = JeffsBotConfiguration.newConfig(hardwareMap, telemetry);

        drive = new TankDrive(this, gamepad1, config.leftMotor, config.rightMotor);
        drive.setCountsPerCm(config.countsPerCm);
        config.leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        config.rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        tasks.add(new TankDriveEncTask(this, 0.5, drive, 0.2, 0.2, 20, 20));
        tasks.add(new TankDriveEncTask(this, 20, drive, 0.5, 0.5, 300, 300));
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