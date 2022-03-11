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
        MeepMeep meepMeep = new MeepMeep(800);

        // Declare our first bot
        RoadRunnerBotEntity myFirstBot = new DefaultBotBuilder(meepMeep)
                // We set this bot to be blue
                .setColorScheme(new ColorSchemeBlueDark())

                .setConstraints(64.31821099615379, 30, Math.toRadians(180), Math.toRadians(180), 15.21)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(7,63,Math.toRadians(-90)))
                                .splineToConstantHeading(new Vector2d(-12, 40.5), Math.toRadians(-90))
                                .lineToLinearHeading(new Pose2d(12, 66.5,Math.toRadians(0)))
                                .lineTo(new Vector2d(38,66.5))
                                .lineTo(new Vector2d(38,38))
                                .lineToLinearHeading(new Pose2d(55,42, Math.toRadians(-90)))
                                .build()
                );

        // Declare out second bot
        RoadRunnerBotEntity mySecondBot = new DefaultBotBuilder(meepMeep)
                // We set this bot to be red
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(64.31821099615379, 30, Math.toRadians(180), Math.toRadians(180), 15.21)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(7,-63,Math.toRadians(90)))
                        .splineToConstantHeading(new Vector2d(-12, -40.5), Math.toRadians(90))
                        .lineToLinearHeading(new Pose2d(12, -66.5,Math.toRadians(0)))
                        .lineTo(new Vector2d(38,-66.5))
                        .lineTo(new Vector2d(38,-38))
                        .lineToLinearHeading(new Pose2d(55,-42, Math.toRadians(90)))
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