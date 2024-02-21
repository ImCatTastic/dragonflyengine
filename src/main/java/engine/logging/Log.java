package engine.logging;

import engine.identification.Identifier;
import engine.logging.formatting.DateFormatter;
import engine.logging.formatting.LogFormatter;
import engine.logging.formatting.TimeFormatter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;

import static engine.logging.formatting.DateFormatter.Component.*;
import static engine.logging.formatting.LogComponent.*;
import static engine.logging.formatting.TimeFormatter.Component.*;

public class Log
{
    private final static Identifier GENERIC_ID = new Identifier("LOG_GENERIC");
    private final static LocalTime startDate = LocalTime.now();
    public static LogPriority minPriority = LogPriority.LOW;
    public static LogFilter logFilter = LogFilter.NONE;
    public static boolean useAbsoluteTime = false;
    public static boolean timeEnabled = true;
    public static boolean dateEnabled = true;
    public static boolean categoryEnabled = true;
    public static boolean priorityEnabled = true;
    public static boolean namespaceEnabled = true;
    public static boolean stackTraceEnabled = true;
    private final static ArrayList<Identifier> whiteList = new ArrayList<>();
    private final static ArrayList<Identifier> blackList = new ArrayList<>();
    private final static ConcurrentLinkedDeque<LogMessage> queuedMessages = new ConcurrentLinkedDeque<>();
    private static LogFormatter logFormatter = new LogFormatter(DATE, TIME, PRIORITY, STACKTRACE, CATEGORY, MESSAGE);
    private static DateFormatter dateFormatter = new DateFormatter(YEAR, MONTH, DAY);
    private static TimeFormatter timeFormatter = new TimeFormatter(HOUR, MINUTE, SECOND, MILLISECOND);
    public static void setLogFormatter(LogFormatter formatter)
    {
        logFormatter = formatter;
    }
    public static void setDateFormatter(DateFormatter dateFormatter)
    {
        Log.dateFormatter = dateFormatter;
    }
    public static void setTimeFormatter(TimeFormatter timeFormatter)
    {
        Log.timeFormatter = timeFormatter;
    }
    public static void debug(Identifier logID, String message, LogPriority priority)
    {
        queue(build(logID, message, priority, LogCategory.DEBUG));
    }
    public static void debug(Identifier logID, String message)
    {
        queue(build(logID, message, LogPriority.LOW, LogCategory.DEBUG));
    }
    public static void warn(Identifier logID, String message, LogPriority priority)
    {
        queue(build(logID, message, priority, LogCategory.WARN));
    }
    public static void warn(Identifier logID, String message)
    {
        queue(build(logID, message, LogPriority.LOW, LogCategory.WARN));
    }
    public static void info(Identifier logID, String message, LogPriority priority)
    {
        queue(build(logID, message, priority, LogCategory.INFO));
    }
    public static void info(Identifier logID, String message)
    {
        queue(build(logID, message, LogPriority.LOW, LogCategory.INFO));
    }
    public static void error(Identifier logID, String message, LogPriority priority)
    {
        queue(build(logID, message, priority, LogCategory.ERROR));
    }
    public static void error(Identifier logID, String message)
    {
        queue(build(logID, message, LogPriority.LOW, LogCategory.ERROR));
    }
    public static void fatal(Identifier logID, String message)
    {
        queue(build(logID, message, LogPriority.LOW, LogCategory.FATAL));
    }
    public static void fatal(Identifier logID, String message, LogPriority priority)
    {
        queue(build(logID, message, priority, LogCategory.FATAL));
    }
    public static void update()
    {
        while (!queuedMessages.isEmpty())
            System.out.println(formatMessage(queuedMessages.pop()));
    }
    private static LogMessage build(Identifier logID, String message, LogPriority priority, LogCategory category)
    {
        return new LogMessage(
                logID,
                Thread.currentThread().getStackTrace()[3],
                message,
                priority,
                category,
                getPassedTime(),
                LocalTime.now(),
                LocalDate.now()
        );
    }
    private static void queue(LogMessage message)
    {
        switch (logFilter)
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
    private static LocalTime getPassedTime()
    {
        var time = LocalTime.of(0,0,0, 0);
        return time.plus(Duration.between(startDate, LocalTime.now()));
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
