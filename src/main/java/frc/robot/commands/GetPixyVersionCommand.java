package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.vision.PixyPacket;

public class GetPixyVersionCommand extends Command {
    public GetPixyVersionCommand() {
        requires(Robot.ledSubsystem);
    }

    @Override
    protected void initialize() {
        Robot.ledSubsystem.turnOnRelay();
    }

    double[] results = new double[2];
    @Override
    protected void execute() {
        //this array gets filled with all the "blocks" (things that the pixy sees of the correct color)
        //and then we can process them however we want.
        //here, i'm println-ing info from the first block just because I wanted to test that the printed
        //coordinates change when we moved the tape, but you could also process it a different way or call
        //the getCenterCoordinates() method, which does some processing for you
        PixyPacket[] arr = new PixyPacket[5];
        Robot.pixyCam.getBlocks(arr, (byte) 0x01, 0x01);

        //gotta check if it's equal to null in case we don't see anything,
        //because if we don't see anything and still try to print then we'll get a 
        //NullPointerException :(
        if(arr[0] != null) {
            System.out.println(arr[0].toString());
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }

}