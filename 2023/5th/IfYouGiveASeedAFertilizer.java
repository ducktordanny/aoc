import java.util.ArrayList;

public class IfYouGiveASeedAFertilizer {
  private ArrayList<Long> seedIDs = new ArrayList<Long>();
  private ArrayList<ConvertNumbersMap> seedToSoil = new ArrayList<ConvertNumbersMap>();
  private ArrayList<ConvertNumbersMap> soilToFertilizer = new ArrayList<ConvertNumbersMap>();
  private ArrayList<ConvertNumbersMap> fertilizerToWater = new ArrayList<ConvertNumbersMap>();
  private ArrayList<ConvertNumbersMap> waterToLight = new ArrayList<ConvertNumbersMap>();
  private ArrayList<ConvertNumbersMap> lightToTemperature = new ArrayList<ConvertNumbersMap>();
  private ArrayList<ConvertNumbersMap> temperatureToHumidity = new ArrayList<ConvertNumbersMap>();
  private ArrayList<ConvertNumbersMap> humidityToLocation = new ArrayList<ConvertNumbersMap>();

  IfYouGiveASeedAFertilizer(ArrayList<String> content) {
    setSeedIDs(content.get(0));

    AlmanacMapTypes currentState = null;
    for (int i = 2; i < content.size(); i++) {
      var line = content.get(i);

      if (line == "")
        currentState = null;

      if (currentState != null) {
        String[] mapSlitValue = line.split(" ");
        var destination = Long.parseLong(mapSlitValue[0]);
        var source = Long.parseLong(mapSlitValue[1]);
        var rangeLength = Long.parseLong(mapSlitValue[2]);

        if (currentState == AlmanacMapTypes.SeedToSoil)
          this.seedToSoil.add(new ConvertNumbersMap(destination, source, rangeLength));
        if (currentState == AlmanacMapTypes.SoilToFertilizer)
          this.soilToFertilizer.add(new ConvertNumbersMap(destination, source, rangeLength));
        if (currentState == AlmanacMapTypes.FertilizerToWater)
          this.fertilizerToWater.add(new ConvertNumbersMap(destination, source, rangeLength));
        if (currentState == AlmanacMapTypes.WaterToLight)
          this.waterToLight.add(new ConvertNumbersMap(destination, source, rangeLength));
        if (currentState == AlmanacMapTypes.LightToTemperature)
          this.lightToTemperature.add(new ConvertNumbersMap(destination, source, rangeLength));
        if (currentState == AlmanacMapTypes.TemperatureToHumidity)
          this.temperatureToHumidity.add(new ConvertNumbersMap(destination, source, rangeLength));
        if (currentState == AlmanacMapTypes.HumidityToLocation)
          this.humidityToLocation.add(new ConvertNumbersMap(destination, source, rangeLength));
      }

      if (line.contains("map")) {
        currentState = AlmanacMapTypes.fromValue(line.split(" ")[0]);
      }
    }
  }

  public long getLowestLocation2() {
    long lowestLocation = Long.MAX_VALUE;
    for (int i = 0; i < seedIDs.size(); i += 2) {
      long from = seedIDs.get(i);
      for (long j = from; j < from + seedIDs.get(i + 1); j++) {
        long location = getLocationFromSeed(j);
        if (lowestLocation > location)
          lowestLocation = location;
      }
    }
    return lowestLocation;
  }

  public long getLowestLocation1() {
    long lowestLocation = Long.MAX_VALUE;
    for (var seedID : seedIDs) {
      long location = getLocationFromSeed(seedID);
      if (lowestLocation > location)
        lowestLocation = location;
    }
    return lowestLocation;
  }

  private long getLocationFromSeed(long seed) {
    long soil = Long.MAX_VALUE;
    for (ConvertNumbersMap convertMap : seedToSoil)
      if (convertMap.isInRange(seed)) {
        soil = convertMap.transform(seed);
        break;
      }
    if (soil == Long.MAX_VALUE)
      soil = seed;

    long fertilizer = Long.MAX_VALUE;
    for (ConvertNumbersMap convertMap : soilToFertilizer)
      if (convertMap.isInRange(soil)) {
        fertilizer = convertMap.transform(soil);
        break;
      }
    if (fertilizer == Long.MAX_VALUE)
      fertilizer = soil;

    long water = Long.MAX_VALUE;
    for (ConvertNumbersMap convertMap : fertilizerToWater)
      if (convertMap.isInRange(fertilizer)) {
        water = convertMap.transform(fertilizer);
        break;
      }
    if (water == Long.MAX_VALUE)
      water = fertilizer;

    long light = Long.MAX_VALUE;
    for (ConvertNumbersMap convertMap : waterToLight)
      if (convertMap.isInRange(water)) {
        light = convertMap.transform(water);
        break;
      }
    if (light == Long.MAX_VALUE)
      light = water;

    long temperature = Long.MAX_VALUE;
    for (ConvertNumbersMap convertMap : lightToTemperature)
      if (convertMap.isInRange(light)) {
        temperature = convertMap.transform(light);
        break;
      }
    if (temperature == Long.MAX_VALUE)
      temperature = light;

    long humidity = Long.MAX_VALUE;
    for (ConvertNumbersMap convertMap : temperatureToHumidity)
      if (convertMap.isInRange(temperature)) {
        humidity = convertMap.transform(temperature);
        break;
      }
    if (humidity == Long.MAX_VALUE)
      humidity = temperature;

    long location = Long.MAX_VALUE;
    for (ConvertNumbersMap convertMap : humidityToLocation)
      if (convertMap.isInRange(humidity)) {
        location = convertMap.transform(humidity);
        break;
      }
    if (location == Long.MAX_VALUE)
      location = humidity;

    return location;
  }

  private void setSeedIDs(String seedLine) {
    boolean shouldSkip = true;
    var splits = seedLine.split(" ");
    for (String split : splits) {
      if (shouldSkip == true) {
        shouldSkip = false;
        continue;
      }

      seedIDs.add(Long.parseLong(split));
    }
  }
}
