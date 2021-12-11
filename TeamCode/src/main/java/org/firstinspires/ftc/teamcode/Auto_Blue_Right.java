package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Auto_Blue_Right", group = "Linear Opmode")
@Disabled
public class Auto_Blue_Right extends DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        ResetWheelEncoders();
        waitForStart();
        barcodeReader();
        verticalDrive(-5,0.5);
        moveArm(Raised);
        horizontalDrive(-23,0.3);
        moveArm(Scoring);
        verticalDrive(-12,0.3);
        if (barcode == 0){ ScoreLow();}
        if (barcode == 1){ ScoreMid();}
        if (barcode == 2){ ScoreTop();}
        sleep(2000);
        HoldMid();
        HighHold();
        horizontalDrive(-51, 0.3);
        verticalDrive(-10,0.3);
        moveArm(Intake);
    }
}   