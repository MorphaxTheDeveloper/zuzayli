// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Ddrive extends SubsystemBase {
  /** Creates a new Ddrive. */  

private Encoder leftEncoder =
new Encoder(
    1,
    2,
    false,
    EncodingType.k4X);


    private Encoder rightEncoder =
    new Encoder(
        3,
        4,
        false,
        EncodingType.k4X);
        
  CANSparkMax rightfrontmotor = new CANSparkMax(Constants.kDrive.krightfront,MotorType.kBrushed);
  CANSparkMax rightbackmotor = new CANSparkMax(Constants.kDrive.krightback,MotorType.kBrushed);
  CANSparkMax leftfrontmotor = new CANSparkMax(Constants.kDrive.kleftfront,MotorType.kBrushed);
  CANSparkMax leftbackmotor = new CANSparkMax(Constants.kDrive.kleftback,MotorType.kBrushed);
  
  MotorControllerGroup rightcontoller = new MotorControllerGroup(rightfrontmotor, rightbackmotor);
  MotorControllerGroup leftcontoller = new MotorControllerGroup(leftfrontmotor, leftbackmotor);
  
  DifferentialDrive diffdrive = new DifferentialDrive(rightcontoller,leftcontoller);

  public Ddrive() {

    rightcontoller.setInverted(Constants.kDrive.krightinvert);
    leftcontoller.setInverted(Constants.kDrive.kleftinvert);
    }

    public void arcadeDrive(double xspeed,double yrotation){
    diffdrive.arcadeDrive(xspeed, yrotation);
    }


    public double getLeftEncoderDistance() {
      leftEncoder.setDistancePerPulse(1.0 / 20.0 * Math.PI * 6 * (1 / 10.71));
      return leftEncoder.getDistance() * 2.54;
    }
  
    public double getRightEncoderDistance() {
      rightEncoder.setDistancePerPulse(1.0 / 20.0 * Math.PI * 6 * (1 / 10.71));
      return rightEncoder.getDistance() * 2.54 * -1;
    }
  
    public double getStraightDriveDistance() {
      return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2;
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
