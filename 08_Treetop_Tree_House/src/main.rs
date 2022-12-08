extern crate core;

use std::collections::HashSet;
use std::fs;

fn main() {
    let _input = fs::read_to_string("input.txt").unwrap();

    let _lines: Vec<&str> = _input.split("\n").collect();
    let _col_count = _lines[0].len();
    println!("Size {} x {}", _col_count, _lines.len());

    let mut _visible_trees_count = HashSet::new();

    let mut _t_tree_maxes: Vec<i8> = (0.._col_count).map(|_| -1).collect();
    let mut _b_tree_maxes: Vec<i8> = (0.._col_count).map(|_| -1).collect();

    for _row_index in 0.._lines.len() {
        let mut _l_tree_max = -1;
        let mut _r_tree_max = -1;
        let _t_line = _lines[_row_index];
        let _b_line = _lines[_lines.len() - _row_index - 1];

        for _col_index in 0.._col_count {
            let _l_t_tree_height = String::from(_t_line.chars().nth(_col_index).unwrap()).parse::<i8>().unwrap();
            let _r_t_tree_height = String::from(_t_line.chars().nth(_col_count - _col_index - 1).unwrap()).parse::<i8>().unwrap();
            let _l_b_tree_height = String::from(_b_line.chars().nth(_col_index).unwrap()).parse::<i8>().unwrap();

            if _l_t_tree_height > _l_tree_max {
                _l_tree_max = _l_t_tree_height;
                _visible_trees_count.insert(format!("{}_{}", _row_index, _col_index));
            }
            if _l_t_tree_height > _t_tree_maxes[_col_index] {
                _t_tree_maxes[_col_index] = _l_t_tree_height;
                _visible_trees_count.insert(format!("{}_{}", _row_index, _col_index));
            }

            if _r_t_tree_height > _r_tree_max {
                _r_tree_max = _r_t_tree_height;
                _visible_trees_count.insert(format!("{}_{}", _row_index, _col_count - _col_index - 1));
            }

            if _l_b_tree_height > _b_tree_maxes[_col_index] {
                _b_tree_maxes[_col_index] = _l_b_tree_height;
                _visible_trees_count.insert(format!("{}_{}", _lines.len() - _row_index - 1, _col_index));
            }

        }
    }

    println!("Visible: {}", _visible_trees_count.len());

    let mut total = 0;
    for _row_index in 0.._lines.len() {
        print!("{:03} ", _row_index);
        for _col_index in 0.._col_count {

            if _visible_trees_count.contains(
                format!("{}_{}", _row_index, _col_index).as_str()
            ) {
                print!("*");
                total += 1;
            } else {
                print!(" ")
            }
        }
        println!()
    }
    println!("Total {}", total);
}
