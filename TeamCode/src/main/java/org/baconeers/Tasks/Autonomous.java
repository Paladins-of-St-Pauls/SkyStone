package org.baconeers.Tasks;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.baconeers.common.BaconOpMode;

import java.util.ArrayDeque;

import SkystoneDrive.NormalisedMecanumDrive;
import SkystoneDrive.SkystoneConfiguration;


@TeleOp(name = "TaskTest")
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

        tasks.add(new DriveXYRTask (this, 0.3175, mecanumDrive, 0, 0.3, 0));
        tasks.add(new DriveXYRTask (this, 0.289, mecanumDrive, 0, -0.3, 0));
        tasks.add(new DriveXYRTask (this, 4.272, mecanumDrive, -0.3, 0, 0));
        tasks.add(new DriveXYRTask (this, 0.26, mecanumDrive, 0, 0.3,0));
        //(pick up) tasks.add(new SkyGrab (this,{add parameters here}));
        tasks.add(new DriveXYRTask (this, 0.231, mecanumDrive, 0, -0.3, 0));
        tasks.add(new DriveXYRTask (this, 4.272, mecanumDrive, 0.3, 0, 0));
        tasks.add(new DriveXYRTask (this,0.173,mecanumDrive,0, 0.3, 0));
        //(Let go) tasks.add(new SkyGrab(this, {add parameters here}));
        tasks.add(new DriveXYRTask(this,0.173, mecanumDrive, 0,0.3,0));
        tasks.add(new DriveXYRTask (this, 5.408, mecanumDrive, -0.3, 0, 0));
        tasks.add(new DriveXYRTask (this, 0.26, mecanumDrive, 0, 0.3,0));
        //(pick up)_tasks.add(new SkyGrab (this, 0.3175, mecanumDrive, 0.3, 0.3, 0.3));
        tasks.add(new DriveXYRTask (this, 0.231, mecanumDrive, 0, -0.3, 0));
        tasks.add(new DriveXYRTask (this, 5.408, mecanumDrive, 0.3, 0, 0));
        tasks.add(new DriveXYRTask (this,0.173,mecanumDrive,0, 0.3, 0));
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
