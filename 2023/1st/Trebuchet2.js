const fs = require("fs");

const spelledNumbers = {
  one: 1,
  two: 2,
  three: 3,
  four: 4,
  five: 5,
  six: 6,
  seven: 7,
  eight: 8,
  nine: 9,
};

const inputs = fs.readFileSync("./Trebuchet.txt", { encoding: "utf8" });
const lines = inputs.split("\n");
const calibrationValues = [];

for (const line of lines) {
  const numbers = line
    .split("")
    .map((value, index) => {
      if (Number.isInteger(+value)) return +value;
      const codeFromIndex = line.substring(index);
      const key = Object.keys(spelledNumbers).filter((value) =>
        codeFromIndex.startsWith(value),
      )[0];
      if (!key) return null;
      return spelledNumbers[key];
    })
    .filter((value) => value !== null);
  if (!numbers.length) continue;
  const calibrationValue = numbers.at(0).toString() + numbers.at(-1).toString();
  calibrationValues.push(+calibrationValue);
}

const sumOfCalibrationValues = calibrationValues.reduce(
  (prev, curr) => prev + curr,
  0,
);

console.log(sumOfCalibrationValues);
