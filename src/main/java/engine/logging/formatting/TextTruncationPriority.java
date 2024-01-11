package engine.logging.formatting;

import java.util.function.BiFunction;

public enum TextTruncationPriority
{
    START_THEN_CENTER,
    START_THEN_TAIL,
    CENTER_THEN_HEAD,
    CENTER_THEN_TAIL,
    TAIL_THEN_HEAD,
    TAIL_THEN_CENTER
}

