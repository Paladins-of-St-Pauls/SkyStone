package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;

public class TongsTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;

    public TongsTask(BaconOpMode opMode, double time, SkystoneConfiguration config, double liftPower) {
        super(opMode, time);
        this.config = config;

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
