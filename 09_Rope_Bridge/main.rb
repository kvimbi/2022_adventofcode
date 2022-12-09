require 'set'

instructions = File.open("input.txt")::read::split "\n"

start_pos = 0

t_visited = Set.new
t_visited.add([start_pos, start_pos])

h_pos = [start_pos, start_pos]
t_pos_arr = []
(0..8).each { t_pos_arr.push([start_pos, start_pos]) }

def dist(h_pos, t_pos)
  Math.sqrt(
    ((h_pos[0] - t_pos[0]) ** 2) +
      (h_pos[1] - t_pos[1]) ** 2
  )
end

puts [].at(0)

def show(h_pos, t_pos, t_visited)
  all_x = t_visited.collect { |a| a[0] }
                   .push(h_pos.length > 0 ? h_pos[0] : 0)
                   .concat(t_pos.map {|e| e[0]})
  min_col = all_x.min - 1
  max_col = all_x.max + 1

  all_y =t_visited.collect { |a| a[1] }
                  .push(h_pos.length > 1 ? h_pos[1] : 0)
                  .concat(t_pos.map {|e| e[1]})
  min_row = all_y.min - 1
  max_row = all_y.max + 1

  (min_row..max_row).each do |row_i|
    (min_col..max_col).each do |col_i|
      if [col_i, row_i] == h_pos
        print "H"
      elsif t_pos.include?([col_i, row_i])
        print t_pos.index([col_i, row_i]) + 1
      else
        print(t_visited.include?([col_i, row_i]) ? "#" : ".")
      end
    end
    puts ""
  end
  puts ""
end

head_move = {
  "R" => [1, 0],
  "L" => [-1, 0],
  "U" => [0, -1],
  "D" => [0, 1],
}

def normalize_force(force)
  if force > 1
    return 1
  elsif force < -1
    return -1
  end

  force
end

def get_force(elem1, elem2)
  if dist(elem1, elem2) >= 2
    return [normalize_force(elem1[0] - elem2[0]), normalize_force(elem1[1] - elem2[1])]
  end

  [0, 0]
end

instructions.each { |instruction|
  command, count = instruction.split " "

  command_x = head_move[command][0]
  command_y = head_move[command][1]

  (0..count.to_i - 1).each { ||
    h_pos = [h_pos[0] + command_x, h_pos[1] + command_y]

    prev_t = h_pos
    (0..t_pos_arr.length - 1).each { |t_pos_index|
      t_pos = t_pos_arr[t_pos_index]
      force = get_force(prev_t, t_pos)
      t_pos_arr[t_pos_index] = [t_pos[0] + force[0], t_pos[1] + force[1]]
      prev_t = t_pos_arr[t_pos_index]
    }
    t_visited.add t_pos_arr.last
    # show(h_pos, t_pos_arr, t_visited)
  }
}

show([], [], t_visited)
puts "Visited #{t_visited.length}"

