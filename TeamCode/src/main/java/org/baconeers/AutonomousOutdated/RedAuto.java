package org.baconeers.AutonomousOutdated;

import org.baconeers.Tasks.DriveXYRTask;
import org.baconeers.Tasks.FoundationTask;
import org.baconeers.Tasks.Task;
import org.baconeers.common.BaconOpMode;

import java.util.ArrayDeque;

import org.baconeers.SkystoneDrive.NormalisedMecanumDrive;
import org.baconeers.SkystoneDrive.SkystoneConfiguration;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RedAuto")
public class RedAuto extends BaconOpMode {

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

        tasks.add(new DriveXYRTask(this, 1.75, mecanumDrive, -0.3, 0, 0));
        tasks.add(new FoundationTask(this,2, config, -1));
        tasks.add(new DriveXYRTask (this, 1.80, mecanumDrive, 0.3, 0, 0));
        tasks.add(new FoundationTask(this, 2, config, 1));
        tasks.add(new DriveXYRTask (this, 2.80, mecanumDrive, 0, -0.3, 0));
        tasks.add(new DriveXYRTask(this, 2.0, mecanumDrive, 0, 0.3,0));



        //(Let go) tasks.add(new SkyGrab(this, {add parameters here}));
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
