package org.baconeers.stoneStackingMechanism;

import org.baconeers.common.BaconComponent;
import org.baconeers.common.BaconOpMode;

public class stoneTongsClass extends BaconComponent {

    private BaconOpMode opmode;
    private SkystoneConfiguration config;

    public stoneTongsClass(BaconOpMode opmodeIn, SkystoneConfiguration configIn) {
        super(opmodeIn);
        opmode = opmodeIn;
        config = configIn;

    }

    public void update() {
        

    }


}
