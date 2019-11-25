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

    public Range getIntersection(Range range) {
        if (Math.max(from, range.from) < Math.min(to, range.to)) {
            return new Range(Math.max(from, range.from), Math.min(to, range.to));
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if (Math.max(from, range.from) <= Math.min(to, range.to)) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        } else {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }
    }

    public Range[] getDifference(Range range) {
        if (from < range.from && range.from < to) {
            if (to < range.to) {
                return new Range[]{new Range(from, range.from)};
            } else {
                return new Range[]{new Range(from, range.from), new Range(range.to, to)};
            }
        } else if (range.from <= from && from <= range.to) {
            if (to > range.to) {
                return new Range[]{new Range(range.to, to)};
            } else {
                return new Range[0];
            }
        } else {
            return new Range[]{new Range(from, to)};
        }
    }
}
