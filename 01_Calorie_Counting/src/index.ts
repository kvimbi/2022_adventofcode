import * as fs from "fs";
import {loadFoodStack, findFirstLargestStock} from "./food_stock";

const fileName = process.argv[2];
if (!fileName) {
  console.error('Usage npm start [file_path]');
  process.exit(1);
}

if (!fs.existsSync(fileName)) {
  console.error(`File ${fileName} not found`);
  process.exit(1);
}
const maxStock =
  findFirstLargestStock(
    loadFoodStack(
      fs.readFileSync(fileName).toString('utf-8')
    )
  )

console.log(maxStock);
