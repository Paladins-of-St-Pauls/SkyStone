package org.baconeers.stoneStackingMechanism;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.baconeers.common.BaconOpMode;

import SkystoneDrive.SkystoneConfiguration;
import SkystoneDrive.SkystoneDrive;

@TeleOp(name = "StackingTest")
public class StackingOpMode extends BaconOpMode {
    private SkystoneConfiguration config;
    private StoneTongsClass tongs;
    private TongsLiftClass lift;

    @Override
    protected void onInit() {
       config  = SkystoneConfiguration.newConfig(hardwareMap, telemetry);

       tongs = new StoneTongsClass(this, config);
       lift = new TongsLiftClass(this, config);

    }

    @Override
    protected void activeLoop() throws InterruptedException {
        tongs.update();
        lift.update();
        
    }
}
