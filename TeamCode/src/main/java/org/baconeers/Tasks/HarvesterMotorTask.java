package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import org.baconeers.SkystoneDrive.NormalisedMecanumDrive;
import org.baconeers.SkystoneDrive.SkystoneConfiguration;

public class HarvesterMotorTask extends BaseTask implements Task    {

    private final NormalisedMecanumDrive drive;
    private final double speedY;
    private final SkystoneConfiguration config;
    private double HarvesterPower;
    private double Speed;



    public HarvesterMotorTask(BaconOpMode opMode, double time, SkystoneConfiguration config, NormalisedMecanumDrive drive, double HarvesterPower, double Speed) {
        super(opMode, time);
        this.speedY = Speed;
        this.config = config;
        this.drive = drive;
        this.HarvesterPower = HarvesterPower;


    }

    void update() {
         config.HarvesterServoRight.setPosition(HarvesterPower);
         config.HarvesterServoLeft.setPosition(HarvesterPower);

    }

    @Override
    public void run() {
        if (isFinished()) {
            HarvesterPower = 0;
            update();
            return;
        }
        update();
    }

}
