"""
A, X - ROCK
B, Y - PAPER
C, Z - SCISSORS
"""

PLAY_POINTS_MAP = {
    "A": 1,
    "B": 2,
    "C": 3,
    "X": 1,
    "Y": 2,
    "Z": 3,
}

MY_GAME_RESULT = {
    "AX": "DRAW",
    "AY": "VICTORY",
    "AZ": "DEFEAT",

    "BX": "DEFEAT",
    "BY": "DRAW",
    "BZ": "VICTORY",

    "CX": "VICTORY",
    "CY": "DEFEAT",
    "CZ": "DRAW",
}

""" r p s
X - loose
Y - draw
Z - win
"""
MY_GAME_INTENT = {
    "AX": "Z",
    "AY": "X",
    "AZ": "Y",

    "BX": "X",
    "BY": "Y",
    "BZ": "Z",

    "CX": "Y",
    "CY": "Z",
    "CZ": "X",
}

MY_GAME_POINTS_MAP = {
    "VICTORY": 6,
    "DRAW": 3,
    "DEFEAT": 0
}


class GameRound:
    def __init__(self, opponent_play, my_play):
        self.opponent_play = opponent_play
        self.my_play = my_play

    def __eq__(self, o: object) -> bool:
        if not isinstance(o, GameRound):
            return False
        return self.opponent_play == o.opponent_play and self.my_play == o.my_play

    def get_my_result(self) -> str:
        return MY_GAME_RESULT[self.opponent_play + self.my_play]

    def get_my_score(self) -> int:
        return PLAY_POINTS_MAP[self.my_play] + MY_GAME_POINTS_MAP[self.get_my_result()]

    def get_my_correct_result(self) -> str:
        return MY_GAME_RESULT[self.opponent_play + MY_GAME_INTENT[self.opponent_play + self.my_play]]

    def get_my_correct_score(self) -> int:
        """
        see https://adventofcode.com/2022/day/2#part2
        :return:
        """
        return PLAY_POINTS_MAP[MY_GAME_INTENT[self.opponent_play + self.my_play]] + MY_GAME_POINTS_MAP[self.get_my_correct_result()]


class InputParser:
    @staticmethod
    def parse_line(line: str) -> GameRound:
        parts = line.strip().split(" ")
        if len(parts) != 2 or parts[0] not in PLAY_POINTS_MAP or parts[1] not in PLAY_POINTS_MAP:
            raise Exception("Invalid game play " + line)

        return GameRound(parts[0], parts[1])

    @staticmethod
    def parse(text_input: str) -> [GameRound]:
        return list(
            map(
                InputParser.parse_line,
                text_input.split("\n")
            )
        )


if __name__ == '__main__':
    pass
