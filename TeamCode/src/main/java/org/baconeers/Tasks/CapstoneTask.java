package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;

public class CapstoneTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private double CapstonePower;

    public CapstoneTask(BaconOpMode opMode, double time, SkystoneConfiguration config, double CapstonePower) {
        super(opMode, time);
        this.config = config;
        this.CapstonePower = CapstonePower;

    }

    void update() {
         config.CapStoneServo.setPosition(CapstonePower);


    }

    @Override
    public void run() {
        if (isFinished()) {
            CapstonePower = 0;
            update();
            return;
        }
        update();
    }

}
