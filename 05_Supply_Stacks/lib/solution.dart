import 'context.dart';

final LABEL_ROW_REGEXP = RegExp(r'^\s+[0-9][\s0-9]*$');
final instructionRowRegexp = RegExp(r'move ([0-9]+) from ([0-9]+) to ([0-9]+)');

Instruction parseInstructionLine(String line) {
  final parts = instructionRowRegexp.allMatches(line).toList().first;
  return Instruction(
      int.parse(parts.group(2)!),
      int.parse(parts.group(3)!),
      int.parse(parts.group(1)!)
  );
}

Context parseInstructions(List<String> lines, int index, Context context) {
  if (lines.length == index) {
    return context;
  }
  context.instructions.add(parseInstructionLine(lines[index]));
  return parseInstructions(lines, index + 1, context);
}

int _countStacks(String line) {
  return (line.length + 1) ~/ 4;
}

List<String> splitStackLine(String line) {
  return List.generate(_countStacks(line), (index) => line.substring(index * 4, (index * 4 + 3)))
      .map((item) => item.trim())
      .toList();
}

extension EachIndexed<E> on List<E> {
  forEachIndexed(void Function(E elem, int index) action) {
    for (int i = 0; i < length; i++) {
      action(this[i], i);
    }
  }
}

Context parseCratesLine(List<String> lines, int index, Context context) {
  if (lines.length == index) {
    return context;
  }

  if (LABEL_ROW_REGEXP.hasMatch(lines[index])) {
    return parseInstructions(lines, index + 2, context);
  }

  splitStackLine(lines[index])
      .forEachIndexed((element, index) {
            if (element.isNotEmpty) {
              context.stacks[index].insert(0, element);
            }
          });

  return parseCratesLine(lines, index + 1, context);
}

Context parseInputLines(List<String> lines) {
  return parseCratesLine(lines, 0, Context(_countStacks(lines.first)));
}