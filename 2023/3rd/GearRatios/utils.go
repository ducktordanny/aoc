package gearratios

import (
	"errors"
	"fmt"
	"os"
	"strconv"
)

func FindNumberInStringNearToIndex(str string, index int) (int, error) {
	_, err := strconv.Atoi(string(str[index]))
	if err != nil {
		return -1, errors.New("The provided string has no number at the given index.")
	}
	if index > len(str) {
		return -1, errors.New("Index is out of range.")
	}

	numberStr := string(str[index])

	// search to right
	for i := index + 1; i < len(str); i += 1 {
		_, err = strconv.Atoi(string(str[i]))
		isNumber := err == nil
		if isNumber {
			numberStr = numberStr + string(str[i])
		} else {
			break
		}
	}

	// search to left
	for i := index - 1; i >= 0; i -= 1 {
		_, err = strconv.Atoi(string(str[i]))
		isNumber := err == nil
		if isNumber {
			numberStr = string(str[i]) + numberStr
		} else {
			break
		}
	}

	number, err := strconv.Atoi(numberStr)
	if err != nil {
		fmt.Println(err)
		os.Exit(1)
	}

	return number, nil
}

func IsNumber(str string) bool {
	_, err := strconv.Atoi(str)
	return err == nil
}

func ReplaceCharacterAtIndex(originalStr *string, newChar string, index int) {
	*originalStr = (*originalStr)[:index] + newChar + (*originalStr)[index+1:]
}
