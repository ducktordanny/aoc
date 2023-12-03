package gearratios

func ReplaceCharacterAtIndex(originalStr *string, newChar string, index int) {
	*originalStr = (*originalStr)[:index] + newChar + (*originalStr)[index+1:]
}
