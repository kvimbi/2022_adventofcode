import {describe, expect, test} from '@jest/globals';
import {sortStock, loadFoodStack} from "./food_stock";


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
    expect(foodStack).toStrictEqual([
      [1000, 2000, 3000],
      [4000],
      [5000, 6000],
      [7000, 8000, 9000],
      [10000],
    ])
  });

  test('Test empty input', () => {
    const inputStock: number[][] = [];
    const largest = sortStock(inputStock);
    expect(largest).toStrictEqual([])
  })

  test('Find single largest', () => {
    const inputStock = [
      [1000, 2000, 3000],
      [4000],
      [5000, 6000],
      [7000, 8000, 9000],
      [10000],
    ];
    const largest = sortStock(inputStock);
    expect(largest).toStrictEqual([
      {"calories": 4000, "index": 1},
      {"calories": 6000, "index": 0},
      {"calories": 10000, "index": 4},
      {"calories": 11000, "index": 2},
      {"calories": 24000, "index": 3}])

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
    const largest = sortStock(inputStock);
    expect(largest).toStrictEqual([
      {"calories": 4000, "index": 1},
      {"calories": 6000, "index": 0},
      {"calories": 10000, "index": 5},
      {"calories": 11000, "index": 3},
      {"calories": 24000, "index": 2},
      {"calories": 24000, "index": 4}]);
  })

});
