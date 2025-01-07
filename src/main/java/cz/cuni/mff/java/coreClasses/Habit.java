package cz.cuni.mff.java.coreClasses;

import java.util.Date;

public class Habit {

    public String Name;
    public Date CreationDate;
    /**
     * User selected target for a habit
     * Default value is -1, which indicated that there is no target for this habbit
     */
    public int Target = -1;
}
