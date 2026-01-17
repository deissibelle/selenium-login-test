package com.test.todo;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features",
        glue = "com.test.todo",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class RunCucumberTest { }
