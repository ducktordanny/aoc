// NOTE: This is just a warm up

const fs = require("fs");

let floor = 0;
let firstTimeInBasement = 0;

const inputs = fs.readFileSync("./NotQuiteLisp.txt", { encoding: "utf8" });

for (const [index, command] of inputs.split("").entries()) {
  if (command === "(") floor++;
  else if (command === ")") floor--;
  if (floor === -1 && firstTimeInBasement === 0)
    firstTimeInBasement = index + 1;
}

console.log("Floor in the end:", floor);
console.log("First time in the basement:", firstTimeInBasement);
