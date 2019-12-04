package org.baconeers.Autonomous;

import org.baconeers.SkystoneDrive.NormalisedMecanumDrive;
import org.baconeers.SkystoneDrive.SkystoneConfiguration;
import org.baconeers.Tasks.DriveXYRTask;
import org.baconeers.Tasks.FoundationTask;
import org.baconeers.Tasks.HarvesterMotorTask;
import org.baconeers.Tasks.HarvesterReleaseTask;
import org.baconeers.Tasks.HarvesterServoTask;
import org.baconeers.Tasks.Task;
import org.baconeers.common.BaconOpMode;

import java.util.ArrayDeque;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RedFoundation")
public class RedFoundation extends BaconOpMode {

    private SkystoneConfiguration config;
    private NormalisedMecanumDrive mecanumDrive = null;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();

    @Override
    protected void onInit() {
        config = SkystoneConfiguration.newConfig(hardwareMap, telemetry);
        mecanumDrive = new NormalisedMecanumDrive(this,
                config.frontLeftMotor, config.frontRightMotor,
                config.backLeftMotor, config.backRightMotor,
                false);

        tasks.add(new DriveXYRTask(this, getDriveTime(78), mecanumDrive, -0.3, 0.0, 0.0));
        tasks.add(new FoundationTask(this, 1.0, config, 0.0));
        tasks.add(new DriveXYRTask(this, getDriveTime(68), mecanumDrive, 0.3, 0.0, 0.0));
        tasks.add(new DriveXYRTask(this, 0.5, mecanumDrive, 0.0, 0.0, 0.3));
        tasks.add(new FoundationTask(this, 1.0, config, 1.0));
        tasks.add(new DriveXYRTask(this, 0.5, mecanumDrive, 0.0, 0.0, -0.3));
        tasks.add(new DriveXYRTask(this,getDriveTime(14), mecanumDrive, -0.3, 0.0,0.0));
        tasks.add(new DriveXYRTask(this, getDriveTime(110), mecanumDrive, 0.0, -0.3, 0.0));

    }

    private double getDriveTime(double distance) {
        double driveTime = 0.0186*distance+0.1819;
        return driveTime;
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
            mecanumDrive.setSpeedXYR(0, 0, 0);
            mecanumDrive.update();
        }
    }
}
