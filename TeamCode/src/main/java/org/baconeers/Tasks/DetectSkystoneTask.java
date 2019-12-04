package org.baconeers.Tasks;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.baconeers.SkystoneDrive.NormalisedMecanumDrive;
import org.baconeers.common.BaconOpMode;

import java.util.ArrayDeque;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;

public class DetectSkystoneTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private final ArrayDeque<Task> tasks;
    private NormalisedMecanumDrive mecanumDrive = null;
    private int stoneCount;
    private double colour;
    private int direction;

    public DetectSkystoneTask(BaconOpMode opMode, double time, SkystoneConfiguration config, ArrayDeque<Task> tasks, int stoneCount, int direction) {
        super(opMode, time);
        this.config = config;
        this.tasks = tasks;
        this.stoneCount = stoneCount;
        this.direction = direction;

        mecanumDrive = new NormalisedMecanumDrive(opMode,
                config.frontLeftMotor, config.frontRightMotor,
                config.backLeftMotor, config.backRightMotor,
                false);

    }

    void update() {
        colour = config.colourSensor.alpha();
    }

    @Override
    public void run() {
        if (isFinished()) {

            //Pseudo-code for detecting stones until colour sensor is coded
            if (colour == 1 && stoneCount < 6) {
                stoneCount += 1;
                tasks.addFirst(new DetectSkystoneTask(opMode, 2.0, config, tasks, stoneCount,1));
                tasks.addFirst(new DriveXYRTask(opMode, 2.0, mecanumDrive, 0.3*direction, 0.0*direction, 0.0*direction));

            } else if (colour == 1 && stoneCount == 6) {
                tasks.addFirst(new SkystoneServoTask(opMode, 1.0, config, 1.0));
                tasks.addFirst(new DriveXYRTask(opMode, 2.0, mecanumDrive,0.0*direction, 0.3*direction, 0.0*direction));

            } else if (colour == 0) {
                tasks.addFirst(new SkystoneServoTask(opMode, 1.0, config, 1.0));
                tasks.addFirst(new DriveXYRTask(opMode, 2.0, mecanumDrive,0.0*direction, 0.3*direction, 0.0*direction));

            }

            return;
        }
        update();

    }

}
