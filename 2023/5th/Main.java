import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
  public static ArrayList<String> readInputContent(String path) {
    ArrayList<String> content = new ArrayList<String>();
    try {
      File myObj = new File(path);
      Scanner myReader = new Scanner(myObj);

      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        content.add(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred:");
      e.printStackTrace();
    }
    return content;
  }

  public static void main(String[] args) {
    System.out.println("If You Give A Seed A Fertilizer");

    var content = readInputContent("IfYouGiveASeedAFertilizer.txt");
    var ifYouGiveASeedAFertilizer = new IfYouGiveASeedAFertilizer(content);

    long part1 = ifYouGiveASeedAFertilizer.getLowestLocation1();
    System.out.println("part 1: " + Long.toString(part1));

    // DAMN...
    // TODO: Maybe later improvement?
    long part2 = ifYouGiveASeedAFertilizer.getLowestLocation2();
    System.out.println("part 2: " + Long.toString(part2));
  }
}
