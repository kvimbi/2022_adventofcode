from game import InputParser

if __name__ == '__main__':
    f = open("input.txt", "r")
    lines = f.readlines()
    game_rounds = list(map(InputParser.parse_line, lines))
    total_score = sum(map(lambda game: game.get_my_score(), game_rounds))
    print("My score:", total_score)
