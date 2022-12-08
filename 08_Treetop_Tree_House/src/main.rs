extern crate core;

use std::collections::HashSet;
use std::fs;

struct Move {
    row: i8,
    col: i8,
}

const UP: Move = Move { row: -1, col: 0 };
const DOWN: Move = Move { row: 1, col: 0 };
const LEFT: Move = Move { row: 0, col: -1 };
const RIGHT: Move = Move { row: 0, col: 1 };

fn is_within_bounds(tree_matrix: &Vec<Vec<i8>>, row: i8, col: i8) -> bool {
    row >= 0 && col >= 0 &&
        row < (tree_matrix.len() as i8) &&
        tree_matrix.len() > 0 && col < (tree_matrix[0].len() as i8)
}

fn get_scenic_distance(tree_matrix: &Vec<Vec<i8>>, row: u8, col: u8, move_direction: &Move) -> u32 {
    let mut _visible_trees: u8 = 0;

    let mut _iter_row = (row as i8) + move_direction.row;
    let mut _iter_col = (col as i8) + move_direction.col;

    let _source_height = tree_matrix[row as usize][col as usize];
    let mut _continue_search = is_within_bounds(tree_matrix, _iter_row, _iter_col);
    while _continue_search {
        _visible_trees += 1;
        _continue_search = tree_matrix[_iter_row as usize][_iter_col as usize] < _source_height;
        _iter_row += move_direction.row;
        _iter_col += move_direction.col;
        _continue_search &= is_within_bounds(tree_matrix, _iter_row, _iter_col);
    }
    _visible_trees as u32
}

fn get_scenic_score(tree_matrix: &Vec<Vec<i8>>, row: u8, col: u8) -> u32 {
    get_scenic_distance(
        tree_matrix,
        row,
        col,
        &UP,
    ) * get_scenic_distance(
        tree_matrix,
        row,
        col,
        &DOWN,
    ) * get_scenic_distance(
        tree_matrix,
        row,
        col,
        &LEFT,
    ) * get_scenic_distance(
        tree_matrix,
        row,
        col,
        &RIGHT,
    )
}

fn main() {
    let _input = fs::read_to_string("input.txt").unwrap();

    let _lines: Vec<&str> = _input.split("\n").collect();
    let _col_count = _lines[0].len();
    println!("Size {} x {}", _col_count, _lines.len());

    let _tree_matrix: Vec<Vec<i8>> = (0.._lines.len()).map(|row_index| {
        let _line = _lines[row_index];
        _line.chars().map(|height_char| height_char.to_string().parse::<i8>().unwrap()).collect()
    }).collect();

    let mut _visible_trees_index_set = HashSet::new();

    let mut _t_tree_maxes: Vec<i8> = (0.._col_count).map(|_| -1).collect();
    let mut _b_tree_maxes: Vec<i8> = (0.._col_count).map(|_| -1).collect();

    let mut _max_scenic_score: i32 = -1;

    for _row_index in 0.._lines.len() {
        let mut _l_tree_max = -1;
        let mut _r_tree_max = -1;

        for _col_index in 0.._col_count {
            let _scenic_score = get_scenic_score(
                &_tree_matrix,
                _row_index as u8,
                _col_index as u8,
            ) as i32;
            if _scenic_score > _max_scenic_score {
                _max_scenic_score = _scenic_score;
            }

            let _l_t_tree_height = _tree_matrix[_row_index][_col_index];
            let _r_t_tree_height = _tree_matrix[_row_index][_col_count - _col_index - 1];
            let _l_b_tree_height = _tree_matrix[_tree_matrix.len() - _row_index - 1][_col_index];

            if _l_t_tree_height > _l_tree_max {
                _l_tree_max = _l_t_tree_height;
                _visible_trees_index_set.insert(format!("{}_{}", _row_index, _col_index));
            }
            if _l_t_tree_height > _t_tree_maxes[_col_index] {
                _t_tree_maxes[_col_index] = _l_t_tree_height;
                _visible_trees_index_set.insert(format!("{}_{}", _row_index, _col_index));
            }

            if _r_t_tree_height > _r_tree_max {
                _r_tree_max = _r_t_tree_height;
                _visible_trees_index_set.insert(format!("{}_{}", _row_index, _col_count - _col_index - 1));
            }

            if _l_b_tree_height > _b_tree_maxes[_col_index] {
                _b_tree_maxes[_col_index] = _l_b_tree_height;
                _visible_trees_index_set.insert(format!("{}_{}", _lines.len() - _row_index - 1, _col_index));
            }
        }
        println!()
    }

    println!("Visible: {}", _visible_trees_index_set.len());

    for _row_index in 0.._lines.len() {
        print!("{:03} ", _row_index);
        for _col_index in 0.._col_count {
            if _visible_trees_index_set.contains(
                format!("{}_{}", _row_index, _col_index).as_str()
            ) {
                print!("*");
            } else {
                print!(" ")
            }
        }
        println!()
    }
    println!("Max Scenic score {}", _max_scenic_score);
}
