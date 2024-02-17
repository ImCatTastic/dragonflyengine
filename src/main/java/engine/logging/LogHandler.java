package engine.logging;

import engine.identification.LoggerIdentifier;
import engine.logging.formatting.DateFormatter;
import engine.logging.formatting.LogFormatter;
import engine.logging.formatting.TimeFormatter;
import engine.identification.Identifier;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

import static engine.logging.formatting.DateFormatter.Component.*;
import static engine.logging.formatting.LogComponent.*;
import static engine.logging.formatting.LogComponent.MESSAGE;
import static engine.logging.formatting.TimeFormatter.Component.*;
import static engine.logging.formatting.TimeFormatter.Component.HOUR;

public final class LogHandler
{
    private LogHandler() {}
    private static LocalTime startDate;
    public static void setStartDate()
    {
        if(startDate != null)
            throw new IllegalStateException("Cannot set startTime twice");

        startDate = LocalTime.now();
    }

    public static LogPriority minPriority = LogPriority.LOW;
    public static LogMode logMode = LogMode.DEFAULT;
    public static boolean useAbsoluteTime = false;
    public static boolean timeEnabled = true;
    public static boolean dateEnabled = true;
    public static boolean categoryEnabled = true;
    public static boolean priorityEnabled = true;
    public static boolean namespaceEnabled = true;
    public static boolean stackTraceEnabled = true;
    private static LogFormatter logFormatter = new LogFormatter(DATE, TIME, NAMESPACE, PRIORITY, CATEGORY, MESSAGE);
    private static DateFormatter dateFormatter = new DateFormatter(YEAR, MONTH, DAY);
    private static TimeFormatter timeFormatter = new TimeFormatter(HOUR, MINUTE, SECOND, MILLISECOND);
    private final static ArrayList<LoggerIdentifier> whiteList = new ArrayList<>();
    private final static ArrayList<LoggerIdentifier> blackList = new ArrayList<>();
    private final static HashMap<Identifier, Logger> loggerRegistry = new HashMap<>();
    private final static ConcurrentLinkedDeque<LogMessage> queuedMessages = new ConcurrentLinkedDeque<>();
    public static void setLogFormatter(LogFormatter formatter)
    {
        logFormatter = formatter;
    }
    public static void setDateFormatter(DateFormatter dateFormatter)
    {
        LogHandler.dateFormatter = dateFormatter;
    }
    public static void setTimeFormatter(TimeFormatter timeFormatter)
    {
        LogHandler.timeFormatter = timeFormatter;
    }

    public static void update()
    {
        while (!queuedMessages.isEmpty())
            System.out.println(formatMessage(queuedMessages.pop()));
    }
    public static void queue(LogMessage message)
    {
        switch (logMode)
        {
            case WHITELIST ->
            {
                if(whiteList.contains(message.identifier()) && message.priority().ordinal() >= minPriority.ordinal())
                    queuedMessages.add(message);
            }
            case BLACKLIST ->
            {
                if(!blackList.contains(message.identifier()) && message.priority().ordinal()  >= minPriority.ordinal())
                    queuedMessages.add(message);
            }
            default -> queuedMessages.add(message);
        }
    }
    public static void whiteListLogger(LoggerIdentifier identifier)
    {
        if(!whiteList.contains(identifier))
            whiteList.add(identifier);
    }
    public static void blackListListLogger(LoggerIdentifier identifier)
    {
        if(!blackList.contains(identifier))
            blackList.add(identifier);
    }

    public static LocalTime getPassedTime()
    {
        var time = LocalTime.of(0,0,0, 0);
        if(startDate == null)
            return time;

        return time.plus(Duration.between(startDate, LocalTime.now()));
    }
    public static void registerLogger(Logger logger)
    {
        if(!loggerRegistry.containsKey(logger.getIdentifier()))
        {
            loggerRegistry.put(logger.getIdentifier(), logger);
        }
    }
    public static String formatMessage(LogMessage message)
    {
        return logFormatter.format(message);
    }
    public static String formatDate(LogMessage message)
    {
        return dateFormatter.format(message.date());
    }
    public static String formatTime(LogMessage message)
    {
        return timeFormatter.format(useAbsoluteTime ? message.absoluteTime() : message.relativeTime());
    }
}
