package com.driver;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.lang3.tuple.Pair;

public class Workspace extends Gmail {
    private ArrayList<Meeting> calendar = new ArrayList();

    public Workspace(String emailId) {
        super(emailId, Integer.MAX_VALUE);
    }

    public void addMeeting(Meeting meeting) {
        this.calendar.add(meeting);
    }

    public int findMaxMeetings() {
        ArrayList<Pair<LocalTime, Integer>> endTimes = new ArrayList();

        for(int i = 0; i < this.calendar.size(); ++i) {
            endTimes.add(Pair.of(((Meeting)this.calendar.get(i)).getEndTime(), i));
        }

        Collections.sort(endTimes);
        LocalTime time_limit = (LocalTime)((Pair)endTimes.get(0)).getLeft();
        int cnt = 0;
        if (!endTimes.isEmpty()) {
            ++cnt;
        }

        for(int i = 1; i < endTimes.size(); ++i) {
            if (((Meeting)this.calendar.get((Integer)((Pair)endTimes.get(i)).getRight())).getStartTime().compareTo(time_limit) > 0) {
                ++cnt;
                time_limit = (LocalTime)((Pair)endTimes.get(i)).getLeft();
            }
        }

        return cnt;
    }
}