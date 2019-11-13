package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import SkystoneDrive.SkystoneConfiguration;

public class TongsTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private double liftPower;

    public TongsTask(BaconOpMode opMode, double time, SkystoneConfiguration config, double liftPower) {
        super(opMode, time);
        this.config = config;
        this.liftPower = liftPower;

    }

    void update() {

    }

    @Override
    public void run() {
        if (isFinished()) {
            update();
            return;
        }
        update();
    }

}
