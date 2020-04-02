package com.orange.apibss.tools;

import static org.assertj.core.api.Assertions.assertThat;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;

import com.orange.apibss.cucumber.tools.Tools;

/**
 * Junit for tools test
 * @author Thibault Duperron
 */
public class ToolsTest {

    @Test
    public void dateNow(){
        DateTime now = DateTime.now();
        for(int i = 0; i < 1337; i++) {
            LocalDate date = Tools.randomDate(0, 0);
            assertThat(date).isNotNull();
            assertThat(date.getYear()).isEqualTo((new LocalDate().getYear()));
            assertThat(date).isLessThanOrEqualTo(now.toLocalDate());
        }
    }

    @Test
    public void datePast(){
        DateTime now = DateTime.now();
        int year = now.getYear();
        int minYear = year - 42;
        int maxYear = year - 5;
        for(int i = 0; i < 1337; i++) {
            LocalDate date = Tools.randomDate(5, 42);
            assertThat(date).isNotNull();
            assertThat(date).isGreaterThan(new LocalDate(minYear + "-01-01"));
            assertThat(date).isLessThanOrEqualTo(new LocalDate(maxYear+1 + "-01-01"));
        }
    }
    @Test
    public void dateFutur(){
        DateTime now = DateTime.now();
        int year = now.getYear();
        int maxYear = year + 42;
        for(int i = 0; i < 1337; i++) {
            LocalDate date = Tools.randomDate(42);
            assertThat(date).isNotNull();
            assertThat(date).isGreaterThan(now.toLocalDate());
            assertThat(date).isLessThan(new LocalDate(maxYear+1 + "-01-01"));
        }
    }
    @Test
    public void randomString(){
        for(int i = 0; i < 1337; i++) {
            String string = Tools.randomString(i);
            assertThat(string).isNotNull();
            assertThat(string.length()).isLessThanOrEqualTo(i);
        }
    }
}
