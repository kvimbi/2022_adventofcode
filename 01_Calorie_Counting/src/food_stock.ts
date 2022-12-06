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

export function findFirstLargestStock(stockPile: number[][]): CalorieStat {
  return stockPile.reduce<CalorieStat>((max, item, index) =>
      ((a: CalorieStat, b: CalorieStat) => a.calories > b.calories ? a : b)(
        {
          index,
          calories: item.reduce((acc, calories) => acc + calories, 0)
        },
        max
      ),
    {index: -1, calories: -1})
}
