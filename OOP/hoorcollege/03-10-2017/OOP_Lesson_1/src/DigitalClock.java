import be.kuleuven.cs.som.annotate.Basic;

/**
 * A class of digital for a digital clock displaying time in terms of hours and minutes
 * @author Daniel Mertens
 * @version 1.0.0
 *
 */
public class DigitalClock {
	
	private int hours;
	private int minutes;
	private int seconds;
	
	/**
	 * @effect ...
	 * 		| this.setHours(this.getMinHours())
	 * @effect ...
	 * 		| this.setMinutes(this.getMinMinutes())
	 * @effect ...
	 * 		| this.setSeconds(0)
	 */
	public DigitalClock() {
		this(0,0,0);
	}
	
	/**
	 * @effect ...
	 * 		| this.setHours(hours)
	 * @effect ...
	 * 		| this.setMinutes(minutes)
	 * @effect ...
	 * 		| this.setSeconds(0)
	 */
	public DigitalClock(int hours, int minutes) {
		this(hours,minutes,0);
	}
	
	/**
	 * @effect ...
	 * 		| this.setHours(hours)
	 * @effect ...
	 * 		| this.setMinutes(minutes)
	 * @effect ...
	 * 		| this.setSeconds(seconds)
	 */
	public DigitalClock(int hours, int minutes, int seconds) {
		this.setHours(hours);
		this.setMinutes(minutes);
		this.setSeconds(seconds);
	}
	
	/**
	 * Return the hours displayed by this digital clock.
	 */
	@Basic
	public int getHours() {
		return hours;
	}
	
	/**
	 * Set the hours of this digital clock to the given hours.
	 * @param hours The new hours of this digital clock.
	 * @post ...
	 * 		| if (hours >= this.getMinHours() && hours <= this.getMaxhours())
	 * 		|	then new.getHours() == hours
	 * @post ...
	 * 		| if (hours < this.getMinHours())
	 * 		| 	then new.getHours() == this.getMinHours()
	 * @post ...
	 * 		| if (hours > this.getMaxhours())
	 * 		| 	then new.getHours() == 
	 * 		|		hours % (this.getMaxhours() + 1 - this.getMinHours()) 
	 * 		|		+ this.getMinHours()
	 */
	@Basic
	public void setHours(int hours) {
		if(hours < this.getMinHours())
			this.hours = this.getMinHours();
		else if(hours > this.getMaxHours())
			this.hours = hours % (this.getMaxHours() + 1 - this.getMinHours()) + this.getMinHours();
		else 
			this.hours = hours;
	}
	
	/**
	 * Return the minutes displayed by the digital clock.
	 */
	@Basic
	public int getMinutes() {
		return minutes;
	}
	
	/**
	 * Set the minutes of this digital clock to the given minutes.
	 * @param minutes The new minutes of this digital clock.
	 * @post ...
	 * 		| if (minutes >= 0 && minutes <= 59)
	 * 		|	then new.getMinutes() == minutes
	 * @post ...
	 * 		| if (minutes < 0)
	 * 		| 	then new.getMinutes() == minutes % 60 + 60
	 * @post ...
	 * 		| if (minutes > 59)
	 * 		| 	then new.getMinutes() == minutes % 60
	 */ 
	public void setMinutes(int minutes) {
		if(minutes < 0)
			this.minutes = minutes % 60 + 60;
		else if(minutes > 59)
			this.minutes = minutes % 60;
		else 
			this.minutes = minutes;
	}

	/**
	 * Return the maximum value for the hours of this digital clock.
	 */
	@Basic
	public int getMaxHours() {
		return 24;
	}

	/**
	 * Return the minimum value for the hours of this digital clock.
	 */
	@Basic
	public int getMinHours() {
		return 0;
	}	
	
	/**
	 * This method adds 1 minute to this clocks time.
	 * 
	 * @post ...
	 * 		| if (this.minutes >= 59 && this.getHours >= this.getMaxHours())
	 * 		|	then new.getHours() == 0 
	 * 		|		&& new.getMinutes() == 0
	 * @post ...
	 * 		| if (this.minutes >= 59 && this.getHours < this.getMaxHours())
	 * 		|	then new.getHours() == this.getHours() + 1 
	 * 		|		&& new.getMinutes() == 0
	 * @post ...
	 * 		| if(this.minutes < 59)
	 * 		|	then new.getMinutes() == this.getMinutes() + 1
	 */
	public void incrementTimeByMinute() {
		if(this.minutes >= 59)
			this.setHours(getHours()+1);
		this.setMinutes(getMinutes()+1);
	}
	
	/**
	 * @effect ...
	 * 		| this.setMinutes(getMinutes() + 1)
	 * @post ...
	 * 		| if(this.minutes >= 59)
	 * 		|	then this.setHours(this.getHours() + 1)
	 */
	public void incrementTimeByMinuteAlt() {
		if(this.minutes >= 59)
			this.setHours(getHours()+1);
		this.setMinutes(getMinutes()+1);
	}
	
	/**
	 * Returns the seconds of this digital clock.
	 */
	@Basic
	public int getSeconds() {
		return this.seconds;
	}
	
	/**
	 * @param seconds
	 * @post ...
	 * 		| if (seconds >= 0 && seconds <= 59)
	 * 		|	then new.getSeconds() == seconds
	 * @post ...
	 * 		| if (seconds < 0)
	 * 		| 	then new.getSeconds() == seconds % 60 + 60
	 * @post ...
	 * 		| if (seconds > 59)
	 * 		| 	then new.getSeconds() == seconds % 60
	 */
	public void setSeconds(int seconds) {
		if(seconds < 0)
			this.seconds = seconds % 60 + 60;
		else if(seconds > 59)
			this.seconds = seconds % 60;
		else 
			this.seconds = seconds;
	}
	
	/**
	 * @effect ...
	 * 		| this.setSeconds(getSeconds() + 1)
	 * @post ...
	 * 		| if(this.minutes >= 59)
	 * 		|	then this.setMinutes(this.getMinutes() + 1)
	 * @post ...
	 * 		| if(this.hours >= getMaxHours())
	 * 		|	then this.setHours(this.getMinHours())
	 */
	public void incrementTimeBySecond() {
		if(this.seconds >= 59)
			this.incrementTimeByMinute();
		this.setSeconds(getSeconds() + 1);
	}
	
}
