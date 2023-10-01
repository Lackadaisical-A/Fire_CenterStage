package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.motors.RevRoboticsUltraPlanetaryHdHexMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import com.qualcomm.hardware.bosch.BHI260IMU;

public class FireHardwareMap {
    //create motors
    public DcMotor FrontRightMotor = null;
    public DcMotor FrontLeftMotor = null;
    public DcMotor BackRightMotor = null;
    public DcMotor BackLeftMotor = null;

    //create servos


    public BNO055IMU imu = null;


    //Hardware Map object
    com.qualcomm.robotcore.hardware.HardwareMap HardwareMap = null;

    public ElapsedTime runtime = new ElapsedTime();

    public FireHardwareMap(com.qualcomm.robotcore.hardware.HardwareMap hwmap){

        initialize(hwmap);
    }

    private void initialize(com.qualcomm.robotcore.hardware.HardwareMap hwmap){
        HardwareMap = hwmap;
        //the name of device should change based on name
        FrontRightMotor = HardwareMap.get(DcMotor.class, "frontRightMotor");
        FrontLeftMotor = HardwareMap.get(DcMotor.class, "frontLeftMotor");
        BackRightMotor = HardwareMap.get(DcMotor.class, "backRightMotor");
        BackLeftMotor = HardwareMap.get(DcMotor.class, "backLeftMotor");



        //Making servo
        //leftServo = HardwareMap.get(Servo.class, "leftServo");

        //Set up motor direction

        FrontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        FrontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        BackRightMotor.setDirection(DcMotor.Direction.FORWARD);
        BackLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        //Set motor mode
        FrontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);





        //Set zero power behavior

        FrontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //Set 0 power
        FrontRightMotor.setPower(0);
        FrontLeftMotor.setPower(0);
        BackRightMotor.setPower(0);
        BackLeftMotor.setPower(0);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        //return value of radians
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        //gets imu from rev hardware map and connects it to code
        imu = hwmap.get(BNO055IMU.class, "imuex");
        //sets the settings we declared above.
        imu.initialize(parameters);
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);


    }








}
