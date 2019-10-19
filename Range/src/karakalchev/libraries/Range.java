package karakalchev.libraries;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double value) {
        return from <= value && value <= to;
    }

    public void print() {
        System.out.printf("Числовой диапазон =  [%.2f .. %.2f]%n", from, to);
    }

    public static Range getIntersection(Range range1, Range range2) {
        if (range1.from <= range2.from && range2.from <= range1.to) {
            if (range1.to <= range2.to) {
                return new Range(range2.from, range1.to);
            } else {
                return new Range(range2.from, range2.to);
            }
        }

        return null;
    }

    public static Range[] getUnion(Range range1, Range range2) {
        Range[] ranges = new Range[2];

        if (range1.from <= range2.from && range2.from <= range1.to) {
            if (range1.to <= range2.to) {
                ranges[0] = new Range(range1.from, range2.to);
            } else {
                ranges[0] = new Range(range1.from, range1.to);
            }
        } else {
            ranges[0] = range1;
            ranges[1] = range2;
        }

        return ranges;
    }

    public static Range[] getDifference(Range range1, Range range2) {
        Range[] ranges = new Range[2];

        if (range1.from <= range2.from && range2.from <= range1.to && range1.to <= range2.to) {
            ranges[0] = new Range(range1.from, range2.from);
        } else {
            ranges[0] = range1;
            ranges[1] = range2;
        }

        return ranges;
    }
}
