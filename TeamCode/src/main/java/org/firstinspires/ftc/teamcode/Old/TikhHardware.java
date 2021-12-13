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

package org.firstinspires.ftc.teamcode.Old;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class TikhHardware
{
    public ElapsedTime runtime = new ElapsedTime();
    public DcMotor FrontLeftDrive = null;
    public DcMotor BackLeftDrive = null;
    public DcMotor FrontRightDrive = null;
    public DcMotor BackRightDrive = null;
    public DcMotor PivotMotor;
    public Servo HighGoal;
    public Servo LowGoal;
    public Servo Grabber;
    public DcMotor SpinnerMotor;
    public CRServo Intake_Servo;
    public DigitalChannel redLED;
    public DigitalChannel greenLED;
    HardwareMap hwMap           =  null;
    public ElapsedTime period  = new ElapsedTime();

    public TikhHardware(){

    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        // Define and Initialize Motors
        FrontLeftDrive = hwMap.get(DcMotor.class, "fl");
        FrontRightDrive = hwMap.get(DcMotor.class, "fr");
        BackLeftDrive = hwMap.get(DcMotor.class, "bl");
        BackRightDrive = hwMap.get(DcMotor.class, "br");
        PivotMotor = hwMap.get(DcMotor.class, "pm");
        HighGoal = hwMap.get(Servo.class, "hg");
        LowGoal = hwMap.get(Servo.class, "lg");
        Grabber = hwMap.get(Servo.class, "grabber");
        SpinnerMotor = hwMap.get(DcMotor.class, "sp");
        Intake_Servo = hwMap.get(CRServo.class,"is");
        redLED = hwMap.get(DigitalChannel.class,"red");
        greenLED = hwMap.get(DigitalChannel.class,"green");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        FrontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        FrontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        BackLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        BackRightDrive.setDirection(DcMotor.Direction.REVERSE);
        PivotMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
 }

