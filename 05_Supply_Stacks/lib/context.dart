import 'dart:math';

class Instruction {
  final int fromStack;
  final int toStack;
  final int count;

  Instruction(this.fromStack, this.toStack, this.count);

  @override
  bool operator ==(Object other) {
    if (other.runtimeType != Instruction) {
      return false;
    }

    other as Instruction;
    return fromStack == other.fromStack && toStack == other.toStack && count == other.count;
  }

  @override
  int get hashCode => count + (fromStack * 1000) + (toStack * 100000);

  @override
  String toString() {
    return "move $count from $fromStack to $toStack";
  }
}

class Context {
  final List<List<String>> stacks = List.empty(growable: true);
  final List<Instruction> instructions = List.empty(growable: true);

  Context(int stackCount) {
    initStackCount(stackCount);
  }

  int getStackCount() {
    return stacks.length;
  }

  /// Model 9000 can move only one crate at a time.
  /// Model 9001 can move multiple at the same time.
  /// @see https://adventofcode.com/2022/day/5#part2
  void executeInstructions(bool isModel9001) {
    for (var instruction in instructions) {
      if (instruction.count == 0) {
        continue;
      }

      final fromStackIndex = instruction.fromStack - 1;
      final toStackIndex = instruction.toStack - 1;
      final movedStack = stacks[fromStackIndex].sublist(max(0, stacks[fromStackIndex].length - instruction.count));

      stacks[toStackIndex].addAll(isModel9001 ? movedStack : movedStack.reversed);
      stacks[fromStackIndex]
          .removeRange(max(0, stacks[fromStackIndex].length - instruction.count), stacks[fromStackIndex].length);
    }
  }

  void initStackCount(int count) {
    stacks.clear();
    stacks.addAll(List.generate(count, (index) => []));
  }
}
