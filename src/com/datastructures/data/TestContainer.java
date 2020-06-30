package com.datastructures.data;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class TestContainer implements DataStructureOutput {


    @Override
    public void doAction(JTextArea output)
    {
        String scheduleStr1 =
                        "Mon 00:00-23:30\n"+
                        "Tue 00:30-23:00\n"+
                        "Wed 01:00-22:00\n"+
                        "Thu 00:30-21:30\n"+
                        "Fri 00:20-22:00\n"+
                        "Sat 00:00-20:00\n"+
                        "Sun 00:00-23:59\n";

        String scheduleStr2 =
                "Mon 00:00-23:30\n"+
                        "Tue 00:30-23:00\n"+
                        "Wed 01:00-22:00\n"+
                        "Thu 00:30-01:30\n"+
                        "Fri 00:20-22:00\n"+
                        "Sat 00:00-20:00\n"+
                        "Sun 00:00-23:59\n";

        //output.setText("Longest :" + solution("Sun 01:30-05:30\nMon 03:45-20:00"));

        byte [] meetingSlotsForWeek =  new byte[MAX_TIMES_PER_DAY * MEETING_DAYS];
        List<Meeting> meetings;
        //initially all slots are free
        for(int idx=0; idx <= meetingSlotsForWeek.length-1; idx++ )
        {
            meetingSlotsForWeek[idx] = FREE;
        }

        meetings = getMeetings(scheduleStr1);

        output.setText("");
        for (Meeting meeting: meetings)
        {
                output.append(meeting.toString()+ "\n");
        }

        //schedule meetings
        for(Meeting aMeeting: meetings)
        {
            scheduleAMeeting(meetingSlotsForWeek,aMeeting);
        }

        output.append("\nThe longest free block is " + findLongestFreeBlock(meetingSlotsForWeek ) + " minutes long.\n");

        int daysToShow =7;
        int hoursPerDay =24;
        int minsPerHour = 60;
        int currentDay = 0;
        StringBuilder scheStr = new StringBuilder("\n-----------------------------");
        for (int idx = 0; idx <= ( minsPerHour * hoursPerDay * daysToShow ) - 1 ; idx++) {

            if(idx % 60 == 0) scheStr.append("\n");
            if(idx % (60 * 24) == 0) scheStr.append("-------------" + currentDay++ +"-----------------\n");

            if(meetingSlotsForWeek[idx] == FREE)
                scheStr.append("O");
            else
                scheStr.append("U");
        }
        output.append(scheStr.toString());

    }

    enum WeekDay{
        Mon,Tues,Wed,Thu,Fri,Sat,Sun;
    }
    final int MAX_TIMES_PER_DAY = 1440; //1 minute meetings
    final int MEETING_DAYS = 7;
    final byte FREE = 0;
    final byte USED = 1;


    class Meeting {
        private String weekDay;
        private String start;
        private String end;

        public Meeting(String weekDay, String start, String end) {
            this.weekDay = weekDay;
            this.start = start;
            this.end = end;
        }

        public int getDurationMins()
        {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            long durationMins =0;
            long durationMillis =0;
            try {
                Date startDate = format.parse(this.start);
                Date endDate = format.parse(this.end);
                durationMillis = endDate.getTime() - startDate.getTime();
                durationMins = durationMillis / (60 * 1000);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            return (int)durationMins;
        }

        public WeekDay getWeekDay()
        {
            if (this.weekDay.equals("Mon")) {
                return WeekDay.Mon;
            }
            if (this.weekDay.equals("Tue")) {
                return WeekDay.Tues;
            }
            if (this.weekDay.equals("Wed")) {
                return WeekDay.Wed;
            }
            if (this.weekDay.equals("Thu")) {
                return WeekDay.Thu;
            }
            if (this.weekDay.equals("Fri")){
                return WeekDay.Fri;
            }
            if (this.weekDay.equals("Sat")){
                return WeekDay.Sat;
            }
            if (this.weekDay.equals("Sun")) {
                return WeekDay.Sun;
            }

            return WeekDay.Sun;
        }

        public String getStart()
        {
            return start;
        }

        public String toString()
        {
            return "Meeting on " + this.weekDay + " from " + this.start + " to " + this.end;
        }
    }


    public int solution(String s)
    {

        byte [] meetingSlotsForWeek =  new byte[MAX_TIMES_PER_DAY * MEETING_DAYS];
        List<Meeting> meetings;
        //initially all slots are free
        for(int idx=0; idx <= meetingSlotsForWeek.length-1; idx++ )
        {
            meetingSlotsForWeek[idx] = FREE;
        }

        meetings = getMeetings(s);

        //schedule meetings
        for(Meeting aMeeting: meetings)
        {
            scheduleAMeeting(meetingSlotsForWeek,aMeeting);
        }

        return 1;
    }

    List<Meeting> getMeetings (String scheduleStr)
    {
        List<Meeting> meetings = new ArrayList<>();
        StringTokenizer strTok = new StringTokenizer(scheduleStr, "\n");
        String fullDetails;
        String day;
        String time;
        String start;
        String end;
        while(strTok.hasMoreTokens())
        {
            fullDetails = strTok.nextToken();
            day = fullDetails.substring(0,3);
            time = fullDetails.substring(3);
            start = time.substring(1,6);
            end = time.substring(7,12);
            System.out.println(fullDetails + "Day :" + day + " Duration:" + time + " start: " + start + " stop : " + end );
            meetings.add(new Meeting(day,start,end));
        }

        return meetings;
    }

    void scheduleAMeeting (byte [] allSlots, Meeting aMeeting )
    {
        int offSet = aMeeting.getWeekDay().ordinal() * MAX_TIMES_PER_DAY;

        //use time slots
        int hour, minsAfterHour;

        System.out.println(" Scheduling a meeting " + aMeeting.toString() + " [" + aMeeting.getWeekDay() + "]");
        try {
            hour = Integer.parseInt(aMeeting.start.substring(0, 2));
            minsAfterHour = Integer.parseInt(aMeeting.start.substring(3, 5));
            System.out.println("Starting a hour: " + hour + " min:  " + minsAfterHour );
            int startIdx = offSet + (hour * 60) + minsAfterHour;
            System.out.println("StartIdx: " + startIdx + " and continues for " + aMeeting.getDurationMins() + " mins" );
            for (int idx = startIdx; idx <= startIdx + aMeeting.getDurationMins(); idx++) {
                allSlots[idx] = USED;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    int findLongestFreeBlock (byte[] meetingSlotsForWeek)
    {
        int longestFreeBlockCount = 0;
        int freeBlockCount =0;
        for (int idx = 0; idx <= meetingSlotsForWeek.length-1 ; idx++)
        {
                if( meetingSlotsForWeek[idx] == FREE )
                {
                    freeBlockCount++;
                }
                else
                {
                    //line break - a used block is found
                    // is the current free block count larger than the current largest?
                    if(freeBlockCount > longestFreeBlockCount)
                    {
                        longestFreeBlockCount = freeBlockCount;
                    }
                    freeBlockCount = 0 ; //reset count
                }
        }
      return longestFreeBlockCount;
    }

}
