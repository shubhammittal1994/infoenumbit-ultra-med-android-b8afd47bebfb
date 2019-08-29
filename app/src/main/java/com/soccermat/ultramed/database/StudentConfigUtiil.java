package com.soccermat.ultramed.database;

import com.soccermat.ultramed.models.CalendarModel;
import com.soccermat.ultramed.models.ExerciseNameModel;
import com.soccermat.ultramed.models.SubExerciseDoneModel;
import com.soccermat.ultramed.models.SubExerciseNameModel;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by info1010 on 10/9/2017.
 */

public class StudentConfigUtiil extends OrmLiteConfigUtil {
    public static final Class<?>[] classes = new Class[]{ExerciseNameModel.class,SubExerciseNameModel.class
            ,SubExerciseDoneModel.class,CalendarModel.class};
  //  public static final Class<?>[] classes_sub_exercise = new Class[]{SubExerciseNameModel.class};

    public static void main(String[] args) throws IOException, SQLException {

        String currDirectory = "user.dir";
        String configPath = "/app/src/main/res/raw/ormlite_config.txt";

        String projectRoot = System.getProperty(currDirectory);

        String fullConfigPath = projectRoot + configPath;

        File file = new File(fullConfigPath);

        if (file.exists()) {
            file.delete();
            file = new File(fullConfigPath);
        }
        writeConfigFile(file, classes);

    }
}
