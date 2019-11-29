package org.baconeers.stoneStackingMechanism;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.baconeers.common.BaconOpMode;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;

@TeleOp(name = "MovementServoTest")
public class TongsLiftTest extends BaconOpMode {
    private SkystoneConfiguration config;
    private StoneTongsClass tongs;

    @Override
    protected void onInit() {
        config = SkystoneConfiguration.newConfig(hardwareMap, telemetry);
        tongs = new StoneTongsClass(this, config);
    }

    @Override
    protected void activeLoop() throws InterruptedException {
            tongs.update();
    }
}
