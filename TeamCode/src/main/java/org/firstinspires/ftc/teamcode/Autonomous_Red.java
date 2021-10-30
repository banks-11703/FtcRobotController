/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name = "autonomous_Red", group = "Linear Opmode")
//@Disabled
public class Autonomous_Red extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public  DcMotor FrontLeftDrive;
    public DcMotor BackLeftDrive;
    public   DcMotor FrontRightDrive;
    public DcMotor BackRightDrive;
    private DcMotor SpinnerMotor;
    double distance;
    int FirstMove = 1000;
    int SecondMove = -500;
    int wheel_dia = 4;// inches
    double ticksperrotation = 384.5;
    double rotations = 1.875;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        FrontLeftDrive = hardwareMap.get(DcMotor.class, "fl");
        FrontRightDrive = hardwareMap.get(DcMotor.class, "fr");
        BackLeftDrive = hardwareMap.get(DcMotor.class, "bl");
        BackRightDrive = hardwareMap.get(DcMotor.class, "br");
        SpinnerMotor = hardwareMap.get(DcMotor.class, "sp");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        FrontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        FrontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        BackLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        BackRightDrive.setDirection(DcMotor.Direction.REVERSE);
        // Wait for the game to start (driver presses PLAY)
        FrontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        FrontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        runtime.reset();




        // run until the end of the match (driver presses STOP)

        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();
        telemetry.addData("Path0", "Starting at %7d :%7d", FrontLeftDrive.getCurrentPosition(), FrontRightDrive.getCurrentPosition(), BackLeftDrive.getCurrentPosition(), BackRightDrive.getCurrentPosition());
        telemetry.update();

        verticalDrive(10,0.5);
        //while (FrontLeftDrive.isBusy() && opModeIsActive());

        beforedrive();
        Turn(360,0.5);

        SpinnerMotor.setPower(0.3);
        sleep(1000);
        //todo Get this to go for certain time
        telemetry.addData("Status", "Run Time: " + runtime.toString());

        telemetry.update();
    }

        public void verticalDrive(int inches,double power){
            FrontLeftDrive.setTargetPosition(distancetoticks(inches));
            FrontRightDrive.setTargetPosition(distancetoticks(inches));
            BackLeftDrive.setTargetPosition(distancetoticks(inches));
            BackRightDrive.setTargetPosition(distancetoticks(inches));

            FrontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FrontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BackRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BackLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FrontLeftDrive.setPower(power);
            FrontRightDrive.setPower(power);
            BackLeftDrive.setPower(power);
            BackRightDrive.setPower(power);
            while (FrontLeftDrive.isBusy() && FrontRightDrive.isBusy() && BackLeftDrive.isBusy() && BackRightDrive.isBusy() && opModeIsActive());


        }

        public void lefthorizontalDrive(int inches,double power){
            FrontLeftDrive.setTargetPosition(distancetoticks(inches));
            FrontRightDrive.setTargetPosition(-distancetoticks(inches));
            BackLeftDrive.setTargetPosition(-distancetoticks(inches));
            BackRightDrive.setTargetPosition(distancetoticks(inches));

            FrontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FrontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BackRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BackLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FrontLeftDrive.setPower(power);
            FrontRightDrive.setPower(power);
            BackLeftDrive.setPower(power);
            BackRightDrive.setPower(power);
            while (FrontLeftDrive.isBusy() && FrontRightDrive.isBusy() && BackLeftDrive.isBusy() && BackRightDrive.isBusy() && opModeIsActive());

        }
    public void righthorizontalDrive(int inches,double power){
        FrontLeftDrive.setTargetPosition(-distancetoticks(inches));
        FrontRightDrive.setTargetPosition(distancetoticks(inches));
        BackLeftDrive.setTargetPosition(distancetoticks(inches));
        BackRightDrive.setTargetPosition(-distancetoticks(inches));

        FrontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLeftDrive.setPower(power);
        FrontRightDrive.setPower(power);
        BackLeftDrive.setPower(power);
        BackRightDrive.setPower(power);
        while (FrontLeftDrive.isBusy() && FrontRightDrive.isBusy() && BackLeftDrive.isBusy() && BackRightDrive.isBusy() && opModeIsActive());

    }
    public void Turn(int degrees,double power){
        FrontLeftDrive.setTargetPosition(-degreestoticks(degrees));
        FrontRightDrive.setTargetPosition(degreestoticks(degrees));
        BackLeftDrive.setTargetPosition(-degreestoticks(degrees));
        BackRightDrive.setTargetPosition(degreestoticks(degrees));

        FrontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLeftDrive.setPower(power);
        FrontRightDrive.setPower(power);
        BackLeftDrive.setPower(power);
        BackRightDrive.setPower(power);
        while (FrontLeftDrive.isBusy() && FrontRightDrive.isBusy() && BackLeftDrive.isBusy() && BackRightDrive.isBusy() && opModeIsActive());

    }



    public void beforedrive(){
        FrontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        FrontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public int distancetoticks(int distance_in) {
        double doubleticks = distance_in * ((ticksperrotation * 2)/(wheel_dia * 3.14)); // 2x is for gear
        int ticksint = (int) Math.round(doubleticks);
       return ticksint;

    }
    public int degreestoticks(int degrees) {
        double Ddoubleticks = (degrees/360 *(10000/rotations));
        int Dticksint = (int) Math.round(Ddoubleticks);
        return Dticksint;

    }
}
