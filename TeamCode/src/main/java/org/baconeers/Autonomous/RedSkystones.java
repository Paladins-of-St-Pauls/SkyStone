package org.baconeers.Autonomous;

import org.baconeers.SkystoneDrive.NormalisedMecanumDrive;
import org.baconeers.SkystoneDrive.SkystoneConfiguration;
import org.baconeers.Tasks.DriveXYRTask;
import org.baconeers.Tasks.Task;
import org.baconeers.common.BaconOpMode;

import java.util.ArrayDeque;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RedSkystones")
public class RedSkystones extends BaconOpMode {

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

        tasks.add(new DriveXYRTask(this, 1.82, mecanumDrive, -0.3, 0, 0));

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
