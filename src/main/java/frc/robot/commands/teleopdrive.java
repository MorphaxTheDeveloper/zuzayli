// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Ddrive;

public class teleopdrive extends CommandBase {
  /** Creates a new teleopdrive. */
DoubleSupplier xaxis;
DoubleSupplier yaxis;
Ddrive m_Ddrive;


  public teleopdrive(Ddrive m_Ddrive,DoubleSupplier xaxis,DoubleSupplier yaxis) {
    // Use addRequirements() here to declare subsystem dependencies.
this.xaxis = xaxis;
this.yaxis = yaxis;
this.m_Ddrive = m_Ddrive;
addRequirements(m_Ddrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
m_Ddrive.arcadeDrive(xaxis.getAsDouble(), yaxis.getAsDouble());
SmartDashboard.putNumber("get Left Encoder Distance", m_Ddrive.getLeftEncoderDistance());  
SmartDashboard.putNumber("get Right Encoder Distance", m_Ddrive.getRightEncoderDistance());  
SmartDashboard.putNumber("get Straight Encoder Distance", m_Ddrive.getStraightDriveDistance());
  }

public void resetencodercommand(){
m_Ddrive.ResetEncoder();
}


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
