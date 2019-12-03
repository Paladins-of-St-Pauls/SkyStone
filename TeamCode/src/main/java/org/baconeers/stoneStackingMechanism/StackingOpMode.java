package org.baconeers.stoneStackingMechanism;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.baconeers.common.BaconOpMode;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;
import org.baconeers.SkystoneDrive.SkystoneDrive;

@TeleOp(name = "StackingTest")
public class StackingOpMode extends BaconOpMode {
    private SkystoneConfiguration config;
    private StoneTongs tongs;
    private TongsLift lift;
    private SkystoneDrive drive;

    @Override
    protected void onInit() {
       config  = SkystoneConfiguration.newConfig(hardwareMap, telemetry);

       tongs = new StoneTongs(this, config);
       lift = new TongsLift(this, config, telemetry);
       drive = new SkystoneDrive(this, config);

    }

    @Override
    protected void activeLoop() throws InterruptedException {
        drive.update();
        tongs.update();
        lift.update();
        
    }
}
