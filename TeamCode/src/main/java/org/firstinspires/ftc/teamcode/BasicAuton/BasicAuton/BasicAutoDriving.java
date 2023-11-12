package org.firstinspires.ftc.teamcode.BasicAuton.BasicAuton;

import com.qualcomm.robotcore.hardware.DcMotor;


public class BasicAutoDriving {

    public DcMotor frontLeftMotor = null;
    public DcMotor frontRightMotor = null;
    public DcMotor backLeftMotor = null;
    public DcMotor backRightMotor = null;

    //TODO: tune this again for this bot
    double ticksPerCentimeterDrive = 100;
    double ticksPerCentimeterStrafe = 150;
    double ticksPerDegree = 5;

    int error = 8;

    public BasicAutoDriving (DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight)
    {
        frontLeftMotor = frontLeft;
        frontRightMotor = frontRight;
        backLeftMotor = backLeft;
        backRightMotor = backRight;

        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeftMotor.setTargetPosition(0);
        frontRightMotor.setTargetPosition(0);
        backLeftMotor.setTargetPosition(0);
        backRightMotor.setTargetPosition(0);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0.5);
        backLeftMotor.setPower(0.5);
        backRightMotor.setPower(0.5);
    }



    public void drive (double cm){
        int frontLeftTargetPosition = frontLeftMotor.getCurrentPosition() + (int) (cm*ticksPerCentimeterDrive);
        int frontRightTargetPosition = frontRightMotor.getCurrentPosition() + (int) (cm*ticksPerCentimeterDrive);
        int backLeftTargetPosition = backLeftMotor.getCurrentPosition() + (int) (cm*ticksPerCentimeterDrive);
        int backRightTargetPosition = backRightMotor.getCurrentPosition() + (int) (cm*ticksPerCentimeterDrive);

        frontLeftMotor.setTargetPosition(frontLeftTargetPosition);
        frontRightMotor.setTargetPosition(frontRightTargetPosition);
        backLeftMotor.setTargetPosition(backLeftTargetPosition);
        backRightMotor.setTargetPosition(backRightTargetPosition);
    }

    public void strafe (double cm) {
        int frontLeftTargetPosition = frontLeftMotor.getCurrentPosition() - (int) (cm*ticksPerCentimeterStrafe);
        int frontRightTargetPosition = frontRightMotor.getCurrentPosition() + (int) (cm*ticksPerCentimeterStrafe);
        int backLeftTargetPosition = backLeftMotor.getCurrentPosition() + (int) (cm*ticksPerCentimeterStrafe);
        int backRightTargetPosition = backRightMotor.getCurrentPosition() - (int) (cm*ticksPerCentimeterStrafe);

        frontLeftMotor.setTargetPosition(frontLeftTargetPosition);
        frontRightMotor.setTargetPosition(frontRightTargetPosition);
        backLeftMotor.setTargetPosition(backLeftTargetPosition);
        backRightMotor.setTargetPosition(backRightTargetPosition);

        while (Math.abs(frontLeftTargetPosition - frontLeftMotor.getCurrentPosition()) > error ||
                Math.abs(frontRightTargetPosition - frontRightMotor.getCurrentPosition()) > error ||
                Math.abs(backLeftTargetPosition - backLeftMotor.getCurrentPosition()) > error ||
                Math.abs(backRightTargetPosition - backRightMotor.getCurrentPosition()) > error) {

            int x = 0;

        }
    }

    public void turn (double degrees) {
        int frontLeftTargetPosition = frontLeftMotor.getCurrentPosition() + (int) (degrees*ticksPerDegree);
        int frontRightTargetPosition = frontRightMotor.getCurrentPosition() - (int) (degrees*ticksPerDegree);
        int backLeftTargetPosition = backLeftMotor.getCurrentPosition() + (int) (degrees*ticksPerDegree);
        int backRightTargetPosition = backRightMotor.getCurrentPosition() - (int) (degrees*ticksPerDegree);

        frontLeftMotor.setTargetPosition(frontLeftTargetPosition);
        frontRightMotor.setTargetPosition(frontRightTargetPosition);
        backLeftMotor.setTargetPosition(backLeftTargetPosition);
        backRightMotor.setTargetPosition(backRightTargetPosition);
    }

}