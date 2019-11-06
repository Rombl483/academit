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
        if ((this.from <= range.from && this.to >= range.from) ||
                (range.from <= this.from && range.to >= this.from)){
            if (this.to <= range.to) {
                return new Range(range.from, this.to);
            } else {
                return new Range(range.from, range.to);
            }
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        Range[] ranges;

        if ((this.from <= range.from && range.from <= this.to) ||
                (range.from <= this.from && this.from <= range.to)) {
            ranges = new Range[1];

            if (this.to <= range.to) {
                ranges[0] = new Range(range.from, range.to);
            } else {
                ranges[0] = new Range(this.from, this.to);
            }
        } else {
            ranges = new Range[2];
            ranges[0] = this;
            ranges[1] = range;
        }

        return ranges;
    }

    public Range[] getDifference(Range range) {
        Range[] ranges;

        if (this.from <= range.from && range.from <= this.to) {
            if (this.to <= range.to) {
                ranges = new Range[1];
                ranges[0] = new Range(this.from, range.from);
            } else {
                ranges = new Range[2];
                ranges[0] = new Range(this.from, range.from);
                ranges[1] = new Range(range.to, this.to);
            }
        } else if (range.from <= this.from && this.from <= range.to) {
            if (this.to >= range.to) {
                ranges = new Range[1];
                ranges[0] = new Range(range.to, this.to);
            } else {
                return null;
            }
        } else {
            ranges = new Range[1];
            ranges[0] = this;
        }

        return ranges;
    }
}
