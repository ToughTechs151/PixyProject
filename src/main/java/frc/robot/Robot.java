/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.vision.Pixy2Camera;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static LEDSubsystem ledSubsystem;
  public static OI oi;

  public static Pixy2Camera pixyCam;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    // Print the Splash Screen
    System.out.println("==============================================");
    System.out.println("Starting robotInit for Tough Techs");
    printStatusFiles("deployhost.txt", true, 0, 2, 1);
    printStatusFiles("deploytime.txt", true, 0, 3, 2);
    printStatusFiles("buildtime.txt", false, 0, 0, 2);
    printStatusFiles("branch.txt", false, 0, 5, 1);
    printStatusFiles("commit.txt", false, 1, 0, 10);
    printStatusFiles("changes.txt", false, 2, 0, 10);
    printStatusFiles("remote.txt", false, 3, 0, 10);
    System.out.println("============================================");

    //initialize all the things
    ledSubsystem = new LEDSubsystem();
    oi = new OI();
    pixyCam = new Pixy2Camera(I2C.Port.kOnboard, 0x54); //0x54 is the address, idk why, it just be like that
  }

    private void printStatusFiles(String filename, Boolean isDeploy, int rowIndex, int colIndex, int widthIndex) {
       byte[] buffer = new byte[1024];
       InputStream statusfile;
       try {
         if (isDeploy) {
            if (RobotBase.isSimulation()) {
              statusfile = new BufferedInputStream(
                  new FileInputStream(Filesystem.getLaunchDirectory() + "/src/main/deploy/" + filename));
            } else {
              statusfile = new BufferedInputStream(new FileInputStream(Filesystem.getDeployDirectory() + "/" + filename));
            }
          } else {
            statusfile = getClass().getResourceAsStream("/" + filename);
          }
          System.out.print((filename + ": ").replace(".txt", ""));
          try {
            for (int length = 0; (length = statusfile.read(buffer)) != -1;) {
              String buf = new String(buffer).replaceAll("\\s"," ");
              String tfn = filename.replace(".txt", "");
              String fn = tfn.substring(0,1).toUpperCase() + tfn.substring(1);
              System.out.write(buffer, 0, length);
              SmartDashboard.putString(fn, buf);
              Shuffleboard.getTab("Status").add(fn, buf).withPosition(colIndex, rowIndex).withSize(widthIndex, 1);
            }
          } finally {
            System.out.println();
            statusfile.close();
          }
        } catch (Exception e) {
          System.out.println("Unable to find file.");
          System.out.println(e.getMessage());
        }
      }
  
      /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
