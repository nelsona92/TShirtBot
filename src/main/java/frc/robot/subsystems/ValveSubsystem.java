// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class ValveSubsystem extends CommandBase {
  /** Creates a new ValveSubsystem. */
  public ValveSubsystem() {
    // Use addRequirements() here to declare subsystem dependencies.
 /*     // on/off switch for the valve
  public void valveOpenClose(boolean state){
    if (!state) {
      pmcSolenoid.set(power);}
    else m_intakeSpark.set(0.0);
    Constants.currentValveState = !state;
    }
  */


}
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
