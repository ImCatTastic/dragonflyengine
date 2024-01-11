package engine.logging;

import engine.core.Engine;
import engine.util.Identifier;
import engine.util.Namespace;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Logger
{
    private final LoggerIdentifier identifier;
    private final ArrayList<LogMessage> messages = new ArrayList<>();

    public Logger(LoggerIdentifier identifier)
    {
        this.identifier = identifier;
        identifier.setHolder(this);
        LogHandler.registerLogger(this);
    }

    public void log(String message, LogPriority priority, LogCategory category)
    {
        var traceElement = Thread.currentThread().getStackTrace()[1];
        var logMessage = new LogMessage(identifier, traceElement, message, priority, category, LogHandler.getPassedTime(), LocalTime.now(), LocalDate.now());
        messages.add(logMessage);
        LogHandler.queue(logMessage);
    }
    public Identifier getIdentifier()
    {
        return identifier;
    }
    public List<LogMessage> getMessages()
    {
        return Collections.unmodifiableList(messages).stream().toList();
    }
}
