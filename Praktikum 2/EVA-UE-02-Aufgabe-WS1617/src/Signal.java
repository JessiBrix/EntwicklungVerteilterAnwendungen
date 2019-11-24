public class Signal {

    private boolean rot = false; //Ost
    private boolean gruen = false; //West

    public boolean isRot() {
        return rot;
    }

    public void setRot(boolean rot) {
        this.rot = rot;
    }

    public boolean isGruen() {
        return gruen;
    }

    public void setGruen(boolean gruen) {
        this.gruen = gruen;
    }

    public void faerbe(String s){
        if (s.equals("west"))
        {
            rot = false;
            gruen = true;
        }
        else if (s.equals("ost")){
            rot = true;
            gruen = false;
        }
        else
        {
            rot = false;
            gruen = false;
        }
    }
}
