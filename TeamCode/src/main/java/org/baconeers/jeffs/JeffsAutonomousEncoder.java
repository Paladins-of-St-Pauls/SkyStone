package org.baconeers.jeffs;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.baconeers.Tasks.TankDriveEncTask;
import org.baconeers.Tasks.Task;
import org.baconeers.common.BaconOpMode;
import org.baconeers.common.TankDrive;

import java.util.ArrayDeque;

@Autonomous(name = "JeffsAutonomousEncoder")
public class JeffsAutonomousEncoder extends BaconOpMode {
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
        tasks.add(new TankDriveEncTask(this, 5, drive, 0.8, 0.8, 100, 100));
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