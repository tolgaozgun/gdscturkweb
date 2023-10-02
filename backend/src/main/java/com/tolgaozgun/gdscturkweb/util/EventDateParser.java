package com.tolgaozgun.gdscturkweb.util;

import com.tolgaozgun.gdscturkweb.model.EventDate;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventDateParser {

    /* Type 1
     *
     * Day, MMM, DD, HH:MM AM/PM (GMT+/-HH)
     *
     * Type 2
     *
     * Day, MMM, DD, YYYY, HH:MM AM/PM (GMT+/-HH)
     *
     * Type 3
     *
     * MMM DD - DD, HH:MM AM/PM (GMT+/-HH)
     *
     * Type 4
     *
     * MMM DD - DD, YYYY, HH:MM AM/PM (GMT+/-HH)
     *
     */




    public static Optional<EventDate> parse(String dateString) {
        EventDate eventDate = null;

        eventDate = parseTypeOne(dateString);
        if (eventDate != null) {
            return Optional.of(eventDate);
        }
        eventDate = parseTypeTwo(dateString);
        if (eventDate != null) {
            return Optional.of(eventDate);
        }
        eventDate = parseTypeThree(dateString);
        if (eventDate != null) {
            return Optional.of(eventDate);
        }
        eventDate = parseTypeFour(dateString);
        if (eventDate != null) {
            return Optional.of(eventDate);
        }

        return Optional.empty();
    }


    private static EventDate parseTypeThree(String dateString) {
        String regex = "^(\\w{3}) (\\d{1,2}) - (\\d{1,2}), (\\d{1,2}):(\\d{2}) ([APM]+) \\((GMT[+\\-]\\d+)\\)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateString);

        if (matcher.find()) {
            String month = matcher.group(1);
            String startDayString = matcher.group(2);
            String endDayString = matcher.group(3);
            String hourString = matcher.group(4);
            String minuteString = matcher.group(5);
            String amPm = matcher.group(6);
            String gmtPart = matcher.group(7);


            int startDay = Integer.parseInt(startDayString);
            int endDay = Integer.parseInt(endDayString);
            int monthIndex = getMonthIndex(month);
            int hour = Integer.parseInt(hourString);
            int minute = Integer.parseInt(minuteString);
            int year = Calendar.getInstance().get(Calendar.YEAR);

            if (amPm.equalsIgnoreCase("PM")) {
                hour = hour + 12;
            }
            TimeZone timeZone = TimeZone.getTimeZone(gmtPart);
            Calendar calendar = Calendar.getInstance(timeZone);

            // Set the date and time components
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthIndex);
            calendar.set(Calendar.DAY_OF_MONTH, startDay);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0); // You can set seconds and milliseconds as well

            // Get a Date object from the Calendar
            Date startDate = calendar.getTime();
            calendar.set(Calendar.DAY_OF_MONTH, endDay);
            Date endDate = calendar.getTime();

            EventDate eventDate = new EventDate();
            eventDate.setStartDate(startDate);
            eventDate.setEndDate(endDate);
            return eventDate;
        }
        return null;
    }

    private static EventDate parseTypeFour(String dateString) {
        String regex = "^(\\w{3}) (\\d{1,2}) - (\\d{1,2}), (\\d{4}), (\\d{1,2}):(\\d{2}) ([APM]+) \\((GMT[+\\-]\\d+)\\)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateString);

        if (matcher.find()) {
            String month = matcher.group(1);
            String startDayString = matcher.group(2);
            String endDayString = matcher.group(3);
            String yearString = matcher.group(4);
            String hourString = matcher.group(5);
            String minuteString = matcher.group(6);
            String amPm = matcher.group(7);
            String gmtPart = matcher.group(8);

            int startDay = Integer.parseInt(startDayString);
            int endDay = Integer.parseInt(endDayString);
            int monthIndex = getMonthIndex(month);
            int hour = Integer.parseInt(hourString);
            int minute = Integer.parseInt(minuteString);
            int year = Integer.parseInt(yearString);



            if (amPm.equalsIgnoreCase("PM")) {
                hour = hour + 12;
            }

            // Create two Date objects one for start date and one for end date
            // Use the year, month, and day to create the Date objects
            // Use the time to set the time of the Date objects
            // Use the gmtPart to set the timezone of the Date objects
            TimeZone timeZone = TimeZone.getTimeZone(gmtPart);
            Calendar calendar = Calendar.getInstance(timeZone);

            // Set the date and time components
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthIndex);
            calendar.set(Calendar.DAY_OF_MONTH, startDay);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0); // You can set seconds and milliseconds as well

            // Get a Date object from the Calendar
            Date startDate = calendar.getTime();
            calendar.set(Calendar.DAY_OF_MONTH, endDay);
            Date endDate = calendar.getTime();
            EventDate eventDate = new EventDate();
            eventDate.setStartDate(startDate);
            eventDate.setEndDate(endDate);
            return eventDate;
        }
        return null;
    }

    private static EventDate parseTypeTwo(String dateString) {
        String regex = "^(\\w{3}), (\\w{3}) (\\d{1,2}), (\\d{4}), (\\d{1,2}):(\\d{2}) ([APM]+) \\((GMT[+\\-]\\d+)\\)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateString);

        if (matcher.find()) {
            String monthPart = matcher.group(2);
            String dayPart = matcher.group(3);
            String yearPart = matcher.group(4);
            String hourPart = matcher.group(5);
            String minutePart = matcher.group(6);
            String amPm = matcher.group(7);
            String gmtPart = matcher.group(8);
            int dayIndex = Integer.parseInt(dayPart);
            int monthIndex = getMonthIndex(monthPart);
            int year = Integer.parseInt(yearPart);
            int hour = Integer.parseInt(hourPart);
            int minute = Integer.parseInt(minutePart);

            if (amPm.equalsIgnoreCase("PM")) {
                hour = hour + 12;
            }

            TimeZone timeZone = TimeZone.getTimeZone(gmtPart);
            Calendar calendar = Calendar.getInstance(timeZone);

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthIndex);
            calendar.set(Calendar.DAY_OF_MONTH, dayIndex);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0); // You can set seconds and milliseconds as well

            // Get a Date object from the Calendar
            Date date = calendar.getTime();

            EventDate eventDate = new EventDate();
            eventDate.setStartDate(date);
            return eventDate;
        }
        return null;
    }
    private static EventDate parseTypeOne(String dateString) {
        String regex = "^(\\w{3}),? (\\w{3}|\\w{3}) (\\d{1,2}),? (\\d{1,2}):(\\d{2}) ([APM]+) \\((GMT[+\\-]\\d+)\\)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateString);

        if (matcher.find()) {
            String monthPart = matcher.group(2);
            String day = matcher.group(3);
            String hourPart = matcher.group(4);
            String minutePart = matcher.group(5);
            String amPm = matcher.group(6);
            String gmtPart = matcher.group(7);
            int dayIndex = Integer.parseInt(day);
            int monthIndex = getMonthIndex(monthPart);

            int year = Calendar.getInstance().get(Calendar.YEAR);
            int hour = Integer.parseInt(hourPart);
            int minute = Integer.parseInt(minutePart);

            if (amPm.equalsIgnoreCase("PM")) {
                hour = hour + 12;
            }

            TimeZone timeZone = TimeZone.getTimeZone(gmtPart);
            Calendar calendar = Calendar.getInstance(timeZone);

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthIndex);
            calendar.set(Calendar.DAY_OF_MONTH, dayIndex);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0); // You can set seconds and milliseconds as well

            // Get a Date object from the Calendar
            Date date = calendar.getTime();
            EventDate eventDate = new EventDate();
            eventDate.setStartDate(date);
            return eventDate;
        }
        return null;
    }


    private static int getMonthIndex(String month) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (int i = 0; i < months.length; i++) {
            if (months[i].equalsIgnoreCase(month)) {
                return i;
            }
        }
        return -1; // Not found
    }
}
