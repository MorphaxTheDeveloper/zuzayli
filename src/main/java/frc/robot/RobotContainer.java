// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.autonoumuscommand;
import frc.robot.commands.pull;
import frc.robot.commands.push;
import frc.robot.commands.resetencoder;
import frc.robot.commands.teleopdrive;
import frc.robot.subsystems.Ddrive;
import frc.robot.subsystems.firstsolenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  firstsolenoid m_firstsolenoid = new firstsolenoid();
  Ddrive m_Ddrive = new Ddrive();
  Joystick test = new Joystick(1);
JoystickButton buton1 = new JoystickButton(test, 1);
JoystickButton buton2 = new JoystickButton(test, 2);
  JoystickButton buton3 = new JoystickButton(test, 3);
/** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings



m_Ddrive.setDefaultCommand(new teleopdrive(m_Ddrive,
() -> test.getX(), 
() -> test.getY()
));

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

buton1.whenPressed(new pull(m_firstsolenoid));
buton2.whenPressed(new push(m_firstsolenoid));
buton3.whenPressed(new resetencoder(m_Ddrive));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new SequentialCommandGroup(


      new autonoumuscommand(m_Ddrive, "straight", 0.6, 30),
      new autonoumuscommand(m_Ddrive, "right", 0.6, 30),  
      new autonoumuscommand(m_Ddrive, "left", 0.6, 30)    



    );
  }
}
