import {describe, expect, test} from '@jest/globals';
import {findFirstLargestStock, loadFoodStack} from "./food_stock";


describe('01 Advent', () => {
  test('Parsing an input', () => {
    const example_input = '1000\n' +
      '2000\n' +
      '3000\n' +
      '\n' +
      '4000\n' +
      '\n' +
      '5000\n' +
      '6000\n' +
      '\n' +
      '7000\n' +
      '8000\n' +
      '9000\n' +
      '\n' +
      '10000';

    const foodStack = loadFoodStack(example_input)
    // test number of elves to be 5
    expect(foodStack).not.toBeNull();
    expect(foodStack.length).toBe(5);
    expect(foodStack).toEqual([
      [1000, 2000, 3000],
      [4000],
      [5000, 6000],
      [7000, 8000, 9000],
      [10000],
    ])
  });

  test('Test empty input', () => {
    const inputStock: number[][] = [];
    const largest = findFirstLargestStock(inputStock);
    expect(largest.index).toBe(-1)
    expect(largest.calories).toBe(-1)
  })

  test('Find single largest', () => {
    const inputStock = [
      [1000, 2000, 3000],
      [4000],
      [5000, 6000],
      [7000, 8000, 9000],
      [10000],
    ];
    const largest = findFirstLargestStock(inputStock);
    expect(largest.index).toBe(3)
    expect(largest.calories).toBe(24000)
  })
  test('Find multiple largest', () => {
    const inputStock = [
      [1000, 2000, 3000],
      [4000],
      [22000, 2000],
      [5000, 6000],
      [7000, 8000, 9000],
      [10000],
    ];
    const largest = findFirstLargestStock(inputStock);
    expect(largest.index).toBe(2)
    expect(largest.calories).toBe(24000)
  })

});
