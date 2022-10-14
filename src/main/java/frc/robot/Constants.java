// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {


    public static int kValveButton = XboxController.Button.kA.value;
    public static boolean currentValveState = false; //true open, false closed

    public final class OIConstants{
        public static final int kDriverControllerPort = 0;
    }
  
    public final class DriveConstants{
        public static final int kLeftMotor01CanBusID = 11;
        public static final int kRightMotor02CanBusID = 12;
        public static final double kRampRate = 3;
        public static final double kMaxSpeed = 0.3;
    }

}
