package org.baconeers.Tasks;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.baconeers.common.BaconOpMode;

import java.util.ArrayDeque;


import org.baconeers.SkystoneDrive.NormalisedMecanumDrive;
import org.baconeers.SkystoneDrive.SkystoneConfiguration;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "TaskTest")
public class TaskTestOpMode extends BaconOpMode {

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

        tasks.add(new FoundationTask(this, 1.0, config, 0.0));
        tasks.add(new DriveXYRTask(this, 5.0, mecanumDrive, 0.0, 0.0, 0.0));
        tasks.add(new FoundationTask(this, 1.0, config, 0.7));
        tasks.add(new DriveXYRTask(this, 5.0, mecanumDrive, 0.0, 0.0, 0.0));

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
