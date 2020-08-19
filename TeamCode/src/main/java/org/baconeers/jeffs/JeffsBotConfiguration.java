package org.baconeers.jeffs;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.baconeers.common.RobotConfiguration;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * It is assumed that there is a configuration that is currently activated on the robot controller
 * (run menu / Configure Robot ) with the same name as this class.
 * It is also assumed that the device names in the 'init()' method below are the same as the devices
 * named on the activated configuration on the robot.
 */
public class JeffsBotConfiguration extends RobotConfiguration {
    // Left motors
    public DcMotor leftMotor;
    public DcMotor rightMotor;

    public double countsPerMotorRev = 1440;
    public double driveGearReduction = 90.0/72.0; // 90 Teeth -> 72 Teeth
    public double wheelDiameterCm = 9.0;

    public double countsPerCm = (countsPerMotorRev * driveGearReduction)/(wheelDiameterCm * 3.14159);

    /**
     * Assign your class instance variables to the saved device names in the hardware map
     *
     * @param hardwareMap
     * @param telemetry
     */
    @Override
    protected void init(HardwareMap hardwareMap, Telemetry telemetry) {

        setTelemetry(telemetry);

        leftMotor = (DcMotor) getHardwareOn("leftMotor", hardwareMap.dcMotor);

        rightMotor = (DcMotor) getHardwareOn("rightMotor", hardwareMap.dcMotor);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    /**
     * Factory method for this class
     *
     * @param hardwareMap
     * @param telemetry
     * @return
     */
    public static JeffsBotConfiguration newConfig(HardwareMap hardwareMap, Telemetry telemetry) {

        JeffsBotConfiguration config = new JeffsBotConfiguration();
        config.init(hardwareMap, telemetry);
        return config;
    }


}
