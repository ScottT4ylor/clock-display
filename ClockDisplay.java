
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private int is12HourClock = 1;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }
    
    public void toggle12HourClock()
    {
        is12HourClock = (is12HourClock + 1) % 2;
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        if (is12HourClock == 1)
        {
            int modHours = hours.getValue();
            String ampm = "";
            if (modHours >= 12)
            {
                modHours = modHours - 12;
                ampm = "pm";
            
                if (modHours == 0)
                {
                    modHours = 12;
                }
            }
            else if (modHours == 0)
            {
                modHours = 12;
                ampm = "am";
            }
            else
            {
                ampm = "am";
            }
            String convertHours = hoursConvert(modHours);
            displayString = convertHours + ":" +
            minutes.getDisplayValue() + ampm;
        }
        else
        {
            displayString = hours.getDisplayValue() + ":" + 
            minutes.getDisplayValue();
            }
    }
    
    private String hoursConvert(int value)
    {
        if(value < 10) {
            return "0" + value;
        }
        else {
            return "" + value;
        }
    }
}
