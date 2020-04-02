package com.orange.apibss.cucumber;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "json:target/cucumber.json" })
public class RunCukesTest {
}