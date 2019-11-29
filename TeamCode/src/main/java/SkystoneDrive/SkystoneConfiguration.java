package SkystoneDrive;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.baconeers.common.RobotConfiguration;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;

import java.util.Iterator;

/**
 * It is assumed that there is a configuration that is currently activated on the robot controller
 * (run menu / Configure Robot ) with the same name as this class.
 * It is also assumed that the device names in the 'init()' method below are the same as the devices
 * named on the activated configuration on the robot.
 */
public class SkystoneConfiguration extends RobotConfiguration {

    public DcMotor frontLeftMotor = null;
    public DcMotor frontRightMotor = null;
    public DcMotor backLeftMotor = null;
    public DcMotor backRightMotor = null;
    public CRServo gripServo = null; // Grips the stone
    public CRServo movementServo = null; // Moves the stone tongs
    public DcMotor liftMotor = null;
    public Servo CapStoneServo = null; // Capstone servo
    public Servo HarvesterServoRight = null;
    public Servo HarvesterServoLeft = null;
    public CRServo FoundationServo = null;
    public DcMotor HarvesterRight = null;
    public DcMotor HarvesterLeft = null;
    public Camera Cam = null;


    /**
     * Assign your class instance variables to the saved device names in the hardware map
     *
     * @param hardwareMap
     * @param telemetry
     */
    @Override
    protected void init(HardwareMap hardwareMap, final Telemetry telemetry) {
        setTelemetry(telemetry);

        try {
            frontLeftMotor  = hardwareMap.get(DcMotor.class, "FrontLeftMotor");
        } catch (Exception e) {
            telemetry.addLine("FrontLeftMotor failed to configure");
        }
        try {
            frontRightMotor = hardwareMap.get(DcMotor.class, "FrontRightMotor");
        } catch (Exception e) {
            telemetry.addLine("FrontRightMotor failed to configure");
        }
        try {
            backLeftMotor = hardwareMap.get(DcMotor.class,"BackLeftMotor");
        } catch (Exception e) {
            telemetry.addLine("BackLeftMotor failed to configure");
        }
        try {
            backRightMotor = hardwareMap.get(DcMotor.class,"BackRightMotor");
        } catch (Exception e) {
            telemetry.addLine("BackRightMotor failed to configure");
        }
        try {
            gripServo = hardwareMap.get(CRServo.class, "gripServo");
        } catch (Exception e) {
            telemetry.addLine("gripServo failed to configure");
        }
        try {
            movementServo = hardwareMap.get(CRServo.class, "movementServo");
        } catch (Exception e) {
            telemetry.addLine("movementServo failed to configure");
        }
        try {
            liftMotor = hardwareMap.get(DcMotor.class, "LiftMotor");
            if (liftMotor != null) {
                liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
        } catch (Exception e) {
            telemetry.addLine("LiftMotor failed to configure");
            telemetry.addLine(e.getMessage());
        }
        try {
            HarvesterLeft = hardwareMap.get(DcMotor.class, "HarvesterLeftMotor");
        } catch (Exception e) {
            telemetry.addLine("HarvesterLeftMotor failed to configure");
        }
        try {
            HarvesterRight = hardwareMap.get(DcMotor.class, "HarvesterRightMotor");
        } catch (Exception e) {
            telemetry.addLine("HarvesterRightMotor failed to configure");
        }
        try {
            Cam = hardwareMap.get(Camera.class, "Cam");
        } catch (Exception e) {
            telemetry.addLine("Camera failed to configure");
        }

        if (frontLeftMotor != null && frontRightMotor != null && backLeftMotor != null && backRightMotor != null) {
            frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
            frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
            backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
            backRightMotor.setDirection(DcMotor.Direction.FORWARD);

            frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        if (HarvesterRight != null && HarvesterLeft != null ) {
            HarvesterRight.setDirection(DcMotor.Direction.FORWARD);
            HarvesterLeft.setDirection(DcMotor.Direction.REVERSE);

            HarvesterRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            HarvesterLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        }

        try {
            CapStoneServo = hardwareMap.get (Servo.class, "CapStone");
        } catch (Exception e) {
            telemetry.addLine("CapStone failed to configure");
        }
        try {
            HarvesterServoLeft = hardwareMap.get(Servo.class, "HarvesterServoLeft");
        } catch (Exception e) {
            telemetry.addLine("HarvesterServoLeft failed to configure");
        }
        try {
            HarvesterServoRight = hardwareMap.get(Servo.class, "HarvesterServoRight");
        } catch (Exception e) {
            telemetry.addLine("HarvesterServoRight failed to configure");
        }
        try {
            FoundationServo = hardwareMap.get(CRServo.class, "FoundationServo");
        } catch (Exception e) {
            telemetry.addLine("FoundationServo failed to configure");
        }

//        Iterator<HardwareMap.DeviceMapping<? extends HardwareDevice>> iter  = hardwareMap.allDeviceMappings.iterator();
//        while (iter.hasNext()) {
//            telemetry.addLine(iter.next().toString());
//        }



        telemetry.addData("Initialized", "True");
        telemetry.update();
    }
    //down 1.35
    //up 0.8

    /**
     * Factory method for this class
     *
     * @param hardwareMap
     * @param telemetry
     * @return
     */
    public static SkystoneConfiguration newConfig(HardwareMap hardwareMap, Telemetry telemetry) {
        SkystoneConfiguration config = new SkystoneConfiguration();
        config.init(hardwareMap, telemetry);
        return config;

    }


}
