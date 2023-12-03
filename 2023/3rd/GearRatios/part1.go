package gearratios

import (
	"regexp"
	"strconv"
	"strings"
)

func getNumbersFromLine(line string) []string {
	reg := regexp.MustCompile("[0-9]+")
	return reg.FindAllString(line, -1)
}

func checkAdjecentCharacter(adjecentChar byte) bool {
	notADot := adjecentChar != '.'
	_, err := strconv.Atoi(string(adjecentChar))
	notANumber := err != nil
	return notADot && notANumber
}

func Part1(schematicLines []string) int {
	sumOfPartNumbers := 0

	for lineIndex, line := range schematicLines {

		numbersInLine := getNumbersFromLine(line)
		lineWithoutDuplicates := line

		for _, numberStr := range numbersInLine {
			shouldAddNumber := false
			startIndex := strings.Index(lineWithoutDuplicates, numberStr)

			for j := range numberStr {
				// helper variables
				aboveIndex := lineIndex - 1
				belowIndex := lineIndex + 1
				leftIndex := startIndex - 1
				rightIndex := startIndex + j + 1
				canCheckAbove := aboveIndex >= 0
				canCheckBelow := belowIndex < len(schematicLines)
				canCheckLeft := j == 0 && leftIndex >= 0
				canCheckRight := j == len(numberStr)-1 && rightIndex < len(line)

				// check above
				if canCheckAbove && checkAdjecentCharacter(schematicLines[aboveIndex][startIndex+j]) {
					shouldAddNumber = true
				}
				// check below
				if canCheckBelow && checkAdjecentCharacter(schematicLines[belowIndex][startIndex+j]) {
					shouldAddNumber = true
				}
				// check left
				if canCheckLeft && checkAdjecentCharacter(line[leftIndex]) {
					shouldAddNumber = true
				}
				// check right
				if canCheckRight && checkAdjecentCharacter(line[rightIndex]) {
					shouldAddNumber = true
				}
				// diagonal left
				if canCheckLeft && canCheckAbove && checkAdjecentCharacter(schematicLines[aboveIndex][leftIndex]) {
					shouldAddNumber = true
				}
				if canCheckLeft && canCheckBelow && checkAdjecentCharacter(schematicLines[belowIndex][leftIndex]) {
					shouldAddNumber = true
				}
				// diagonal right
				if canCheckRight && canCheckAbove && checkAdjecentCharacter(schematicLines[aboveIndex][rightIndex]) {
					shouldAddNumber = true
				}
				if canCheckRight && canCheckBelow && checkAdjecentCharacter(schematicLines[belowIndex][rightIndex]) {
					shouldAddNumber = true
				}

				// replace number in line to avoid duplications later
				ReplaceCharacterAtIndex(&lineWithoutDuplicates, ".", startIndex+j)
			}

			if shouldAddNumber {
				number, err := strconv.Atoi(numberStr)
				if err != nil {
					continue
				}
				sumOfPartNumbers += number
			}
		}
	}
	return sumOfPartNumbers
}
