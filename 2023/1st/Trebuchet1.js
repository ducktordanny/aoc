const fs = require("fs");

const inputs = fs.readFileSync("./Trebuchet.txt", { encoding: "utf8" });
const lines = inputs.split("\n");
const calibrationValues = [];

for (const line of lines) {
  const numbers = line.split("").filter((value) => Number.isInteger(+value));
  if (!numbers.length) continue;
  const calibrationValue = numbers.at(0).toString() + numbers.at(-1).toString();
  calibrationValues.push(+calibrationValue);
}

const sumOfCalibrationValues = calibrationValues.reduce(
  (prev, curr) => prev + curr,
  0,
);

console.log(sumOfCalibrationValues);
