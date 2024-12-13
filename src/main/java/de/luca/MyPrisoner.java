package de.luca;

import de.kyle.gefangenendilemma.api.Prisoner;
import de.kyle.gefangenendilemma.api.event.PostMessEvent;
import de.kyle.gefangenendilemma.api.result.PrisonerMessResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyPrisoner implements Prisoner {
    List<PastEvent> history;
    String currentOpp;

    public MyPrisoner() {
        this.history = new ArrayList<>();
        this.currentOpp = "";
    }

    @Override
    public String getName() {
        return "BozzeBot";
    }

    public boolean chronicBetrayer() {
        if (history.size() < 4) {
            return false;
        }
        int betrayed = 0;
        for (int i = 0; i < Math.min(5, history.size()); i++) {
            PastEvent event = history.get(history.size() - 1 - i);
            if (event.oppMove.equals(PrisonerMessResult.BETRAY)) {
                betrayed++;
            }
        }
        return betrayed >= 5;
    }

    @Override
    public PrisonerMessResult messAround(String s) {
        if ((!s.equals(this.currentOpp)) || this.history.isEmpty()){
            this.history = new ArrayList<>();
            this.currentOpp = s;
            history.add(new PastEvent(PrisonerMessResult.COOPERATE));
            return PrisonerMessResult.COOPERATE;
        }
        if (chronicBetrayer()) {
            return PrisonerMessResult.BETRAY;
        }
        PastEvent last = history.get(history.size()-1);
        if (last.myMove.equals(last.oppMove)) {
            history.add(new PastEvent(PrisonerMessResult.COOPERATE));
            return PrisonerMessResult.COOPERATE;
        } else {
            history.add(new PastEvent(PrisonerMessResult.BETRAY));
            return PrisonerMessResult.BETRAY;
        }
    }

    @Override
    public void onPostMessEvent(PostMessEvent postMessEvent) {
        PastEvent currentEvent = history.get(history.size()-1);
        currentEvent.setOppMove(postMessEvent.result());
    }
}
