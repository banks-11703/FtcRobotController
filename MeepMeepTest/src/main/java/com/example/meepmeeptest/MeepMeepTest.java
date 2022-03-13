package com.example.meepmeeptest;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTest {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        // Declare our first bot
        RoadRunnerBotEntity myFirstBot = new DefaultBotBuilder(meepMeep)
                // We set this bot to be blue
                .setColorScheme(new ColorSchemeBlueDark())

                .setConstraints(64.31821099615379, 30, Math.toRadians(180), Math.toRadians(180), 15.21)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder( new Pose2d(-33.5,63,Math.toRadians(-90)))
                                .forward(10)
                                .lineToLinearHeading(new Pose2d(-60, 60,Math.toRadians(-225)))
                                .build()
                );

        // Declare out second bot
        RoadRunnerBotEntity mySecondBot = new DefaultBotBuilder(meepMeep)
                // We set this bot to be red
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(64.31821099615379, 30, Math.toRadians(180), Math.toRadians(180), 15.21)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(7.5,-65,Math.toRadians(90)))
                                .splineToConstantHeading(new Vector2d(-12, -43), Math.toRadians(90))
                                .back(1)
                                .splineToSplineHeading(new Pose2d(-65, -43,Math.toRadians(0)),Math.toRadians(135))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)

                // Add both of our declared bot entities
                .addEntity(myFirstBot)
                .addEntity(mySecondBot)
                .start();
    }
    public static double rpmToVelocity(double rpm) {
        return rpm * 1 * 2 * Math.PI * (3.78/2) / 60.0;
    }
}