export function loadFoodStack(input: string): number[][] {
  return input
    .split("\n")
    .reduce<number[][]>((acc, line) => {
      if (line.trim() === "") {
        acc.push([]);
      } else {
        acc[acc.length - 1].push(parseInt(line));
      }

      return acc
    }, [[]]);
}

type CalorieStat = {
  index: number,
  calories: number
}

export function sortStock(stockPile: number[][]): CalorieStat[] {
  return stockPile.map((item, index) => ({
      index,
      calories: item.reduce((acc, calories) => acc + calories, 0)
    })).sort((a, b) => Math.sign(a.calories - b.calories))
}
