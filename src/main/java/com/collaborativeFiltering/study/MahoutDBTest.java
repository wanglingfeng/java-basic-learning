package com.collaborativeFiltering.study;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;

/**
 * Created by lfwang on 2017/5/10.
 */
public class MahoutDBTest {

    public static void main(String... args) throws TasteException {
        DataModel model = new GenericDataModel(getUserData());
    }

    private static FastByIDMap<PreferenceArray> getUserData() {
        FastByIDMap<PreferenceArray> preferences = new FastByIDMap<>();

        for (User user : User.getAllUsers()) {
            PreferenceArray userPreferences = new GenericUserPreferenceArray(10);

            int i = 0;

            for (User.Video video : user.getVideos()) {
                userPreferences.setUserID(i, user.getId());
                userPreferences.setItemID(i, video.getId());
                userPreferences.setValue(i, 1);

                i++;
            }

            preferences.put(user.getId(), userPreferences);
        }

        return preferences;
    }
}
