package org.baconeers.Autonomous;

import org.baconeers.SkystoneDrive.NormalisedMecanumDrive;
import org.baconeers.SkystoneDrive.SkystoneConfiguration;
import org.baconeers.Tasks.DetectSkystoneTask;
import org.baconeers.Tasks.DriveXYRTask;
import org.baconeers.Tasks.Task;
import org.baconeers.common.BaconOpMode;

import java.util.ArrayDeque;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "BlueSkystones")
public class BlueSkystones extends BaconOpMode {

    private SkystoneConfiguration config;
    private NormalisedMecanumDrive mecanumDrive = null;
    private ArrayDeque<Task> tasks = new ArrayDeque<>();
    private int stoneCount = 1;

    @Override
    protected void onInit() {
        config = SkystoneConfiguration.newConfig(hardwareMap, telemetry);
        mecanumDrive = new NormalisedMecanumDrive(this,
                config.frontLeftMotor, config.frontRightMotor,
                config.backLeftMotor, config.backRightMotor,
                false);

        //Pseudo-code type autonomous until distances are calculated
        tasks.add(new DriveXYRTask(this, 1.82, mecanumDrive, 0.3, 0, 0));
        tasks.add(new DetectSkystoneTask(this, 2.0, config, tasks, stoneCount, -1));
        tasks.add(new DriveXYRTask(this, 2.0, mecanumDrive, 0.0, 0.0, -0.3));
        tasks.add(new DriveXYRTask(this, 5.0, mecanumDrive, 0.3, 0.0, 0.0));

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
