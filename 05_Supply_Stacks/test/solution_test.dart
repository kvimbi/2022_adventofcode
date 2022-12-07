import 'dart:io';

import 'package:supply_stacks/context.dart';
import 'package:supply_stacks/solution.dart';
import 'package:test/test.dart';

void main() {
  test('Parsing sample stack line', () {
    expect(
        splitStackLine("[Q]     [N]     [H] [W] [T]     [Q]"),
        ['[Q]', '', '[N]', '', '[H]', '[W]', '[T]', '', '[Q]']
    );
  });

  test('Parsing sample instruction line', () {
    expect(
        parseInstructionLine("move 1 from 8 to 7"),
        Instruction(8, 7, 1)
    );
  });

  Context prepareContext() {
    final context = Context(2);
    context.stacks[0].addAll([
      "A",
      "B",
      "C",
    ]);

    return context;
  }

  test('Executing valid instruction for 9000', () {
    final context = prepareContext();
    context.instructions.add(Instruction(1, 2, 2));
    context.executeInstructions(false);

    expect(context.stacks[0].length, 1);
    expect(context.stacks[0][0], "A");
    expect(context.stacks[1].length, 2);
    expect(context.stacks[1][0], "C");
    expect(context.stacks[1][1], "B");
  });
  test('Executing valid instruction for 9001', () {
    final context = prepareContext();
    context.instructions.add(Instruction(1, 2, 2));
    context.executeInstructions(true);

    expect(context.stacks[0].length, 1);
    expect(context.stacks[0][0], "A");
    expect(context.stacks[1].length, 2);
    expect(context.stacks[1][0], "B");
    expect(context.stacks[1][1], "C");
  });

  test('Executing zero instruction for 9000', () {
    final context = prepareContext();
    context.instructions.add(Instruction(1, 2, 0));
    context.executeInstructions(false);

    expect(context.stacks[0].length, 3);
    expect(context.stacks[1].length, 0);
  });

  test('Executing over-sized instruction for 9000', () {
    final context = prepareContext();
    context.instructions.add(Instruction(1, 2, 4));
    context.executeInstructions(false);

    expect(context.stacks[0].length, 0);
    expect(context.stacks[1].length, 3);
  });

  test('Executing multiple instructions', () {
    final context = prepareContext();
    context.instructions.add(Instruction(1, 2, 2));
    context.instructions.add(Instruction(2, 1, 1));
    context.executeInstructions(false);

    expect(context.stacks[0].length, 2);
    expect(context.stacks[1].length, 1);
  });

  test('Validate sample solution for 9000', () {
    File file = File('test_input.txt');
    final context = parseInputLines(file.readAsLinesSync());
    context.executeInstructions(false);
    expect(context.stacks[0].last, "[C]");
    expect(context.stacks[1].last, "[M]");
    expect(context.stacks[2].last, "[Z]");
  });

  test('Validate sample solution for 9001', () {
    File file = File('test_input.txt');
    final context = parseInputLines(file.readAsLinesSync());
    context.executeInstructions(true);
    expect(context.stacks[0].last, "[M]");
    expect(context.stacks[1].last, "[C]");
    expect(context.stacks[2].last, "[D]");
  });
}
