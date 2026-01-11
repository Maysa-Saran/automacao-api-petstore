package com.msaran;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/CT001.feature")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty, html:target/cucumber-report/cucumber.html")
@ConfigurationParameter(key = "cucumber.glue", value = "com.msaran.steps")
public class RunnerTest {

}