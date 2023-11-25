package learnBot.eventHandlers;


import learnBot.FOPEntity;
import learnBot.Field;

public record MouseEvent(Field field, FOPEntity[] entities)
{}
