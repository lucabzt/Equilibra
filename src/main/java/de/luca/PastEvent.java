package de.luca;

import de.kyle.gefangenendilemma.api.result.PrisonerMessResult;

public class PastEvent {
    PrisonerMessResult myMove;
    PrisonerMessResult oppMove;

    public PastEvent(PrisonerMessResult myMove) {
        this.myMove = myMove;
    }

    public PrisonerMessResult getMyMove() {
        return myMove;
    }

    public void setMyMove(PrisonerMessResult myMove) {
        this.myMove = myMove;
    }

    public PrisonerMessResult getOppMove() {
        return oppMove;
    }

    public void setOppMove(PrisonerMessResult oppMove) {
        this.oppMove = oppMove;
    }
}
