package MemTrix;

public class MemLeak {
    MemLeak last = null;
    String [] s = new String [10000];//the meat and potatoes of our memory consumption.
    public MemLeak() {    }

    public MemLeak(MemLeak poop){
        last = poop;
    }
}
