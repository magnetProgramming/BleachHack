package org.bleachhack.event.events;

import java.util.List;

import org.bleachhack.event.Event;

public class EventDebugHud extends Event 
{
	
	public List<String> lines;
	
    public EventDebugHud(List<String> lines) 
    {
    	this.lines = lines;
    }
}
