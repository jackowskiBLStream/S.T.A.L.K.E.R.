package com.blstream.stalker;

import android.app.Application;
import android.support.test.rule.ActivityTestRule;
import android.test.ApplicationTestCase;

import com.blstream.stalker.controller.DatabaseController;

import org.junit.Before;
import org.junit.Rule;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    DatabaseController dbController;
    @Rule
    public ActivityTestRule<BaseActivity> mActivityRule = new ActivityTestRule<>(
            BaseActivity.class);

    public ApplicationTest() {
        super(Application.class);
    }

    @Before
    public void before(){
        dbController = new DatabaseController(mContext);
        dbController.clearDB();
    }

}