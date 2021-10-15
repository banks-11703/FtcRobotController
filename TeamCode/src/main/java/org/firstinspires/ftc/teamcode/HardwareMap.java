package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "HardwareMap", group = "Linear Opmode")
@Disabled
public class HardwareMap extends LinearOpMode {

    public ElapsedTime runtime = new ElapsedTime();
    public DcMotor FrontLeftDrive = null;
    public DcMotor BackLeftDrive = null;
    public DcMotor FrontRightDrive = null;
    public DcMotor BackRightDrive = null;
    public DcMotor PivotMotor;
    public Servo HighGoal;
    public Servo LowGoal;
    int ServoMode = 0;


    public void runOpMode() {

        FrontLeftDrive =hardwareMap.get(DcMotor .class,"fl");
        FrontRightDrive =hardwareMap.get(DcMotor .class,"fr");
        BackLeftDrive =hardwareMap.get(DcMotor .class,"bl");
        BackRightDrive =hardwareMap.get(DcMotor .class,"br");
        PivotMotor =hardwareMap.get(DcMotor .class,"pm");
        HighGoal =hardwareMap.get(Servo .class,"hg");
        LowGoal =hardwareMap.get(Servo .class,"lg");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        FrontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        FrontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        BackLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        BackRightDrive.setDirection(DcMotor.Direction.REVERSE);
    }

}














