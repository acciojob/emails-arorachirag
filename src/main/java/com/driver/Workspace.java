package com.driver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar = new ArrayList<>(); // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        this.calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am

        int c = 1;
        Collections.sort(calendar, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                if(o1.getStartTime().compareTo(o2.getStartTime()) > 0){
                    return 1;
                } else {
                    return -1;
                }
            }
        });

//        for(Meeting m : calendar){
//            System.out.println(m.getStartTime() +" "+m.getEndTime());
//        }

        for(int i=0;i<calendar.size()-1;i++){
            if(calendar.get(i).getEndTime().compareTo(calendar.get(i+1).getStartTime()) == 1){
                c++;
            }
        }
        return c;

    }
}
