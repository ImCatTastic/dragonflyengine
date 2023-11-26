package learnBot.eventHandlers;


import learnBot.Entity;
import learnBot.Field;

public record MouseEvent(Field field, Entity[] entities)
{}
