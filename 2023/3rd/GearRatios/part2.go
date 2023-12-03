package gearratios

import (
	"regexp"
	"strings"
)

func getAmountOfStars(line string) int {
	reg := regexp.MustCompile("[*]+")
	return len(reg.FindAllString(line, -1))
}

func checkAdjecentLines(starIndexInLine int, line string, gearRatio *int, countOfPartNumbers *int) {
	previousChar := ""
	for i := starIndexInLine - 1; i < starIndexInLine+2 && i < len(line); i += 1 {
		if i < 0 {
			continue
		}
		if IsNumber(previousChar) && IsNumber(string(line[i])) {
			continue
		}
		number, _ := FindNumberInStringNearToIndex(line, i)
		if number != -1 {
			*gearRatio *= number
			*countOfPartNumbers += 1
		}
		previousChar = string(line[i])
	}
}

func Part2(schematicLines []string) int {
	sumOfGearRatios := 0

	for lineIndex, line := range schematicLines {
		amountOfStars := getAmountOfStars(line)

		lineWithoutDuplicates := line
		for starIndex := 0; starIndex < amountOfStars; starIndex += 1 {
			countOfPartNumbers := 0
			gearRatio := 1

			starIndexInLine := strings.Index(lineWithoutDuplicates, "*")

			if lineIndex-1 >= 0 {
				aboveLine := schematicLines[lineIndex-1]
				checkAdjecentLines(starIndexInLine, aboveLine, &gearRatio, &countOfPartNumbers)
			}
			if lineIndex+1 < len(schematicLines) {
				belowLine := schematicLines[lineIndex+1]
				checkAdjecentLines(starIndexInLine, belowLine, &gearRatio, &countOfPartNumbers)
			}
			if starIndexInLine-1 > 0 {
				number, _ := FindNumberInStringNearToIndex(line, starIndexInLine-1)
				if number != -1 {
					gearRatio *= number
					countOfPartNumbers += 1
				}
			}
			if starIndexInLine+1 < len(line) {
				number, _ := FindNumberInStringNearToIndex(line, starIndexInLine+1)
				if number != -1 {
					gearRatio *= number
					countOfPartNumbers += 1
				}
			}

			ReplaceCharacterAtIndex(&lineWithoutDuplicates, ".", starIndexInLine)
			if countOfPartNumbers >= 2 {
				sumOfGearRatios += gearRatio
			}
		}
	}
	return sumOfGearRatios
}
