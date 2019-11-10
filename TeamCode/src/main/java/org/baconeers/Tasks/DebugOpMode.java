package org.baconeers.Tasks;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.baconeers.common.BaconOpMode;

import java.util.ArrayDeque;

import SkystoneDrive.NormalisedMecanumDrive;
import SkystoneDrive.SkystoneConfiguration;


@TeleOp(name = "DebugOpMode")
public class DebugOpMode extends BaconOpMode {

    private SkystoneConfiguration config;
    private NormalisedMecanumDrive mecanumDrive = null;
    private ArrayDeque<Task> debug = new ArrayDeque<>();

    @Override
    protected void onInit() {
        config = SkystoneConfiguration.newConfig(hardwareMap, telemetry);
        mecanumDrive = new NormalisedMecanumDrive(this,
                config.frontLeftMotor, config.frontRightMotor,
                config.backLeftMotor, config.backRightMotor,
                false);

        debug.add(new MotorTestTask(this, 2.0, config, 1, 0, 0, 0));
        debug.add(new MotorTestTask(this, 2.0, config, 0, 1, 0, 0));
        debug.add(new MotorTestTask(this, 2.0, config, 0, 0, 1, 0));
        debug.add(new MotorTestTask(this, 2.0, config, 0, 0, 0, 1));
        

    }


    @Override
    protected void activeLoop() throws InterruptedException {
        Task currentTask = debug.peekFirst();
        if (currentTask == null) {
            return;
        }
        currentTask.run();
        if (currentTask.isFinished()){
            debug.removeFirst();
        }
        if (debug.isEmpty()) {
            mecanumDrive.setSpeedXYR(0, 0, 0);
            mecanumDrive.update();
        }
    }
}
