package temp.learnBot.eventHandlers;


import temp.learnBot.Entity;
import temp.learnBot.Field;

public record MouseEvent(Field field, Entity[] entities)
{}
