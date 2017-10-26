package domain;

import exceptions.IllegalRealIntervalFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Olha_Chuchuk on 10/12/2017.
 */
public class RealInterval {
    private final double from;
    private final double to;

    public RealInterval(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public static RealInterval valueOf(String interval) {
        Pattern p = Pattern.compile("\\[\\s*(.*)\\s*,\\s*(.*)\\s*\\]");
        Matcher matcher = p.matcher(interval);
        if(!matcher.matches()) {
            throw new IllegalRealIntervalFormatException();
        }
        String fromStr;
        String toStr;
        try {
            fromStr = matcher.group(1);
            toStr = matcher.group(2);
        } catch (NumberFormatException e) {
            throw new IllegalRealIntervalFormatException(e);
        }
        Double from = Double.valueOf(fromStr);
        Double to = Double.valueOf(toStr);
        if(to < from) {
            throw new IllegalRealIntervalFormatException();
        }
        return new RealInterval(from, to);
    }

    @Override
    public String toString() {
        return "[" + from +
                ", " + to +
                ']';
    }
}
