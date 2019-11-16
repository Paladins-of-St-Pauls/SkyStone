package org.baconeers.Tasks;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.baconeers.common.BaconOpMode;

import java.util.ArrayDeque;

import SkystoneDrive.NormalisedMecanumDrive;
import SkystoneDrive.SkystoneConfiguration;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "SkyAuto")
public class Autonomous extends BaconOpMode {

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

        tasks.add(new DriveXYRTask (this, 1.82, mecanumDrive, -0.3, 0, 0));
        tasks.add(new FoundationTask(this,0.5, config, 1));
        tasks.add(new DriveXYRTask (this, 1.71, mecanumDrive, 0.3, 0, 0));
        tasks.add(new FoundationTask(this, 0.5, config, -1));
        tasks.add(new DriveXYRTask (this, 4.27, mecanumDrive, 0, 0.3, 0));
        tasks.add(new DriveXYRTask(this, 0.2 ,mecanumDrive, 0,0,-0.3 ));
        tasks.add(new DriveXYRTask (this, 0.11, mecanumDrive, -0.3, 0,0));
        tasks.add(new DriveXYRTask(this,1.82, mecanumDrive,0.3,0,0));
        tasks.add(new HarvesterServoTask(this,0.5, config,1));
        tasks.add(new HarvesterMotorTask (this,0.3, config, mecanumDrive, 1,0.3));
        tasks.add(new DriveXYRTask (this, 1.43, mecanumDrive, -0.3, 0, 0));
        tasks.add(new DriveXYRTask (this, 4.27, mecanumDrive, 0, -0.3, 0));
        tasks.add(new HarvesterReleaseTask(this,0.5, config, 1));
        tasks.add(new DriveXYRTask (this, 5.64, mecanumDrive, 0, 0.3, 0));
        tasks.add(new DriveXYRTask (this, 1.44, mecanumDrive, 0.3, 0,0));
        tasks.add(new HarvesterServoTask(this,0.5, config,1));
        tasks.add(new HarvesterMotorTask (this,0.3, config, mecanumDrive, 1,0.3));
        tasks.add(new DriveXYRTask (this, 1.43, mecanumDrive, -0.3, 0, 0));
        tasks.add(new DriveXYRTask (this, 5.64, mecanumDrive, 0, -0.3, 0));
        tasks.add(new HarvesterReleaseTask(this,0.5, config, 1));
        tasks.add(new DriveXYRTask (this, 5.64, mecanumDrive, 0, 0.3, 0));
        tasks.add(new HarvesterReleaseTask(this,0.5, config, 1));
        tasks.add(new DriveXYRTask(this,2.8, mecanumDrive,0,0.3,0));


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
