Console.WriteLine("Wait For It");

/// <summary>
/// Based on the assignment: d = (T - t) * t
/// Transforming this we get: t1,2 = (T +- sqrt(T^2 - 4*d)) / 2
/// Where t1,2 are one of the times the recorder had to hold
/// the button for getting to the given d (distance) with T (length of race).
/// </summary>
double GetWaysToBeatRecord(long T, long d)
{
    double t1 = (T + Math.Sqrt(T * T - 4 * d)) / 2;
    double t2 = (T - Math.Sqrt(T * T - 4 * d)) / 2;
    double upperLimit = Math.Ceiling(t1 < t2 ? t2 : t1);
    double lowerLimit = Math.Floor(t1 > t2 ? t2 : t1);
    return upperLimit - lowerLimit - 1;
}

StreamReader sr = new StreamReader("input.txt");
var timesStr = sr.ReadLine();
var distancesStr = sr.ReadLine();
if (timesStr == null || distancesStr == null)
{
    sr.Close();
    Console.WriteLine("Cannot read necessary lines from input.");
    Environment.Exit(1);
}
sr.Close();

int[] times = timesStr
    .Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries)
    .Where(part => !part.Contains(":")).ToArray()
    .Select(part => int.Parse(part)).ToArray();
int[] distances = distancesStr
    .Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries)
    .Where(part => !part.Contains(":")).ToArray()
    .Select(part => int.Parse(part)).ToArray();

// Part 1
double part1Result = 1;
for (int i = 0; i < times.Length; i++)
{
    part1Result *= GetWaysToBeatRecord(times[i], distances[i]);
}

Console.WriteLine("Part 1: {0}", part1Result);

// Part 2
double part2Restult = GetWaysToBeatRecord(long.Parse(string.Join("", times)), long.Parse(string.Join("", distances)));
Console.WriteLine("Part 2: {0}", part2Restult);
