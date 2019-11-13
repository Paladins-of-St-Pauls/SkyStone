package org.baconeers.stoneStackingMechanism;

import org.baconeers.common.BaconComponent;
import org.baconeers.common.BaconOpMode;

import SkystoneDrive.SkystoneConfiguration;

public class Harvester extends BaconComponent {

    private BaconOpMode opmode;
    private SkystoneConfiguration config;

    public Harvester (BaconOpMode opmodeIn, SkystoneConfiguration configIn) {
        super(opmodeIn);
        opmode = opmodeIn;
        config = configIn;
    }

    public void update() {
        

    }

}
