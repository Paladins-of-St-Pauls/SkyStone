package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import org.baconeers.SkystoneDrive.NormalisedMecanumDrive;
import org.baconeers.SkystoneDrive.SkystoneConfiguration;

public class HarvesterMotorTask extends BaseTask implements Task    {

    private final NormalisedMecanumDrive drive;
    private final double speedY;
    private final SkystoneConfiguration config;
    private double harvesterPower;
    private double Speed;



    public HarvesterMotorTask(BaconOpMode opMode, double time, SkystoneConfiguration config, NormalisedMecanumDrive drive, double harvesterPower, double Speed) {
        super(opMode, time);
        this.speedY = Speed;
        this.config = config;
        this.drive = drive;
        this.harvesterPower = harvesterPower;


    }

    void update() {
         config.harvesterServoRight.setPosition(harvesterPower);
         config.harvesterServoLeft.setPosition(harvesterPower);

    }

    @Override
    public void run() {
        if (isFinished()) {
            harvesterPower = 0;
            update();
            return;
        }
        update();
    }

}
