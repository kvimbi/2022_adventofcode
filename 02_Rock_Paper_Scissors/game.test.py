import unittest
from parameterized import parameterized

from game import InputParser, GameRound


class MyTestCase(unittest.TestCase):
    def test_parsing(self):
        game_input = "A Y\nB X\nC Z"
        game_rounds = InputParser.parse(game_input)
        self.assertEqual(len(game_rounds), 3)
        self.assertEqual(game_rounds, [
            GameRound("A", "Y"),
            GameRound("B", "X"),
            GameRound("C", "Z"),
        ])

    @parameterized.expand([
        ["A", "X", "DRAW", 4],
        ["A", "Y", "VICTORY", 8],
        ["A", "Z", "DEFEAT", 3],
        ["B", "X", "DEFEAT", 1],
        ["B", "Y", "DRAW", 5],
        ["B", "Z", "VICTORY", 9],
        ["C", "X", "VICTORY", 7],
        ["C", "Y", "DEFEAT", 2],
        ["C", "Z", "DRAW", 6],
    ])
    def test_game_scoring(self, opponent_play, my_play, expected_result, expected_score):
        game_round = GameRound(opponent_play, my_play)
        result = game_round.get_my_result()
        score = game_round.get_my_score()
        self.assertEqual(result, expected_result)
        self.assertEqual(score, expected_score)


if __name__ == '__main__':
    unittest.main()
