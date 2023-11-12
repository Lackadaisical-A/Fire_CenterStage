package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

public class FireHardwareMap {
    //create drivetrain motors
    public DcMotor frontRightMotor = null;
    public DcMotor frontLeftMotor = null;
    public DcMotor backRightMotor = null;
    public DcMotor backLeftMotor = null;

    // create slide motors
    public DcMotor slideLeftMotor = null;
    public DcMotor slideRightMotor = null;
    public DcMotor actuatorMotor = null;

    //create mechanism servos
    public DcMotor intakeMotor = null;
    public CRServo boxLeftServo = null;
    public CRServo boxRightServo = null;
    public CRServo doorServo = null;
    public CRServo separatorServo = null;
    public CRServo hookServo = null;

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
        frontRightMotor = HardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = HardwareMap.get(DcMotor.class, "frontLeftMotor");
        backRightMotor = HardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = HardwareMap.get(DcMotor.class, "backLeftMotor");
        intakeMotor = HardwareMap.get(DcMotor.class, "intakeMotor");
        actuatorMotor = HardwareMap.get(DcMotor.class, "actuatorMotor");
        slideLeftMotor = HardwareMap.get(DcMotor.class, "slideLeftMotor");
        slideRightMotor = HardwareMap.get(DcMotor.class, "slideRightMotor");

        //Making servo
        boxLeftServo = HardwareMap.get(CRServo.class, "leftServo");
        boxRightServo = HardwareMap.get(CRServo.class, "rightServo");
        separatorServo = HardwareMap.get(CRServo.class, "separatorServo");
        doorServo = HardwareMap.get(CRServo.class, "doorServo");
        hookServo = HardwareMap.get(CRServo.class, "hookServo");

        //Set up motor direction
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        slideLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        slideRightMotor.setDirection((DcMotor.Direction.FORWARD));

        actuatorMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        boxLeftServo.setDirection(CRServo.Direction.FORWARD);
        boxRightServo.setDirection(CRServo.Direction.FORWARD);
        separatorServo.setDirection(CRServo.Direction.FORWARD);
        doorServo.setDirection(CRServo.Direction.FORWARD);
        hookServo.setDirection(CRServo.Direction.FORWARD);

        //Set motor mode
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        slideLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Set zero power behavior

        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        actuatorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Set 0 power
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        intakeMotor.setPower(0);
        actuatorMotor.setPower(0);

//        boxLeftServo.setPower(0.0);
//        boxRightServo.setPower(0.0);
//        separatorServo.setPower(0.0);
//        doorServo.setPower(0.0);
//        hookServo.setPower(0.0);



        slideLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideLeftMotor.setTargetPosition(0);
        slideRightMotor.setTargetPosition(0);
        slideLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideLeftMotor.setPower(0.6);
        slideRightMotor.setPower(0.6);

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