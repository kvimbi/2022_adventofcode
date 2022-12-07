import 'dart:io';

import 'package:supply_stacks/context.dart';
import 'package:supply_stacks/solution.dart' as solution;

void _printFirstSolution(Context context) {
  print(
      context.stacks.map((stack) => stack.last.replaceAll('[', "").replaceAll("]", "")).join("")
  );
}

void main(List<String> arguments) {
  File file = File('input.txt');
  final context = solution.parseInputLines(file.readAsLinesSync());
  context.executeInstructions();
  _printFirstSolution(context);
}
