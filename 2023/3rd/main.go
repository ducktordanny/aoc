package main

import (
	"bufio"
	"fmt"
	"os"

	gearratios "github.com/ducktordanny/aoc/2023/3rd/GearRatios"
)

func main() {
	fmt.Println("GearRatios")

	file, err := os.Open("./GearRatios.txt")
	if err != nil {
		fmt.Println(err)
		os.Exit(1)
	}

	fileScanner := bufio.NewScanner(file)
	fileScanner.Split(bufio.ScanLines)

	var schematicLines []string
	for fileScanner.Scan() {
		schematicLines = append(schematicLines, fileScanner.Text())
	}
	part1Result := gearratios.Part1(schematicLines)
	fmt.Println("part 1:", part1Result)
	part2Result := gearratios.Part2(schematicLines)
	fmt.Println("part 2:", part2Result)

	file.Close()
}
