import * as fs from "fs";
import {loadFoodStack, sortStock} from "./food_stock";

const fileName = process.argv[2];
if (!fileName) {
  console.error('Usage npm start [file_path]');
  process.exit(1);
}

if (!fs.existsSync(fileName)) {
  console.error(`File ${fileName} not found`);
  process.exit(1);
}
const sortedStock =
  sortStock(
    loadFoodStack(
      fs.readFileSync(fileName).toString('utf-8')
    )
  )


console.log(
  "Richest elf:",
  sortedStock[sortedStock.length - 1].calories
);

console.log(
  "Top 3 elves total calories",
  sortedStock.slice(sortedStock.length - 3)
    .reduce((acc, item) => item.calories + acc, 0)
);
