import 'dart:io';

import 'package:supply_stacks/context.dart';
import 'package:supply_stacks/solution.dart' as solution;

String getSolution(Context context) {
  return context.stacks.map((stack) => stack.last.replaceAll('[', "").replaceAll("]", "")).join("");
}

void main(List<String> arguments) {
  final lines = File('input.txt').readAsLinesSync();
  final contextFor9000 = solution.parseInputLines(lines);
  contextFor9000.executeInstructions(false);
  print("Result for 9000: ${getSolution(contextFor9000)}");

  final contextFor9001 = solution.parseInputLines(lines);
  contextFor9001.executeInstructions(true);
  print("Result for 9001: ${getSolution(contextFor9001)}");
}
