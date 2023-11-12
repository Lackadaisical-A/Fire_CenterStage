package org.firstinspires.ftc.teamcode;


import static org.firstinspires.ftc.teamcode.Angle.fromRadians;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ActiveLocation implements Runnable{

    //Hardware Set Up
    private BNO055IMU imu;
    //TODO: Will be implemented once encoders are attached to bot
    private DcMotor xDirectionEncoder;
    private DcMotor yDirectionEncoder;

    //Encoder values
    private double yEncoderValue = 0;
    private double xEncoderValue = 0;

    //Sets the angle that the robot starts at, what it will be reset to if driver wishes, and what it started from
    Angle angle = fromRadians(0);
    Angle resetAngle = fromRadians(0);
    Angle startAngle = fromRadians(0);

    //Field Position, will be fully implemented when Encoders are added
    double fieldXPosition = 0;
    double fieldYPosition = 0;

    //Boolean to control when Active Location Stops
    private boolean isRunning = true;

    //Constants for calculations
    //Ticks per rotation is for encoders, and wheelCircumferance should be of encoders not mecanum wheels
    final static double ticksPerRotation = 8192;
    //TODO: Check calculation as size is different
    final static double wheelCircumference = 90*Math.PI;





    //Sets up the hardware and creates the class, Constructor
    public ActiveLocation (FireHardwareMap robot){
        this.imu = robot.imu;
        //TODO: Will be implemented once encoders are attached to bot
        this.xDirectionEncoder = robot.backRightMotor;
        this.yDirectionEncoder = robot.frontLeftMotor;
    }


    public ActiveLocation(DcMotor xDirectionEncoder, DcMotor yDirectionEncoder,
                          BNO055IMU gyroscope) {
        //TODO: Will be implemented once encoders are attached to bot
        this.yDirectionEncoder = yDirectionEncoder;
        this.xDirectionEncoder = xDirectionEncoder;
        this.imu = gyroscope;
    }

    private static double tickToDistance(double ticks){
        return ((ticks/ticksPerRotation)*wheelCircumference);
    }

    //Not gonna be very accurate
    private static double distanceToTicks(double distanceInMiliMeters){
        return ((distanceInMiliMeters/wheelCircumference)*ticksPerRotation);
    }






    public void setStartPosition(double startX, double startY, Angle startingAngle){
        this.startAngle = startingAngle;

        //TODO:Understand Math
        this.xEncoderValue = startX * Math.cos(startAngle.getAngleInRadians())
                - startY * Math.sin(startAngle.getAngleInRadians());
        this.yEncoderValue = startX * Math.sin(startAngle.getAngleInRadians())
                + startY * Math.cos(startAngle.getAngleInRadians());
    }
    public void updateSensors(){
        yEncoderValue = yDirectionEncoder.getCurrentPosition();
        xEncoderValue = xDirectionEncoder.getCurrentPosition();
        angle = fromRadians(-((imu.getAngularOrientation().firstAngle)
                + startAngle.getAngleInRadians() - resetAngle.getAngleInRadians()));
    }

    public void findFieldPosition() {
        double internalPreviousY = fieldYPosition;
        double internalPreviousX = fieldXPosition;
        synchronized (this) {
            fieldYPosition = tickToDistance(yEncoderValue);
            fieldXPosition = tickToDistance(xEncoderValue);
        }
        // TODO: I have no idea what this is so I'll ask Gorg and Rahul
//        double deltaY = internalCurrentY - internalPreviousY;
//        double deltaX = internalCurrentX - internalPreviousX;
//        fieldXPosition += deltaX * Math.cos(angle.getAngleInRadians())
//                - deltaY * Math.sin(angle.getAngleInRadians());
//        fieldYPosition += deltaX * Math.sin(angle.getAngleInRadians())
//                + deltaY * Math.cos(angle.getAngleInRadians());
    }

    public double getFieldY(){
        updateSensors();
        findFieldPosition();
        return fieldYPosition;
    }

    public double getFieldX(){
        updateSensors();
        findFieldPosition();
        return fieldXPosition;
    }

    /**
     * DO NOT USE THIS, USE getTrimmedAngleInRadians
     * @return the angle of the bot in radians. Is based on the orientation of when the bot was started.
     */
    public double getAngleInRadians(){
        updateSensors();
        return angle.getAngleInRadians();
    }

    /**
     * DO NOT USE THIS, USE getTrimmedAngleInDegrees
     * @return the angle of the bot in Degrees. Is based on the orientation of when the bot was started.
     */
    public double getAngleInDegrees(){
        updateSensors();
        return angle.getAngleInDegrees();
    }

    /**
     *
     * @return the angle of the bot in Radians. Is based on the orientation of when the bot was started and returns an angle in a trimmed circle.
     */
    public double getTrimmedAngleInRadians(){
        updateSensors();
        return angle.getTrimmedAngleInRadians();
    }

    /**
     *
     * @return the angle of the bot in Degrees. Is based on the orientation of when the bot was started and returns an angle in a trimmed circle.
     */
    public double getTrimmedAngleInDegrees(){
        updateSensors();
        return angle.getTrimmedAngleInDegrees();
    }

    //Resets the orientation of the robot so the current direction is forward
    public void setResetAngle(){
        updateSensors();
        resetAngle = angle;
    }

    /**
     *Stops Active Location from running.
     * */
    public void stopActiveLocation() {
        isRunning = false;
    }

    @Override
    public void run(){
        while (isRunning){
            updateSensors();
            findFieldPosition();
        }
    }
}