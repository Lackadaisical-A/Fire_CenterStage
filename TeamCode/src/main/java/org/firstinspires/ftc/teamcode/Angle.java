package org.firstinspires.ftc.teamcode;

import java.util.Locale;

import static org.firstinspires.ftc.teamcode.Constants.PI;
import static org.firstinspires.ftc.teamcode.Constants.TAU;

public class Angle {
    //IN RADIANS
    private final double angle;


    //Constructor

    /**
     * Constructor for an Angle Object
     * @param angle The angle value in radians
     */
    public Angle(double angle) {
        this.angle = angle;
    }

    //Returns the value of the angle in degrees
    public double getAngleInDegrees() {
        return Math.toDegrees(angle);
    }

    //returns the value of the angle in Radians
    public double getAngleInRadians() {
        return angle;
    }


    //makes angle positive if it is negative

    /**
     * Creates an Angle that is refelcted to the positive side of the angle circle
     * @returns an angle that is on the positive side of the angle circle with the same absolute value angle
     */
    public Angle makePositive() {
        return new Angle(angle < 0 ? -angle : angle);
    }


    public static Angle fromDegrees(double angle, boolean reflectDirection) {
        return fromRadians(Math.toRadians(angle), reflectDirection);
    }

    public static Angle fromDegrees(double angle) {
        return fromDegrees(angle, false);
    }

    public static Angle fromRadians(double angle, boolean reflectDirection) {
        // If Angle < 0, We want to maintain the sign but fix the modulus, which means that we reflect
        // it again, use the modulus, and undo the reflection. Otherwise, simply apply the modulus operation.
        return new Angle((reflectDirection ? -1 : 1) * (angle < 0 ? -((-angle) % TAU) : (angle % TAU)));
    }

    public static Angle fromRadians(double angle) {
        return fromRadians(angle, false);
    }

    /**
     * The way you want to get the angle. Will return the angle and make sure it is on the 0 to +-PI
     * @return It returns the trimmed angle value.
     */
    public double getTrimmedAngleInRadians() {
        //If over 180 degrees
        if (angle > PI) {
            //angle -= 360 degrees
            return angle - TAU;
            //angle is less than -180 degrees
        } else if (angle < -PI) {
            //angle += 360 degrees
            return angle + TAU;
            //returns current angle if it -180 <= angle <= 180
        } else return angle;
    }

    /**
     * Returns the angle value in degrees, and trims it to fit the 0 to +-180 angle circle
     * @return
     */
    public double getTrimmedAngleInDegrees() {
        return Math.toDegrees(getTrimmedAngleInRadians());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Angle angle1 = (Angle) o;

        return Double.compare(angle1.angle, angle) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(angle);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%.3f °", getAngleInDegrees());
    }

    public String toStringRadians() {
        return String.format(Locale.ENGLISH, "%.3f rad", getAngleInRadians());
    }
}