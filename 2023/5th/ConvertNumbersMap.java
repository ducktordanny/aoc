public class ConvertNumbersMap {
  private long destination;
  private long source;
  private long rangeLength;

  ConvertNumbersMap(long destination, long source, long rangeLength) {
    this.destination = destination;
    this.source = source;
    this.rangeLength = rangeLength;
  }

  public boolean isInRange(long source) {
    return source >= this.source && source < this.source + rangeLength;
  }

  public long transform(long source) {
    if (!isInRange(source))
      throw new Error("Given source is out of range");
    return source + (destination - this.source);
  }
}
