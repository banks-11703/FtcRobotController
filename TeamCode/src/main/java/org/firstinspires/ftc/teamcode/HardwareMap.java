package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class HardwareMap extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontLeftDrive = null;
    private DcMotor BackLeftDrive = null;
    private DcMotor FrontRightDrive = null;
    private DcMotor BackRightDrive = null;
    private DcMotor PivotMotor;
    private Servo HighGoal;
    private Servo LowGoal;
    int ServoMode = 0;

    public void runOpMode() {
        FrontLeftDrive = hardwareMap.get(DcMotor.class, "fl");
        FrontRightDrive = hardwareMap.get(DcMotor.class, "fr");
        BackLeftDrive = hardwareMap.get(DcMotor.class, "bl");
        BackRightDrive = hardwareMap.get(DcMotor.class, "br");
        PivotMotor = hardwareMap.get(DcMotor.class, "pm");
        HighGoal = hardwareMap.get(Servo.class, "hg");
        LowGoal = hardwareMap.get(Servo.class, "lg");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        FrontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        FrontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        BackLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        BackRightDrive.setDirection(DcMotor.Direction.REVERSE);



















    }
}