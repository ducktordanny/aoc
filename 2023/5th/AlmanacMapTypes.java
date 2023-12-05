public enum AlmanacMapTypes {
  SeedToSoil("seed-to-soil"),
  SoilToFertilizer("soil-to-fertilizer"),
  FertilizerToWater("fertilizer-to-water"),
  WaterToLight("water-to-light"),
  LightToTemperature("light-to-temperature"),
  TemperatureToHumidity("temperature-to-humidity"),
  HumidityToLocation("humidity-to-location");

  private final String value;

  private AlmanacMapTypes(String value) {
    this.value = value;
  }

  public static AlmanacMapTypes fromValue(String value) {
    for (AlmanacMapTypes almanacMapTypes : AlmanacMapTypes.values()) {
      if (almanacMapTypes.value.equals(value)) {
        return almanacMapTypes;
      }
    }
    throw new IllegalArgumentException("No enum constant value: " + value);
  }
}
