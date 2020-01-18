/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Just controls the bright green Pixy LED through a Relay
 */
public class LEDSubsystem extends Subsystem {

  private Relay led;

  public LEDSubsystem() {
    led = new Relay(0);
  }
  
  public void turnOnRelay() {
    led.set(Relay.Value.kForward);
  }

  @Override
  protected void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
