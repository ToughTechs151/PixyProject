/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.GetPixyVersionCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //I didn't extend OI because I was just using one button

  Joystick stick;

  public OI() {
    stick = new Joystick(0);
    JoystickButton xButton = new JoystickButton(stick, 3);
    xButton.whenPressed(new GetPixyVersionCommand());
  }
  
  
  public Joystick getJoystick() {
    return stick;
  }
}
